package com.aestasit.infrastructure.commands

class ExecutionOptions {
  boolean failOnError
  Long maxWait             = 0
  Long succeedOnExitStatus = 0
  File outputFile          = null
  Boolean appendFile       = false
  String prefix            = null
  String suffix            = null
  def escapeCharacters     = null
}
