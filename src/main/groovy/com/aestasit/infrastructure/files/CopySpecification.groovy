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
