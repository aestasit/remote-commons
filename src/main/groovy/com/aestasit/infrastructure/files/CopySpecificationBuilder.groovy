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
class CopySpecificationBuilder {

  private final FileSetBuilder source = new FileSetBuilder()
  private final FileSetBuilder target = new FileSetBuilder()

  static copy(@DelegatesTo(strategy = DELEGATE_FIRST, value = CopySpecificationBuilder) Closure cl) {
    CopySpecificationBuilder builder = new CopySpecificationBuilder()
    cl.delegate = builder
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    builder
  }

  FileSetBuilder from(@DelegatesTo(strategy = DELEGATE_FIRST, value = FileSetBuilder) Closure cl) {
    cl.delegate = source
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    source
  }

  FileSetBuilder into(@DelegatesTo(strategy = DELEGATE_FIRST, value = FileSetBuilder) Closure cl) {
    cl.delegate = target
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
    target
  }

  FileSetBuilder getSource() {
    source
  }

  FileSetBuilder getTarget() {
    target
  }

}
