<html>
<head>
  <title>GenRocket - Project Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">

<div class="color-line"></div>

<ul class="breadcrumb">
  <li><g:link controller="home" action="dashboard">Project Dashboard</g:link><span class="divider"></span></li>
</ul>

<div class="container">

  <div class="col-lg-12">
    <g:if test="${flash.message}">
      <div class="alert alert-success">
        ${flash.message}
      </div>
    </g:if>
    <h4 class="font-bold">Project Versions of Project ${project?.name}</h4>

    <table cellpadding="1" cellspacing="1" class="table table-condensed table-bordered">
      <thead>
      <tr>
        <th>Version</th>
        <th>Description</th>
        <th>&nbsp;</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${project?.projectVersions}" var="projectVersion">
        <tr>
          <td>${projectVersion.versionNumber}
          </td>
          <td>${projectVersion.description}</td>
          <td><g:link controller="home" action="showProjectArtifacts" class="btn btn-success"
                      params="[name: project.name, versionNumber: projectVersion.versionNumber]">Show Artifacts</g:link></td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
