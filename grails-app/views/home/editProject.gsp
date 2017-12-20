<html>
<head>
  <title>GenRocket - Edit Project</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<div class="login-container">
  <div class="row">
    <div class="col-md-12">
      <div class="text-center m-b-md">
        <h3>Manage Project</h3>
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
          <g:form controller="home" action="saveProject" name="loginForm">
            <div class="form-group">
              <label class="control-label" for="name">Project Name</label>
              <input type="text" required="" value="${project?.name}"
                     name="name" id="name" class="form-control">
            </div>
            <g:hiddenField name="oldName" value="${project ? project.name : ''}"/>

            <div class="form-group">
              <label class="control-label" for="description">Description</label>
              <textarea
                  name="description" id="description" class="form-control">${project?.description}</textarea>
            </div>

            <button class="btn btn-success">Save Project</button>
          </g:form>
        </div>
      </div>
    </div>
  </div>

</div>
</body>
</html>
