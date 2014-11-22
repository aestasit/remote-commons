package com.aestasit.infrastructure.files

import static org.apache.commons.io.FilenameUtils.*

abstract class PosixFileSystem implements RemoteFileSystem {

  @Override
  String normalizePath(String path) {
    separatorsToUnix(normalize(path))
  }
}
