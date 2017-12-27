package com.genRocket

class HomeController {

  def index() {
    if (AppUtil.selectedToken()) {
      redirect(action: "dashboard")
    }
  }

  def logout() {
    AppUtil.saveToken(null)
    redirect(action: "index")
  }

  def login(String username, String password) {
    String loginURL = AppConstant.API_URL + "rest/login"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(loginURL, [username: username, password: password], false)

    String loginToken = resp.accessToken
    if (loginToken) {
      AppUtil.saveToken(loginToken)
    } else {
      render(view: "index", model: [errorMessage: "Invalid UserName and password combination"])
      return
    }

    redirect(action: "dashboard")
  }

  def dashboard() {
    String listProject = AppConstant.API_URL + "rest/list/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID])
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
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id,
                                                                    versionNumber : params.versionNumber])
    resp + [projectName: id]
  }

  def saveProjectVersion() {
    String oldVersionNumber = params.oldVersionNumber
    String listProject = AppConstant.API_URL + "rest/create/projectVersion"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId  : AppConstant.ORG_ID, projectName: params.projectName,
                                                                    oldVersionNumber: oldVersionNumber,
                                                                    versionNumber   : params.versionNumber, description: params.description])
    if (resp.success) {
      redirect(action: "listProjectVersions", params: [id: params.projectName])
    } else {
      render(view: "editProjectVersion", model: [errors        : resp.errors, projectName: params.projectName,
                                                 projectVersion: [versionNumber: oldVersionNumber,
                                                                  description  : params.description]])
    }
  }

  def createDomain(String id, String versionNumber) {
    render(view: "editDomain", model: [projectName: id, versionNumber: versionNumber])
  }

  def editDomain(String id, String versionNumber, String externalId) {
    String listProject = AppConstant.API_URL + "rest/show/domain"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id, versionNumber: versionNumber,
                                                                    domainId      : externalId])
    resp + [projectName: id, versionNumber: versionNumber]
  }

  def saveDomain() {
    String listProject = AppConstant.API_URL + "rest/create/domain"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: params.projectName,
                                                                    versionNumber : params.versionNumber, name: params.name, externalId: params.externalId,
                                                                    description   : params.description])
    if (resp.success) {
      redirect(action: "showProjectArtifacts", params: [id: params.projectName, versionNumber: params.versionNumber])
    } else {
      render(view: "editDomain", model: [errors       : resp.errors, projectName: params.projectName,
                                         versionNumber: params.versionNumber,
                                         domain       : [name       : params.name,
                                                         description: params.description]])
    }
  }

  def deleteDomain(String id) {
    String deleteProject = AppConstant.API_URL + "rest/delete/domain"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(deleteProject, [organizationId: AppConstant.ORG_ID,
                                                                      domainId      : params.domainId])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(action: "showProjectArtifacts", params: [id: id, versionNumber: params.versionNumber])
  }

  def lockProject(String id) {
    String listProject = AppConstant.API_URL + "rest/lock/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    if (resp.success) {
      flash.message = "Locked the project ${id}"
      redirect(action: "dashboard")
    }
  }

  def unLockProject(String id) {
    String listProject = AppConstant.API_URL + "rest/unLock/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    if (resp.success) {
      flash.message = "UnLocked the project ${id}"
      redirect(action: "dashboard")
    }
  }

  def editProject(String id) {
    String listProject = AppConstant.API_URL + "rest/show/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    resp
  }

  def deleteProject(String id) {
    String deleteProject = AppConstant.API_URL + "rest/delete/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(deleteProject, [organizationId: AppConstant.ORG_ID,
                                                                      projectName   : id])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(action: "dashboard")
  }

  def saveProject() {
    String oldName = params.oldName
    String listProject = AppConstant.API_URL + "rest/create/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, oldName: oldName,
                                                                    name          : params.name, description: params.description])
    if (resp.success) {
      redirect(action: "dashboard")
    } else {
      render(view: "editProject", model: [errors: resp.errors, project: [name: oldName, description: params.description]])
    }
  }

  def listProjectVersions(String id) {
    String listProject = AppConstant.API_URL + "rest/show/project"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id])
    resp
  }

  def showProjectArtifacts() {
    String listDomains = AppConstant.API_URL + "rest/list/domain"
    String listScenarios = AppConstant.API_URL + "rest/list/scenario"
    String listScenarioChains = AppConstant.API_URL + "rest/list/chain"
    Map requestMap = [organizationId: AppConstant.ORG_ID, projectName: params.id, versionNumber: params.versionNumber]
    Map domains = AppUtil.makeRequestAndRetrieveResponse(listDomains, requestMap)
    Map scenarios = AppUtil.makeRequestAndRetrieveResponse(listScenarios, requestMap)
    Map chains = AppUtil.makeRequestAndRetrieveResponse(listScenarioChains, requestMap)
    [domains: domains, scenarios: scenarios, chains: chains, projectName: params.id, versionNumber: params.versionNumber]
  }

}
