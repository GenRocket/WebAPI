package webapi

class LoginFilters {

  def filters = {
    all(controller: 'home', action: '*') {
      before = {
        if (actionName != "index" && actionName != "login") {
          if (session.getAttribute("accessToken") != null) {
            redirect(controller: "home", action: "index")
            return false
          }
        }
      }
      after = { Map model ->

      }
      afterView = { Exception e ->

      }
    }
  }
}
