package com.aestasit.infrastructure.logging

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
@TypeChecked
abstract class BaseEventLogger implements EventLogger {

  @Override
  void commandErrorOutput(String[] lines) {
    lines.each { String line ->
      commandErrorOutput()
    }
  }

  @Override
  void commandOutput(String[] lines) {
    lines.each { String line ->
      commandOutput()
    }
  }

}
