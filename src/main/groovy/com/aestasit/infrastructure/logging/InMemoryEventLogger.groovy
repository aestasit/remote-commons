package com.aestasit.infrastructure.logging

import com.aestasit.infrastructure.commands.RemoteCommand
import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

/**
 * In-memory logging event storage.
 *
 * @author Andrey Adamovich
 *
 */
@Canonical
@CompileStatic
@TypeChecked
class InMemoryEventLogger implements EventLogger {

  @Canonical
  static class Event {
    String eventType
    String message
  }

  // TODO: change for concurrent implementation. Queue?
  List<Event> messages = new ArrayList<Event>()

  private Event event(String eventType, String message) {
    messages.add(new Event(eventType: eventType, message: message))
  }

  @Override
  void connected(String host) {
    event('connected', host)
  }

  @Override
  void disconnected(String host) {
    event('disconnected', host)
  }

  @Override
  void commandStart(RemoteCommand command) {

  }

  @Override
  void commandOutput(String line) {
    event('commandOutput', line)
  }

  @Override
  void commandOutput(String[] lines) {
    lines.each { String line ->
      commandOutput(line)
    }
  }

  @Override
  void commandErrorOutput(String line) {
    event('commandErrorOutput', line)
  }

  @Override
  void commandErrorOutput(String[] lines) {
    lines.each { String line ->
      commandErrorOutput(line)
    }
  }

  @Override
  void commandInput(String message) {
    event('commandInput', message)
  }

  @Override
  void commandTimeout(RemoteCommand command) {

  }

  @Override
  void commandFinish(RemoteCommand command) {

  }

  @Override
  void uploadStart(String local, String remote) {
    event('uploadStart', "${local} to ${remote}")
  }

  @Override
  void uploadProgress(String local, String remote, int progress) {
    event('uploadProgress', "${progress}% of ${local} to ${remote}")
  }

  @Override
  void uploadEnd(String local, String remote) {
    event('uploadEnd', "${local} to ${remote}")
  }

  @Override
  void downloadStart(String remote, String local) {
    event('downloadStart', "${remote} to ${local}")
  }

  @Override
  void downloadProgress(String remote, String local, int progress) {
    event('downloadProgress', "${progress}% of ${remote} to ${local}")
  }

  @Override
  void downloadEnd(String remote, String local) {
    event('downloadEnd', "${remote} to ${local}")
  }

  @Override
  void info(String message) {
    event('info', message)
  }

  @Override
  void warn(String message) {
    event('warn', message)
  }

  @Override
  void exception(String message, Throwable exception) {

  }

  @Override
  void debug(String message) {
    event('debug', message)
  }

}
