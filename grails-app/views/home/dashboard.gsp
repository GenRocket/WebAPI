<html>
<head>
  <title>GenRocket - Login</title>
  <meta name="layout" content="main"/>
</head>

<body class="blank">

<div class="color-line"></div>


<div class="login-container">
  <div class="row">
    <div class="col-lg-6">
      <table cellpadding="1" cellspacing="1" class="table table-condensed">
        <thead>
        <tr>
          <th>Name</th>
          <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${projects}" var="project">
          <tr>
            <td><a href="#">${project.name}</a>
            </td>

            <td>

            </td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
