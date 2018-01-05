package com.genRocket

import grails.util.Holders
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.commons.io.FileUtils
import org.apache.http.Header

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
      ex.printStackTrace()
      return [success: false, errorMessage: ex.message]
    }
  }

  static File makeRequestAndDownloadResponse(String url, Map requestMap, Boolean setHeader = true) {
    try {
      File file = null
      HTTPBuilder hTTPBuilder = new HTTPBuilder(url)
      hTTPBuilder.ignoreSSLIssues()
      Map jsonResp = [:]

      hTTPBuilder.request(Method.POST, ContentType.BINARY) {
        body = requestMap
        requestContentType = ContentType.JSON

        if (selectedToken() && setHeader) {
          headers = ["X-Auth-Token": selectedToken()]
        }

        response.success = { resp, inputStream ->
          String fileName = null
          List<Header> responseHeader = resp.getHeaders("Content-Disposition");
          fileName = responseHeader.first().getValue()
          fileName = fileName ? fileName.split("filename=")?.last() : fileName
          file = new File(fileName)
          FileUtils.copyInputStreamToFile(inputStream, file)
        }

        response.failure = { resp ->
          println "Request failed with status ${resp.status}"
        }
      }

      return file
    } catch (Exception ex) {
      println ex.message
      return null
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
