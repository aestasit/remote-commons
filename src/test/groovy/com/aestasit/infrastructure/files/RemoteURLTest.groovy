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

import com.aestasit.infrastructure.RemoteURL
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.junit.Test

@CompileStatic
class RemoteURLTest extends GroovyTestCase {

  @Test
  void "test valid urls"() {
    checkUrl(new RemoteURL('192.168.1.1'))
    checkUrl(new RemoteURL('192.168.1.1:23'))
    checkUrl(new RemoteURL('user@192.168.1.1:23'))
    checkUrl(new RemoteURL('user:password@192.168.1.1:23'))
    checkUrl(new RemoteURL('user:password@192.168.1.1'))
    checkUrl(new RemoteURL('user:password@192.168.1.1', 23))
  }

  @Test
  void "test invalid urls"() {
    checkError { new RemoteURL('user:password@192.168.1.1:') }
    checkError { new RemoteURL('@192.168.1.1:23') }
    checkError { new RemoteURL(':password@192.168.1.1') }
    checkError { new RemoteURL(':password@') }
    checkError { new RemoteURL('user:password@') }
  }

  void checkUrl(RemoteURL url) {
    assertNotNull(url.host)
    assertNotNull(url.port)
  }

  void checkError(Closure cl) {
    try {
      RemoteURL url = (RemoteURL) cl()
      fail("URL should fail parsing: ${url}")
    } catch (MalformedURLException ex) {
      assertNotNull ex.message
      assertTrue ex.message.contains('Unknown URL format')
    }
  }

}
