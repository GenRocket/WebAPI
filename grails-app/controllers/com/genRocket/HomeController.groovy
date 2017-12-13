package com.genRocket

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class HomeController {

  def index() {
    if (session.loginToken) {
      redirect(action: "dashboard")
    }
  }

  def login(String username, String password) {
    String loginURL = AppConstant.API_URL + "api/login"
    Map resp = makeRequestAndRetrieveResponse(loginURL, [username: username, password: password], false)
    String loginToken = resp.access_token
    if (loginToken) {
      println "Setting access token: ${loginToken}"
      saveToken(loginToken)
    } else {
      render(view: "index", model: [errorMessage: "Invalid UserName and password combination"])
      return
    }
    redirect(action: "dashboard")
  }

  def dashboard() {
    println selectedToken()
    String listProject = AppConstant.API_URL + "rest/list/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID])
    [projects: resp?.projects]
  }

  private Map makeRequestAndRetrieveResponse(String url, Map requestMap, Boolean setHeader = true) {
    HTTPBuilder hTTPBuilder = new HTTPBuilder(url)
    hTTPBuilder.ignoreSSLIssues()
    Map jsonResp = [:]

    hTTPBuilder.request(Method.POST, ContentType.JSON) {
      body = requestMap

      if (session.loginToken && setHeader) {
        headers = ["X-Auth-Token": selectedToken()]
      }

      response.success = { resp, json ->
        jsonResp = json as Map
      }

      response.failure = { resp ->
        println "Request failed with status ${resp.status}"
      }
    }
    return jsonResp
  }

  private def selectedToken() {
    getSession().getAttribute("loginToken")
  }

  def saveToken(String accessToken) {
    getSession().setAttribute("loginToken", accessToken)
  }

}
