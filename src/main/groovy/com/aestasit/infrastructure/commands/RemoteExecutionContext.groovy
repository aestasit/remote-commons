package com.aestasit.infrastructure.commands

interface RemoteExecutionContext {

  RemoteCommandOutput execute(String command)
  RemoteCommandOutput execute(RemoteCommand command)
  RemoteCommandOutput execute(Closure cl)
  RemoteCommandOutput execute(RemoteCommand command, RemoteCommand rollbackCommand)

//  CommandOutput parallelExec(Collection<String> cmds) {
//    // TODO: implement
//    null
//  }

  // get facts
  // isUbuntu, isRedhat, isCentOS
  // remoteDate()
  // remoteTimestamp()

  // get environment variables
  // remoteEnv

  // file/dir listing
  // remoteFile().eachFile { RemoteFile ->
  //
  // }

  // command output stream??

  // def $(String name) {
  //
  // }

  // add define blocks
  // defineFact('currentDirEmpty', 'ls -la | wc -l', BOOLEAN, NOT_CACHED)
  // if ($('currentDirEmpty')) {
  //
  // }
  // defineFact('abc') {
  //
  //   return 'true'
  // }
  // defineBlock('ensureRunning') { String service ->
  //   exec "/sbin/service $service start"
  // }
  // run 'ensureRunning', 'httpd'
  //
  // defineResource('service') {
  //   parameter 'enabled', Boolean, true
  //   exists {
  //     exec "/sbin/service $('name') status"
  //   }
  //   create {
  //     exec "/sbin/service $('name') start"
  //   }
  //   valid {
  //     exec "/sbin/service $('name') status"
  //   }
  //   ensure {
  //     exec "/sbin/service $('name') start"
  //   }
  //   remove {
  //
  //   }
  // }
  // resource('service', 'httpd') {
  //   enable = true
  // }
  // removeResource('service', 'httpd')
  //

  // add rollback
  // TransactionResult transaction(Closure cl) {
  //
  // }.onComplete {
  //
  // }.onFailure {
  //
  // }

}
