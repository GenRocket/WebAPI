package com.genRocket

class DomainController {

  def dashboard(String id) {
    String operation = AppConstant.API_URL + "rest/domain/show"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [organizationId: AppConstant.ORG_ID,
                                                                        domainId      : id])

    render(view: "dashboard", model: resp)
  }

  def createAttribute(String id) {
    render(view: "editAttribute", model: [domainId: id])
  }

  def editAttribute(String id, String versionNumber, String externalId) {
    String operation = AppConstant.API_URL + "rest/attribute/show"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [organizationId: AppConstant.ORG_ID, projectName: id, versionNumber: versionNumber,
                                                                    domainId      : externalId])
    resp + [projectName: id, versionNumber: versionNumber]
  }

  def saveAttribute() {
    String operation = AppConstant.API_URL + "rest/attribute/create"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [domainId: params.domainId, oldAttributeName: params.oldAttributeName,
                                                                    name : params.name])
    if (resp.success) {
      redirect(action: "dashboard", params: [id: params.domainId])
    } else {
      render(view: "editAttribute", model: [errors       : resp.errors])
    }
  }

  def deleteAttribute(String id) {
    String operation = AppConstant.API_URL + "rest/attribute/delete"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [organizationId: AppConstant.ORG_ID,
                                                                      domainId      : params.id, name: params.name])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(action: "dashboard", params: [id: id])
  }

  def previewData() {
    String operation = AppConstant.API_URL + "rest/domain/preview"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [domainId: params.id, loopCount: 10])
    render(template: "previewData", model: resp)
  }
}
