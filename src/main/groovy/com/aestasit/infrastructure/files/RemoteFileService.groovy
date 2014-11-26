package com.aestasit.infrastructure.files

import com.aestasit.infrastructure.exceptions.ConfigurationException
import com.aestasit.infrastructure.commands.RemoteExecutionContext
import com.aestasit.infrastructure.logging.EventLogger
import groovy.transform.CompileStatic

import static groovy.lang.Closure.DELEGATE_FIRST
import static com.aestasit.infrastructure.files.FileSetType.*
import static org.apache.commons.io.FilenameUtils.*
import static org.apache.commons.codec.digest.DigestUtils.md5Hex

@CompileStatic
class RemoteFileService {

  private RemoteFileSystem fileSystem
  private RemoteExecutionContext executionContext
  private EventLogger eventLogger

  RemoteFileService(RemoteFileSystem fileSystem, RemoteExecutionContext executionContext, EventLogger eventLogger) {
    this.eventLogger = eventLogger
    this.fileSystem = fileSystem
    this.executionContext = executionContext
  }

  void copy(File sourceFile, String dst) {
    CopySpecification specification = new CopySpecification()
    specification.with {
      from { localFile(sourceFile) }
      into { remoteDir(dst) }
    }
    upload(specification)
  }

  void copy(@DelegatesTo(strategy = DELEGATE_FIRST, value = CopySpecification) Closure cl) {
    CopySpecification specification = new CopySpecification()
    cl.delegate = specification
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    copy(specification)
  }

  void copy(CopySpecification specification) {
    validateCopySpecification(specification)
    if (specification.source.type == LOCAL) {
      upload(specification)
    } else if (specification.source.type == REMOTE) {
      download(specification)
    }
  }

  RemoteFile remoteFile(String destination) {
    new RemoteFile(destination, fileSystem)
  }

  RemoteFile remoteDir(String destination) {
    new RemoteFile(destination, fileSystem)
  }

  boolean mkdir(String destination) {
    // TODO: improve error handling
    exec "mkdir -p ${destination}"
  }

  boolean remkdir(String destination) {
    delete(destination)
    mkdir(destination)
  }

  // TODO: Allow setting parameters (permissions, owner etc.) for remote directory creation through closure.
  // boolean mkdir(Closure cl) {
  //
  // }
  //
  // boolean remkdir(Closure cl) {
  //
  // }

  boolean delete(String destination) {
    // TODO: improve error handling
    exec "rm -rf ${destination}"
  }

  boolean touch(String destination) {
    // TODO: improve error handling
    exec "touch ${destination}"
  }

  // TODO: Allow remote file manipulation through closure.
  // RemoteFile remoteFile(String destination, Closure cl) {
  //
  // }
  //
  // RemoteFile remoteDir(String destination, Closure cl) {
  //
  // }

  ///////////////////////////////////////////////////////////
  // PRIVATE METHODS
  ///////////////////////////////////////////////////////////

  static private void validateCopySpecification(CopySpecification specification) {
    if (specification.source.type == null || specification.source.type == UNKNOWN ||
        specification.target.type == null || specification.target.type == UNKNOWN) {
      throw new ConfigurationException("Either copy source (from) or target (into) is of unknown type!")
    }
    if (specification.source.type == specification.target.type) {
      throw new ConfigurationException("Copy source (from) and target (into) shouldn't be both local or both remote!")
    }
  }

