package webapi

import com.genRocket.AppConstant
import com.genRocket.AppUtil

class ApiTagLib {

  static namespace = "api"

  def globalVariables = { attr, body ->
    String domainId = attr.domainId
    String globalVariableList = AppConstant.API_URL + "rest/list/globalVariable"

    Map response = AppUtil.makeRequestAndRetrieveResponse(globalVariableList, [domainId: domainId])
    out << g.render(template: "/domain/globalVariables", model: response)
  }

  def domainReceivers = { attr, body ->
    String domainId = attr.domainId
    String domainReceiverList = AppConstant.API_URL + "rest/list/domainReceiver"

    Map response = AppUtil.makeRequestAndRetrieveResponse(domainReceiverList, [domainId: domainId])
    out << g.render(template: "/domain/domainReceivers", model: response)
  }

  def truncateText = { attr ->
    String text = attr['text']
    int maxChars = Integer.parseInt(attr['maxChars'])
    if (text?.length() <= maxChars) {
      out << text
    } else {
      int maxFit = maxChars - 3
      int truncateAt = text.lastIndexOf(' ', maxFit)
      if (truncateAt == -1 || truncateAt < maxChars / 2) {
        truncateAt = maxFit
      }
      out << text.substring(0, truncateAt) + " <span class='ellipse-help' data-trigger='hover' data-title='Description' data-content=\"${text}\" style='text-decoration: underline; cursor: pointer;'>...</span>"
    }
  }
}
