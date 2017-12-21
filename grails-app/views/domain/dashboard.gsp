<html>
<head>
  <title>GenRocket - Domain Dashboard</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">
<ul class="breadcrumb">
  <li><g:link controller="home" action="dashboard">Project Dashboard</g:link><span class="divider"></span></li>
  <li><g:link controller="home" action="listProjectVersions"
              params="[id: domain?.projectName]">Project Version</g:link> <span
      class="divider"></span></li>
  <li><g:link controller="home" action="showProjectArtifacts"
              params="[id: domain?.projectName, versionNumber: domain?.versionNumber]">Artifacts</g:link> <span
      class="divider"></span></li>
</ul>
<g:hiddenField name="domainId" value="${domain?.externalId}"/>

<div class="col-lg-2">
  <h4 class="font-bold">Attributes (${domain?.attributes?.size()})&nbsp;&nbsp;<g:link controller="domain"
                                                                                      action="createAttribute"
                                                                                      id="${domain?.externalId}"><i
        class="pe-7s-plus"></i></g:link></h4>

  <ul class="nav" id="side-menu" style="border-right: 1px solid #e4e5e7; border-left: 1px solid #e4e5e7">
    <g:each in="${domain?.attributes}" var="attribute">
      <li><a href="#" style="width: 70%;display: inline-block">${attribute?.name}</a>
        <g:link controller="domain" action="deleteAttribute" params="[id: domain?.externalId, name: attribute?.name]"
                style="display: inline-block"><i class="fa fa-trash"></i></g:link>
      </li>
    </g:each>
  </ul>
</div>

<div class="col-lg-10" id="lowerPanel">

  <div class="col-lg-6">
    <div class="hpanel">
      <div class="panel-heading">
        Domain Variables
      </div>

      <div class="panel-body">
        <div class="table-responsive">
          <api:globalVariables domainId="${domain?.externalId}"/>
        </div>

        <div class="panel-footer">
          <button class="btn btn-success btn-sm createGlobalVariableBtn"
                  data-domainid="${domain?.id}">Create Global Variable</button>
        </div>
      </div>
    </div>
  </div>

  <div class="col-lg-6">
    <div class="hpanel">
      <div class="panel-heading">
        Domain Receivers
      </div>

      <div class="panel-body">
        <div class="table-responsive">
          <api:domainReceivers domainId="${domain?.externalId}"/>
        </div>

        <div class="panel-footer">
          <button class="btn btn-success btn-sm addDomainReceiver"
                  data-domainid="${domain?.id}">Add Domain Receiver</button>
        </div>
      </div>
    </div>
  </div>

  <div class="col-lg-12">
    <div class="hpanel">
      <div class="panel-heading">
        Preview Data
      </div>

      <div class="panel-body border-top">

        <div id="previewDataLoading" style="text-align: center;">
          <img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="spinner"/> Loading...
        </div>

        <div id="dataPreviewContainer" style="margin: 0"></div>
      </div>

    </div>
  </div>

</div>

<script type="text/javascript">
  $(document).ready(function () {
    $("#dataPreviewContainer").empty();
    $("#previewDataLoading").show();
    $("#dataPreviewContainer").load("${createLink(controller: 'domain', action: 'previewData')}/" + $("#domainId").val(), {}, function () {
      $("#previewDataLoading").hide();
    });
  });
</script>

</body>
</html>
