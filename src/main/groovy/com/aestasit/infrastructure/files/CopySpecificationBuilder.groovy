package com.aestasit.infrastructure.files

import static groovy.lang.Closure.DELEGATE_FIRST

class CopySpecificationBuilder {

  private final FileSetBuilder source = new FileSetBuilder()
  private final FileSetBuilder target = new FileSetBuilder()

  def from(@DelegatesTo(strategy = DELEGATE_FIRST, value = FileSetBuilder) Closure cl) {
    cl.delegate = source
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
  }

  def into(@DelegatesTo(strategy = DELEGATE_FIRST, value = FileSetBuilder) Closure cl) {
    cl.delegate = target
    cl.resolveStrategy = DELEGATE_FIRST
    cl()
  }

  FileSetBuilder getSource() {
    source
  }

  FileSetBuilder getTarget() {
    target
  }

}
