/*
 * Copyright (C) 2011-2014 Aestas/IT
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
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.slf4j.LoggerFactory

/**
 * Slf4j-based logger.
 *
 * @author Andrey Adamovich
 *
 */
@CompileStatic
@TypeChecked
class Slf4jEventLogger implements EventLogger {

  private org.slf4j.Logger logger

  Slf4jEventLogger() {
    logger = LoggerFactory.getLogger(getClass().getPackage().getName())
  }

  Slf4jEventLogger(org.slf4j.Logger logger) {
    this.logger = logger
  }

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
    logger.info(line)
  }

  @Override
  void commandOutput(String[] lines) {
    lines.each { String line ->
      logger.info(line)
    }
  }

  @Override
  void commandErrorOutput(String line) {
    logger.warn(line)
  }

  @Override
  void commandErrorOutput(String[] lines) {
    lines.each { String line ->
      logger.warn(line)
    }
  }

  @Override
  void commandInput(String message) {
    logger.info("${message}:")
  }

  @Override
  void commandTimeout(RemoteCommand command) {

  }

  @Override
  void commandFinish(RemoteCommand command) {

  }

  @Override
  void uploadStart(String local, String remote) {
    logger.info("${local} -> ${remote}")
  }

  @Override
  void uploadProgress(String local, String remote, int progress) {
    // DO NOTHING.
  }

  @Override
  void uploadEnd(String local, String remote) {
    logger.info('Finished.')
  }

  @Override
  void downloadStart(String remote, String local) {
    logger.info("${local} <- ${remote}")
  }

  @Override
  void downloadProgress(String remote, String local, int progress) {
    // DO NOTHING.
  }

  @Override
  void downloadEnd(String remote, String local) {
    logger.info('Finished.')
  }

  @Override
  void info(String message) {
    logger.info(message)
  }

  @Override
  void warn(String message) {
    logger.warn(message)
  }

  @Override
  void exception(String message, Throwable exception) {

  }

  @Override
  void debug(String message) {
    logger.debug(message)
  }
}
