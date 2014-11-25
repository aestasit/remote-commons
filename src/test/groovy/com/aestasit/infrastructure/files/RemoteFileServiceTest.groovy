package com.aestasit.infrastructure.files

import com.aestasit.infrastructure.commands.NullExecutionContext
import com.aestasit.infrastructure.logging.SysOutEventLogger
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.junit.Before
import org.junit.Test

@CompileStatic
@TypeChecked
class RemoteFileServiceTest {

  RemoteFileService fileService

  @Before
  void create() {
    fileService = new RemoteFileService(new LocalFileSystem(), new NullExecutionContext(), new SysOutEventLogger())
  }

  @Test
  void testFileDownloading() {
//    fileService.copy {
//      from {
//      }
//    }
  }

  @Test
  void testFileUploading() {
//    fileService.copy {
//      from {
//      }
//    }
  }

}
