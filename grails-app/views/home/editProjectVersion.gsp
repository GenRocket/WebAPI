<html>
<head>
  <title>GenRocket - Edit Project Version</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<div class="login-container">
  <div class="row">
    <div class="col-md-12">
      <div class="text-center m-b-md">
        <h3>Manage Project Version</h3>
      </div>

      <div class="hpanel">
        <g:if test="${errors}">
          <div class="alert alert-danger">
            <g:each in="${errors}" var="error">
              ${error.value}
            </g:each>
          </div>
        </g:if>
        <div class="panel-body">
          <g:form controller="home" action="saveProjectVersion" name="loginForm">
            <div class="form-group">
              <label class="control-label" for="versionNumber">Version Number</label>
              <g:hiddenField name="projectName" value="${projectName}"/>
              <input type="text" required="" value="${projectVersion?.versionNumber}"
                     name="versionNumber" id="versionNumber" class="form-control">
            </div>
            <g:hiddenField name="oldVersionNumber" value="${projectVersion ? projectVersion.versionNumber : ''}"/>

            <div class="form-group">
              <label class="control-label" for="description">Description</label>
              <textarea
                  name="description" id="description" class="form-control">${projectVersion?.description}</textarea>
            </div>

            <button class="btn btn-success">Save</button>
          </g:form>
        </div>
      </div>
    </div>
  </div>

</div>
</body>
</html>
