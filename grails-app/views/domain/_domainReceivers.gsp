<table id="globalVariablesTable" cellpadding="1" cellspacing="1"
       class="table table-condensed">
  <thead>
  <tr>
    <th>Name</th>
    <th>Type</th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${domainReceivers}" var="domainReceiver">
    <tr>
      <td>
        ${domainReceiver.name}
      </td>
      <td>${domainReceiver.receiverType}</td>
    </tr>
  </g:each>
  </tbody>
</table>