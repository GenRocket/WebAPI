package com.genRocket

class DomainController {

  def dashboard(String id) {
    String domainDashboard = AppConstant.API_URL + "rest/show/domain"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(domainDashboard, [organizationId: AppConstant.ORG_ID,
                                                                        domainId      : id])

    render(view: "dashboard", model: resp)
  }

  def createAttribute(String id) {
    render(view: "editAttribute", model: [domainId: id])
  }

  def editAttribute(String id, String versionNumber, String externalId) {
    String listProject = AppConstant.API_URL + "rest/show/attribute"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [organizationId: AppConstant.ORG_ID, projectName: id, versionNumber: versionNumber,
                                                                    domainId      : externalId])
    resp + [projectName: id, versionNumber: versionNumber]
  }

  def saveAttribute() {
    String listProject = AppConstant.API_URL + "rest/create/attribute"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [domainId: params.domainId, oldAttributeName: params.oldAttributeName,
                                                                    name : params.name])
    if (resp.success) {
      redirect(action: "dashboard", params: [id: params.domainId])
    } else {
      render(view: "editAttribute", model: [errors       : resp.errors])
    }
  }

  def deleteAttribute(String id) {
    String deleteProject = AppConstant.API_URL + "rest/delete/attribute"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(deleteProject, [organizationId: AppConstant.ORG_ID,
                                                                      domainId      : params.id, name: params.name])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(action: "dashboard", params: [id: id])
  }

  def previewData() {
    String listProject = AppConstant.API_URL + "rest/preview/domain"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(listProject, [domainId: params.id, loopCount: 10])
    render(template: "previewData", model: resp)
  }
}
