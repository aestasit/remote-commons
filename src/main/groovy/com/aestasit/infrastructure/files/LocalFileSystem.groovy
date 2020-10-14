/*
 * Copyright (C) 2011-2020 Aestas/IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
