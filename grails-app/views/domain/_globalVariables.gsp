<table id="globalVariablesTable" cellpadding="1" cellspacing="1"
       class="table table-condensed">
  <thead>
  <tr>
    <th>Name</th>
    <th>Description</th>
    <th>Value</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${globalVariables}" var="globalVariable">
    <tr>
      <td>
        ${globalVariable.name}
      </td>
      <td><api:truncateText text="${globalVariable.description}" maxChars="90"/></td>
      <td align="right">${globalVariable.value}</td>
      <td align="right">

      </td>
    </tr>
  </g:each>
  </tbody>
</table>