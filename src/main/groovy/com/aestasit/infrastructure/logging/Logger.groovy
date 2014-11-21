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

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

/**
 * Slf4j-based logger.
 *
 * @author Andrey Adamovich
 *
 */
@CompileStatic
@TypeChecked
interface Logger {

  // Command execution lifecycle messages.
  void commandOutput(String line)
  void commandOutput(String[] lines)
  void commandErrorOutput(String line)
  void commandErrorOutput(String[] lines)
  void commandInput(String message)

  // File operation messages.
  void uploadStart(String local, String remote)
  void uploadProgress(String local, String remote, int progress)
  void uploadEnd(String local, String remote)
  void downloadStart(String remote, String local)
  void downloadProgress(String remote, String local, int progress)
  void downloadEnd(String remote, String local)

  // Generic logging messages.
  void info(String message)
  void warn(String message)
  void debug(String message)

}
