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
package com.aestasit.infrastructure.logging

import com.aestasit.infrastructure.commands.RemoteCommand
import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

/**
 * ANSI colored standard system output logger.
 *
 * @author Andrey Adamovich
 *
 */
@CompileStatic
@TypeChecked
class AnsiSysOutEventLogger extends BaseEventLogger {

  // TODO: implement

  @Override
  void connected(String host) {

  }

  @Override
  void disconnected(String host) {

  }

  @Override
  void commandStart(RemoteCommand command) {

  }

  @Override
  void commandOutput(String line) {

  }

  @Override
  void commandErrorOutput(String line) {

  }

  @Override
  void commandInput(String message) {

  }

  @Override
  void commandTimeout(RemoteCommand command) {

  }

  @Override
  void commandFinish(RemoteCommand command) {

  }

  @Override
  void uploadStart(String local, String remote) {

  }

  @Override
  void uploadProgress(String local, String remote, int progress) {

  }

  @Override
  void uploadEnd(String local, String remote) {

  }

  @Override
  void downloadStart(String remote, String local) {

  }

  @Override
  void downloadProgress(String remote, String local, int progress) {

  }

  @Override
  void downloadEnd(String remote, String local) {

  }

  @Override
  void info(String message) {

  }

  @Override
  void warn(String message) {

  }

  @Override
  void exception(String message, Throwable exception) {

  }

  @Override
  void debug(String message) {

  }
}
