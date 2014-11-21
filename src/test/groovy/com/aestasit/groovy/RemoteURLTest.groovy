package com.aestasit.groovy

import com.aestasit.infrastructure.RemoteURL
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.junit.Test

@CompileStatic
@TypeChecked
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
