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
