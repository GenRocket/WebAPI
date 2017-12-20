package com.genRocket

import grails.util.Holders
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class AppUtil {


  static Map makeRequestAndRetrieveResponse(String url, Map requestMap, Boolean setHeader = true) {
    try {
      HTTPBuilder hTTPBuilder = new HTTPBuilder(url)
      hTTPBuilder.ignoreSSLIssues()
      Map jsonResp = [:]

      hTTPBuilder.request(Method.POST, ContentType.JSON) {
        body = requestMap

        if (selectedToken() && setHeader) {
          headers = ["X-Auth-Token": selectedToken()]
        }

        response.success = { resp, json ->
          jsonResp = json as Map
        }

        response.failure = { resp ->
          println "Request failed with status ${resp.status}"
        }
      }

      if (!jsonResp) {
        Holders.grailsApplication.config.loginToken = null
        //redirect may be!
        jsonResp = [:]
      }

      return jsonResp
    } catch (Exception ex) {
      return [success: false, errorMessage: ex.message]
    }
  }

  static def selectedToken() {
    return Holders.grailsApplication.config.loginToken
    //getSession().getAttribute("loginToken")
  }

  static def saveToken(String accessToken) {
    Holders.grailsApplication.config.loginToken = accessToken
    //getSession().setAttribute("loginToken", accessToken)
  }

}
