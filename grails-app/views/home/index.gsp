<html>
<head>
  <title>GenRocket - Login</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<div class="login-container">
  <div class="row">
    <div class="col-md-12">
      <div class="text-center m-b-md">
        <h3>GenRocket Web API App</h3>
      </div>

      <div class="hpanel">
        <g:if test="${errorMessage}">
          <div class="alert alert-danger">
            ${errorMessage}
          </div>
        </g:if>
        <div class="panel-body">
          <g:form controller="home" action="login" name="loginForm">
            <div class="form-group">
              <label class="control-label" for="username">Username</label>
              <input type="text" placeholder="example@gmail.com" title="Please enter you username" required="" value=""
                     name="username" id="username" class="form-control">
            </div>

            <div class="form-group">
              <label class="control-label" for="password">Password</label>
              <input type="password" title="Please enter your password" placeholder="******" required="" value=""
                     name="password" id="password" class="form-control">
            </div>

            <button class="btn btn-success btn-block">Login</button>
          </g:form>
        </div>
      </div>
    </div>
  </div>

</div>
</body>
</html>
