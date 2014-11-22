package com.aestasit.infrastructure.files

/**
 *  RemoteFileSystem implementation that just uses local file system as a target.
 *
 *  @author Andrey Adamovich
 */
class LocalFileSystem implements RemoteFileSystem {

  @Override
  void uploadFile(File localFile, String remoteDestination) {
    // TODO: localFile.copyTo(new File(remoteDestination))
  }

  @Override
  void downloadFile(String remoteFile, File localDir) {
    // TODO: new File(remoteFile).copyTo(localDir)
  }

  @Override
  boolean createRemoteDirectory(String path) {
    new File(path).mkdir()
  }

  @Override
  List<String> listFiles(String remoteDir) {
    new File(remoteDir).list().toList()
  }

  @Override
  List<String> listFilesRecursively(String remoteDir) {
    List<String> files = [] as List<String>
    new File(remoteDir).eachFileRecurse { File file -> files << file }
    files
  }

  @Override
  boolean fileExists(String path) {
    new File(path).exists()
  }

  @Override
  String normalizePath(String path) {
    path
  }

}
