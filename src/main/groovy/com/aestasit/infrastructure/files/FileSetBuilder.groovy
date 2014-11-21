/*
 * Copyright (C) 2011-2014 Aestas/IT
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

import com.sun.javaws.exceptions.InvalidArgumentException
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import static com.aestasit.infrastructure.files.FileSetType.*

/**
 * File set data builder that is used to collect data about remote or local file collection used for uploading or downloading.
 *
 * @author Andrey Adamovich
 *
 */
@CompileStatic
@TypeChecked
class FileSetBuilder {

  private FileSetType type = UNKNOWN
  private List<File> localDirs = new ArrayList<File>()
  private List<File> localFiles = new ArrayList<File>()
  private List<String> remoteFiles = new ArrayList<String>()
  private List<String> remoteDirs = new ArrayList<String>()

  void localFile(String file) {
    if (file) {
      setType(LOCAL)
      localFiles += new File(file)
    }
  }

  void localFile(File file) {
    if (file) {
      setType(LOCAL)
      localFiles += file
    }
  }

  void localDir(String dir) {
    if (dir) {
      setType(LOCAL)
      localDirs += new File(dir)
    }
  }

  void localDir(File dir) {
    if (dir) {
      setType(LOCAL)
      localDirs += dir
    }
  }

  void remoteFile(String file) {
    if (file) {
      setType(REMOTE)
      remoteFiles += file
    }
  }

  void remoteDir(String dir) {
    if (dir) {
      setType(REMOTE)
      remoteDirs += dir
    }
  }

  private setType(FileSetType type) {
    if (this.type == UNKNOWN || this.type == type) {
      this.type = type
    } else {
      throw new InvalidArgumentException("File set can not contain both local and remote source or target definitions!")
    }
  }

  FileSetType getType() {
    type
  }

  List<File> getLocalDirs() {
    localDirs
  }

  List<File> getLocalFiles() {
    localFiles
  }

  List<String> getRemoteFiles() {
    remoteFiles
  }

  List<String> getRemoteDirs() {
    remoteDirs
  }

}
