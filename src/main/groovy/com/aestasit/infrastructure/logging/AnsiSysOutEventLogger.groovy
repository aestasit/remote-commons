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
