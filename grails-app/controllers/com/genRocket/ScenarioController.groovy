package com.genRocket

class ScenarioController {

  def delete(String id) {
    String operation = AppConstant.API_URL + "rest/scenario/delete"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [scenarioId: params.scenarioId])
    if (!resp.success) {
      flash.error = resp.errors
    }
    redirect(controller: "home", action: "showProjectArtifacts", params: [id: id, versionNumber: params.versionNumber])
  }

  def download(String id) {
    String validateScenarioOpr = AppConstant.API_URL + "rest/scenario/validate"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(validateScenarioOpr, [scenarioId: params.scenarioId])
    if (resp.success) {
      String downloadScenarioOpr = AppConstant.API_URL + "rest/scenario/download"
      File file = AppUtil.makeRequestAndDownloadResponse(downloadScenarioOpr, [scenarioId: params.scenarioId])

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
    String operation = AppConstant.API_URL + "rest/scenario/show"
    Map resp = AppUtil.makeRequestAndRetrieveResponse(operation, [organizationId: AppConstant.ORG_ID,
                                                                        scenarioId    : id])

    render(view: "dashboard", model: resp)
  }

}
