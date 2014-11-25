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

  @Override
  RemoteCommandOutput execute(RemoteCommand command) {
    return null
  }

  @Override
  RemoteCommandOutput execute(Closure cl) {
    return null
  }

  @Override
  RemoteCommandOutput execute(RemoteCommand command, RemoteCommand rollbackCommand) {
    return null
  }
}
