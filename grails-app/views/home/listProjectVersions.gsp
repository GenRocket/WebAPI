<html>
<head>
  <title>GenRocket - Project Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<ul class="breadcrumb">
  <li><g:link controller="home" action="dashboard">Project Dashboard</g:link><span class="divider"></span></li>
</ul>

<div class="container">

  <div class="hpanel">
    <div class="panel-heading">
      <h4 class="font-bold">Project Versions (${project?.projectVersions?.size()}) of Project ${project?.name}</h4>
    </div>

    <div class="panel-body">
      <div class="col-lg-12">
        <g:if test="${flash.message}">
          <div class="alert alert-success">
            ${flash.message}
          </div>
        </g:if>
        <g:if test="${flash.error}">
          <div class="alert alert-danger">
            ${flash.error}
          </div>
        </g:if>

        <table cellpadding="1" cellspacing="1" class="table table-condensed table-bordered">
          <thead>
          <tr>
            <th>Version</th>
            <th>Description</th>
            <th class="text-center">Actions</th>
          </tr>
          </thead>
          <tbody>
          <g:each in="${project?.projectVersions}" var="projectVersion">
            <tr>
              <td>${projectVersion.versionNumber}
              </td>
              <td>${projectVersion.description}</td>
              <td class="text-center">
                <g:link controller="home" action="showProjectArtifacts"
                        params="[id: project.name, versionNumber: projectVersion.versionNumber]"><i
                    class="pe-7s-menu"></i></g:link>
                <g:link controller="home" action="editProjectVersion"
                        params="[id: project.name, versionNumber: projectVersion.versionNumber]"><i
                    class="pe-7s-pen"></i></g:link>
              </td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
    </div>

    <div class="panel-footer text-center">
      <g:link controller="home" action="createProjectVersion" id="${project?.name}"
              class="btn btn-success btn-sm">New Project Version</g:link>
    </div>
  </div>
</div>
</body>
</html>
