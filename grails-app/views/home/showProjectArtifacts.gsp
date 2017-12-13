<html>
<head>
  <title>GenRocket - Project Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<ul class="breadcrumb">
  <li><g:link controller="home" action="dashboard">Project Dashboard</g:link><span class="divider"></span></li>
  <li><g:link controller="home" action="listProjectVersions" params="[id: projectName]">Project Version</g:link> <span
      class="divider"></span></li>
</ul>

<div class="col-lg-12">
  <g:if test="${flash.message}">
    <div class="alert alert-success">
      ${flash.message}
    </div>
  </g:if>
  <div class="col-lg-4">
    <div class="hpanel">
      <div class="panel-heading">
        Domains
      </div>

      <div class="panel-body">
        <div class="table-responsive">
          <h4 class="font-bold">Domains</h4>

          <table cellpadding="1" cellspacing="1" class="table table-condensed table-bordered">
            <thead>
            <tr>
              <th>Name</th>
              <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${domains.domains}" var="domain">
              <tr>
                <td>${domain.name}
                </td>
                <td></td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
      </div>

      <div class="panel-footer">
        <g:link controller="home" action="createDomain" class="btn btn-success">New Domain</g:link>
      </div>
    </div>
  </div>


  <div class="col-lg-4">
    <div class="hpanel">
      <div class="panel-heading">
        Domains
      </div>

      <div class="panel-body">
        <div class="table-responsive">
          <h4 class="font-bold">Scenarios</h4>

          <table cellpadding="1" cellspacing="1" class="table table-condensed table-bordered">
            <thead>
            <tr>
              <th>Name</th>
              <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${scenarios?.scenarios}" var="scenario">
              <tr>
                <td>${scenario.name}
                </td>
                <td></td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
      </div>

      <div class="panel-footer">
        <g:link controller="home" action="createScenario" class="btn btn-success">New Scenario</g:link>
      </div>
    </div>
  </div>

  <div class="col-lg-4">
    <div class="hpanel">
      <div class="panel-heading">
        Domains
      </div>

      <div class="panel-body">
        <div class="table-responsive">
          <h4 class="font-bold">Scenario Chain</h4>

          <table cellpadding="1" cellspacing="1" class="table table-condensed table-bordered">
            <thead>
            <tr>
              <th>Name</th>
              <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${chains?.chains}" var="chain">
              <tr>
                <td>${chain.name}
                </td>
                <td></td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
      </div>

      <div class="panel-footer">
        <g:link controller="home" action="createChain" class="btn btn-success">New Chain</g:link>
      </div>
    </div>
  </div>

</div>
</body>
</html>
