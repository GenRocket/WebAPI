<html>
<head>
  <title>GenRocket - Project Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<div class="container">

  <div class="hpanel">
    <div class="panel-heading">
      <h4 class="font-bold">Projects (${projects?.size()})</h4>
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
        <table cellpadding="1" cellspacing="1" id="projectTable" class="table table-condensed table-bordered">
          <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Locked</th>
            <th style="text-align: center" width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          <g:each in="${projects}" var="project">
            <tr>
              <td><a
                  href="${createLink(controller: "home", action: "listProjectVersions", id: project?.name)}">${project.name}</a>
              </td>
              <td>${project.description}</td>
              <td>${project.locked ? "Yes" : "No"}</td>

              <td style="text-align: center">
                <g:link controller="home" action="editProject" title="Edit project"
                        id="${project.name}"><i class="pe-7s-pen"></i></g:link>
                <g:if test="${project.locked}">
                  <g:link controller="home" action="unLockProject"
                          id="${project.name}"><i class="fa fa-unlock"></i></g:link>
                </g:if><g:else>
                <g:link controller="home" action="lockProject"
                        id="${project.name}"><i class="fa fa-lock"></i></g:link>
              </g:else>
                <g:link controller="home" action="deleteProject" title="Delete project"
                        id="${project.name}"><i class="pe-7s-trash"></i></g:link>
              </td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
    </div>

    <div class="panel-footer text-center">
      <g:link controller="home" action="createProject" class="btn btn-success btn-sm">New Project</g:link>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $("#projectTable").dataTable({
      "bDestroy": true,
      "sDom": "<''<'span4'l><'span4'f>r>t<''<'span4'i><'span4'p>>",
      "bPaginate": true,
      "bLengthChange": false,
      "iDisplayLength": 15,
      "bFilter": true,
      "bSort": false,
      "bInfo": false,
      "bAutoWidth": false,
      oLanguage: {
        sSearch: ""
      }
    });
  });
</script>
</body>
</html>
