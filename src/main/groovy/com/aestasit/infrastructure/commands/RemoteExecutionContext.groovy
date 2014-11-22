package com.aestasit.infrastructure.commands

interface RemoteExecutionContext {

  RemoteCommandOutput execute(String command)
  RemoteCommandOutput execute(RemoteCommand command)

}
