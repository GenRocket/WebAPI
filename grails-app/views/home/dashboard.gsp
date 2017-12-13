<html>
<head>
  <title>GenRocket - Project Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">

<div class="color-line"></div>

<div class="container">

  <div class="col-lg-12">
    <g:if test="${flash.message}">
      <div class="alert alert-success">
        ${flash.message}
      </div>
    </g:if>
    <h4 class="font-bold">Projects</h4>

    <table cellpadding="1" cellspacing="1" class="table table-condensed table-bordered">
      <thead>
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Locked</th>
        <th width="25%">&nbsp;</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${projects}" var="project">
        <tr>
          <td><a href="${createLink(controller: "home", action: "listProjectVersions", id: project?.name)}">${project.name}</a></td>
          <td>${project.description}</td>
          <td>${project.locked ? "Yes" : "No"}</td>

          <td>
            <g:link controller="home" class="btn btn-success" action="editProject"
                    id="${project.name}">Edit Project</g:link>
            <g:if test="${project.locked}">
              <g:link controller="home" class="btn btn-warning" action="unLockProject"
                      id="${project.name}">UnLock Project</g:link>
            </g:if><g:else>
              <g:link controller="home" class="btn btn-warning2" action="lockProject"
                      id="${project.name}">Lock Project</g:link>
            </g:else>
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
