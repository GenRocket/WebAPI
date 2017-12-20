<html>
<head>
  <title>GenRocket - Project Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<div class="container">

  <div class="col-lg-12">
    <g:if test="${flash.message}">
      <div class="alert alert-success">
        ${flash.message}
      </div>
    </g:if>
    <h4 class="font-bold">Projects</h4>

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
                      id="${project.name}"><i class="pe-7s-lock"></i></g:link>
            </g:if><g:else>
            <g:link controller="home" action="lockProject"
                    id="${project.name}"><i class="pe-7s-unlock"></i></g:link>
          </g:else>
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $("#projectTable").dataTable({
      "bDestroy": true,
      "sDom": "<''<'span4'l><'span4'f>r>t<''<'span4'i><'span4'p>>",
      "bPaginate": true,
      "bLengthChange": false,
      "iDisplayLength": 20,
      "bFilter": false,
      "bSort": false,
      "bInfo": false,
      "bAutoWidth": false
    });
  });
</script>
</body>
</html>
