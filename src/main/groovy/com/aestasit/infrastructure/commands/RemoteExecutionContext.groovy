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
