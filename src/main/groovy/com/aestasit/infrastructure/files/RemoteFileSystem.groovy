package com.aestasit.infrastructure.files

/**
 * Interface that defines base remote file system operations.
 *
 * @author Andrey Adamovich
 */
interface RemoteFileSystem {

  void uploadFile(File localFile, String remoteDestination)

  void downloadFile(String remoteFile, File localDir)

  boolean createRemoteDirectory(String path)

  List<String> listFiles(String remoteDir)

  List<String> listFilesRecursively(String remoteDir)

  boolean fileExists(String path)

  String normalizePath(String path)

}
