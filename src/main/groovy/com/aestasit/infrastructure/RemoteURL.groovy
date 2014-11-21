package com.aestasit.infrastructure

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import java.util.regex.Pattern

@CompileStatic
@TypeChecked
@Canonical
class RemoteURL {

  String host
  Integer port

  String user
  String password

  private static final Pattern URL_PATTERN = ~/^(([^:@]+)(:([^@]+))?@)?([^:@]+)(:(\d+))?$/

  RemoteURL(String url, int defaultPort = 22) {
    def matcher = URL_PATTERN.matcher(url)
    if (matcher.matches()) {
      host = matcher.group(5)
      if (matcher.group(7)) {
        port = matcher.group(7).toInteger()
      } else {
        port = defaultPort
      }
      user = matcher.group(2)
      password = matcher.group(4)
    } else {
      throw new MalformedURLException("Unknown URL format: " + url)
    }
  }

}
