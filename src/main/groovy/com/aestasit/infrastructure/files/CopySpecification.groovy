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
package com.aestasit.infrastructure.files

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import static groovy.lang.Closure.DELEGATE_FIRST

/**
 * File copy specification builder.
 *
 * @author Andrey Adamovich
 *
 */
@CompileStatic
@TypeChecked
class CopySpecification {

  private final FileSet source = new FileSet()
  private final FileSet target = new FileSet()

  static copy(@DelegatesTo(strategy = DELEGATE_FIRST, value = CopySpecification) Closure cl) {
    CopySpecification builder = new CopySpecification()
    cl.delegate = builder
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    builder
  }

  FileSet from(@DelegatesTo(strategy = DELEGATE_FIRST, value = FileSet) Closure cl) {
    cl.delegate = source
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    source
  }

  FileSet into(@DelegatesTo(strategy = DELEGATE_FIRST, value = FileSet) Closure cl) {
    cl.delegate = target
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    target
  }

  FileSet getSource() {
    source
  }

  FileSet getTarget() {
    target
  }

}
