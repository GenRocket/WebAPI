package com.genRocket

class ScenarioController {

  def delete(String id) {
    String deleteScenario = AppConstant.API_URL + "rest/scenario/delete"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(deleteScenario, [scenarioId: params.scenarioId])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(controller: "home", action: "showProjectArtifacts", params: [id: id, versionNumber: params.versionNumber])
  }

  def download(String id) {
    String validateScenario = AppConstant.API_URL + "rest/scenario/validate"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(validateScenario, [scenarioId: params.scenarioId])
    if (resp.success) {
      String downloadScenario = AppConstant.API_URL + "rest/scenario/download"
      File file = AppUtil.makeRequestAndDownloadResponse(downloadScenario, [scenarioId: params.scenarioId])

      if (file) {
        response.setHeader("Content-disposition", "attachment; filename=${file.name}")
        response.contentType = "application/x-zip-compressed"
        response.outputStream << file.getBytes()
        file.delete()
      } else {
        flash.error = "An Error Occurred"
        redirect(controller: "home", action: "showProjectArtifacts", params: [id: id, versionNumber: params.versionNumber])
      }
    } else {
      flash.error = resp.errors
      redirect(controller: "home", action: "showProjectArtifacts", params: [id: id, versionNumber: params.versionNumber])
    }

  }

  def dashboard(String id) {
    String domainDashboard = AppConstant.API_URL + "rest/scenario/show"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(domainDashboard, [organizationId: AppConstant.ORG_ID,
                                                                        scenarioId    : id])

    render(view: "dashboard", model: resp)
  }

}
