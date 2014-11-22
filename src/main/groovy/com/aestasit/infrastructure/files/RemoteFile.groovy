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
