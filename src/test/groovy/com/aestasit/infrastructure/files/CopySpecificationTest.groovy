package com.aestasit.infrastructure.files

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.junit.Test

import static com.aestasit.infrastructure.files.CopySpecification.copy

@CompileStatic
@TypeChecked
class CopySpecificationTest extends GroovyTestCase {

  @Test
  void testCopy() {
    copy {
      from {
        localDir new File('.')
      }
      into {
        remoteDir '/tmp/test'
      }
    }
  }

}
