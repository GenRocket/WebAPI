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
        <h3>Manage Attribute</h3>
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
          <g:form controller="domain" action="saveAttribute" name="loginForm">
            <div class="form-group">
              <label class="control-label" for="name">Name:</label>
              <g:hiddenField name="domainId" value="${domainId}"/>
              <input type="text" required="" value="${attribute?.name}"
                     name="name" id="name" class="form-control">
            </div>
            <g:hiddenField name="oldAttributeName" value="${attribute ? attribute.name : ''}"/>

            <button class="btn btn-success">Save</button>
          </g:form>
        </div>
      </div>
    </div>
  </div>

</div>
</body>
</html>
