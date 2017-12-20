<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title><g:layoutTitle default="Grails"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon">
  <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
  <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- Page title -->
  <title>HOMER | WebApp admin theme</title>

  <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
  <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->


  <!-- Vendor scripts -->
  <asset:stylesheet src="font-awesome.css"/>
  <asset:stylesheet src="metisMenu.css"/>
  <asset:stylesheet src="animate.css"/>
  <asset:stylesheet src="bootstrap.css"/>
  <asset:stylesheet src="pe-icon-7-stroke.css"/>
  <asset:stylesheet src="helper.css"/>
  <asset:stylesheet src="style.css"/>
  <asset:stylesheet src="dataTables.bootstrap.css"/>
  <asset:stylesheet src="DataTables_bootstrap.css"/>

  <asset:javascript src="jquery.min.js"/>
  <asset:javascript src="jquery-ui.min.js"/>
  <asset:javascript src="jquery.slimscroll.min.js"/>
  <asset:javascript src="bootstrap.min.js"/>
  <asset:javascript src="metisMenu.min.js"/>
  <asset:javascript src="jquery.dataTables.min.js"/>
  <asset:javascript src="dataTables.bootstrap.min.js"/>
  <asset:javascript src="jquery.nestable.js"/>

  %{--<asset:javascript src="homer.js"/>--}%

  <g:layoutHead/>
</head>

<body>
<div class="color-line"></div>

<div class="col-lg-12 text-right"><g:link controller="home" action="logout">Logout</g:link></div>
<g:layoutBody/>
</body>
</html>
