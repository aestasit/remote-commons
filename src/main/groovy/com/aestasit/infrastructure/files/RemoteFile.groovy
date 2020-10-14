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

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

/**
 * Class that defines common operations for a remote file defined by a path and file system interface.
 *
 * @author Andrey Adamovich
 */
@CompileStatic
@TypeChecked
@Canonical
class RemoteFile {

  private String remotePath
  private RemoteFileSystem fileSystem

  RemoteFile(String remotePath, RemoteFileSystem fileSystem) {
    this.fileSystem = fileSystem
    this.remotePath = remotePath
  }

  String getText() {
    File tempFile = File.createTempFile(this.getClass().getPackage().name, "txt")
    try {
      fileSystem.downloadFile(remotePath, tempFile)
      return tempFile.text
    } finally {
      tempFile.delete()
    }
  }

  void setText(String text) {
    File tempFile = File.createTempFile(this.getClass().getPackage().name, "txt")
    text.eachLine { String line ->
      tempFile << "${line.trim()}\n"
    }
    try {
      fileSystem.uploadFile(tempFile, remotePath)
    } finally {
      tempFile.delete()
    }
  }

  void touch() {
    // TODO: implement
    // delegate.exec(command: 'touch ' + this.destination, failOnError: true, showOutput: true)
  }

  String getOwner() {
    // TODO: implement
    null
  }

  void setOwner(String user) {
  }

  String getGroup() {
    // TODO: implement
    null
  }

  void setGroup(String group) {
    // TODO: implement
  }

  void setPermissions(int mask) {
    // TODO: implement
  }

  int getPermissions() {
    // TODO: implement
  }

}
