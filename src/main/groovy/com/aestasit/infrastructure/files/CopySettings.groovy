package com.aestasit.infrastructure.files

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

/**
 * File copy operation settings.
 *
 * @author Andrey Adamovich
 *
 */
@CompileStatic
@TypeChecked
@Canonical
class CopySettings {

  String uploadToDirectory
  String postUploadCommand

}
