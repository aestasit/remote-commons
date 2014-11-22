package com.aestasit.infrastructure.commands

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
@TypeChecked
class NullExecutionContext implements RemoteExecutionContext {

  @Override
  RemoteCommandOutput execute(String command) {
    new RemoteCommandOutput(0, "")
  }

}
