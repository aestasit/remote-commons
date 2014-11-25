package com.aestasit.infrastructure.commands

interface RemoteExecutionContext {

  RemoteCommandOutput execute(String command)
  RemoteCommandOutput execute(RemoteCommand command)
  RemoteCommandOutput execute(Closure cl)
  RemoteCommandOutput execute(RemoteCommand command, RemoteCommand rollbackCommand)




}