  private void upload(CopySpecification specification) {

    def remoteDirs = specification.target.remoteDirs
    def remoteFiles = specification.target.remoteFiles

    // Check if upload should go through an intermediate directory and append its path hash to all target paths.
    Map<String, String> uploadMap = [:] as Map<String, String>
    String uploadToDirectory = null // TODO: get value from configuration
    if (uploadToDirectory) {
      eventLogger.debug("Uploading through: ${uploadToDirectory}")
      def uploadDirectory = fileSystem.normalizePath(uploadToDirectory)
      fileSystem.createRemoteDirectory(uploadDirectory)
      remoteDirs = remoteDirs.collect { String dstPath ->
        def uploadPath = uploadDirectory + '/' + md5Hex(dstPath)
        uploadMap[uploadPath] = dstPath
        uploadPath
      }
      remoteFiles = remoteFiles.collect { String dstPath ->
        def dstDir = getFullPathNoEndSeparator(dstPath)
        def dstName = new File(dstPath).name
        def uploadPath = uploadDirectory + '/' + md5Hex(dstDir) + '/' + dstName
        uploadMap[uploadPath] = dstDir
        uploadPath
      }
    }

    // Create remote directories.
    remoteFiles.each { String dstFile ->
      def dstDir = getFullPathNoEndSeparator(dstFile)
      fileSystem.createRemoteDirectory(dstDir)
    }
    remoteDirs.each { String dstDir ->
      fileSystem.createRemoteDirectory(dstDir)
    }

    // Upload local files and directories.
    def allLocalFiles = specification.source.localFiles + specification.source.localDirs
    allLocalFiles.each { File sourcePath ->
      if (sourcePath.isDirectory()) {
        sourcePath.eachFileRecurse { File childPath ->
          def relativePath = relativePath(sourcePath, childPath)
          eventLogger.debug("Working with relative path: $relativePath")
          remoteDirs.each { String dstDir ->
            if (childPath.isDirectory()) {
              def dstParentDir = fileSystem.normalizePath(concat(dstDir, relativePath))
              fileSystem.createRemoteDirectory(dstParentDir)
            } else {
              def dstPath = fileSystem.normalizePath(concat(dstDir, relativePath))
              fileSystem.uploadFile(childPath.canonicalFile, dstPath)
            }
          }
        }
      } else {
        remoteDirs.each { String dstDir ->
          def dstPath = fileSystem.normalizePath(concat(dstDir, sourcePath.name))
          fileSystem.uploadFile(sourcePath, dstPath)
        }
        remoteFiles.each { String dstFile ->
          fileSystem.uploadFile(sourcePath, fileSystem.normalizePath(dstFile))
        }
      }
    }

    // Move files to their final destination using predefined command.
    String postUploadCommand = null // TODO: read from configuration
    if (uploadToDirectory && postUploadCommand) {
      remoteDirs.each { String copiedPath ->
        executionContext.execute(postUploadCommand.replaceAll('%from%', copiedPath).replaceAll('%to%', uploadMap[copiedPath]))
      }
      remoteFiles.each { String copiedFilePath ->
        def copiedPath = getFullPathNoEndSeparator(copiedFilePath)
        executionContext.execute(postUploadCommand.replaceAll('%from%', copiedPath).replaceAll('%to%', uploadMap[copiedFilePath]))
      }
    }

  }

  private void download(CopySpecification specification) {

    // Download remote files.
    specification.source.remoteFiles.each { String srcFile ->
      specification.target.localDirs.each { File dstDir ->
        dstDir.mkdirs()
        fileSystem.downloadFile(srcFile, dstDir)
      }
      specification.target.localFiles.each { File dstFile ->
        dstFile.parentFile.mkdirs()
        fileSystem.downloadFile(srcFile, dstFile)
      }
    }

    // Download remote directories.
    specification.source.remoteDirs.each { String srcDir ->
      remoteEachFileRecursively(srcDir) { String srcFile ->
        specification.target.localDirs.each { File dstDir ->
          def dstFile = new File(dstDir.canonicalPath, relativePath(srcDir, srcFile))
          dstFile.parentFile.mkdirs()
          fileSystem.downloadFile(srcFile, new File(dstDir.canonicalPath, relativePath(srcDir, srcFile)))
        }
      }
      specification.target.localFiles.each { File dstFile ->
        eventLogger.warn("Can't copy remote directory ($srcDir) to a local file (${dstFile.path})!")
      }
    }

  }

  private String relativePath(File parent, File child) {
    fileSystem.normalizePath(child.canonicalPath.replace(parent.canonicalPath, '')).replaceAll('^/', '')
  }

  private static String relativePath(String parent, String child) {
    normalizeNoEndSeparator(child)
        .replace(normalizeNoEndSeparator(parent) + File.separatorChar, '')
        .replace(File.separatorChar.toString(), '/')
  }

  private void remoteEachFileRecursively(String remoteDir, Closure cl) {
    fileSystem.listFilesRecursively(remoteDir).each(cl)
  }

}
