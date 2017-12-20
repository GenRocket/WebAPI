package com.genRocket

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class HomeController {

  def index() {
    if (selectedToken()) {
      redirect(action: "dashboard")
    }
  }

  def logout() {
    saveToken(null)
    redirect(action: "index")
  }

  def login(String username, String password) {
    String loginURL = AppConstant.API_URL + "api/login"
    Map resp = makeRequestAndRetrieveResponse(loginURL, [username: username, password: password], false)

    String loginToken = resp.access_token
    if (loginToken) {
      saveToken(loginToken)
    } else {
      render(view: "index", model: [errorMessage: "Invalid UserName and password combination"])
      return
    }

    redirect(action: "dashboard")
  }

  def dashboard() {
    String listProject = AppConstant.API_URL + "rest/list/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID])
    [projects: resp?.projects]
  }

  def createProject() {
    render(view: "editProject")
  }

  def createProjectVersion(String id) {
    render(view: "editProjectVersion", model: [projectName: id])
  }

  def editProjectVersion(String id) {
    String listProject = AppConstant.API_URL + "rest/show/projectVersion"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id,
                                                            versionNumber : params.versionNumber])
    resp + [projectName: id]
  }

  def saveProjectVersion() {
    String oldVersionNumber = params.oldVersionNumber
    String listProject = AppConstant.API_URL + "rest/create/projectVersion"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId  : AppConstant.ORG_ID, projectName: params.projectName,
                                                            oldVersionNumber: oldVersionNumber,
                                                            versionNumber   : params.versionNumber, description: params.description])
    if (resp.success) {
      redirect(action: "listProjectVersions", params: [id: params.projectName])
    } else {
      render(view: "editProjectVersion", model: [errors: resp.errors, projectVersion: [versionNumber: oldVersionNumber,
                                                                                       description  : params.description]])
    }
  }

  def lockProject(String id) {
    String listProject = AppConstant.API_URL + "rest/lock/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    if (resp.success) {
      flash.message = "Locked the project ${id}"
      redirect(action: "dashboard")
    }
  }

  def unLockProject(String id) {
    String listProject = AppConstant.API_URL + "rest/unLock/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    if (resp.success) {
      flash.message = "UnLocked the project ${id}"
      redirect(action: "dashboard")
    }
  }

  def editProject(String id) {
    String listProject = AppConstant.API_URL + "rest/show/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    resp
  }

  def deleteProject(String id) {
    String deleteProject = AppConstant.API_URL + "rest/delete/project"
    Map resp = makeRequestAndRetrieveResponse(deleteProject, [organizationId: AppConstant.ORG_ID,
                                                              projectName   : id])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(action: "dashboard")
  }

  def saveProject() {
    String oldName = params.oldName
    String listProject = AppConstant.API_URL + "rest/create/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, oldName: oldName,
                                                            name          : params.name, description: params.description])
    if (resp.success) {
      redirect(action: "dashboard")
    } else {
      render(view: "editProject", model: [errors: resp.errors, project: [name: oldName, description: params.description]])
    }
  }

  def listProjectVersions(String id) {
    String listProject = AppConstant.API_URL + "rest/show/project"
    Map resp = makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    resp
  }

  def showProjectArtifacts() {
    String listDomains = AppConstant.API_URL + "rest/list/domain"
    String listScenarios = AppConstant.API_URL + "rest/list/scenario"
    String listScenarioChains = AppConstant.API_URL + "rest/list/chain"
    Map requestMap = [organizationId: AppConstant.ORG_ID, projectName: params.name, versionNumber: params.versionNumber]
    Map domains = makeRequestAndRetrieveResponse(listDomains, requestMap)
    Map scenarios = makeRequestAndRetrieveResponse(listScenarios, requestMap)
    Map chains = makeRequestAndRetrieveResponse(listScenarioChains, requestMap)
    [domains: domains, scenarios: scenarios, chains: chains, projectName: params.name, versionNumber: params.versionNumber]
  }

  private Map makeRequestAndRetrieveResponse(String url, Map requestMap, Boolean setHeader = true) {
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
        grailsApplication.config.loginToken = null
        //redirect may be!
      }

      return jsonResp
    } catch (Exception ex) {
      return [success: false, errorMessage: ex.message]
    }
  }

  private def selectedToken() {
    return grailsApplication.config.loginToken
    //getSession().getAttribute("loginToken")
  }

  def saveToken(String accessToken) {
    grailsApplication.config.loginToken = accessToken
    //getSession().setAttribute("loginToken", accessToken)
  }

}
