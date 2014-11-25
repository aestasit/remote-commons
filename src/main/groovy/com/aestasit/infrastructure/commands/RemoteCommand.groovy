package com.aestasit.infrastructure.commands

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked


@CompileStatic
@TypeChecked
@Canonical
class RemoteCommand {

  String program
  List<Argument> arguments = new ArrayList<Argument>()

  void program(String program) {
    this.program = program
  }

  void argument(String argument) {
    arguments.add(new Argument())
  }

}
