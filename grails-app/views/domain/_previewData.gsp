<div class="table-responsive previewDomainData">
  <table id="dataPreviewTable" class="table table-bordered table-striped table-condensed">
    <thead>
    <tr>
      <g:each in="${attributes}" var="attribute">
        <th>${attribute}</th>
      </g:each>
    </tr>
    </thead>
    <tbody>
    <g:each in="${attributeData}" var="data">
      <tr>
        <g:each in="${data}" var="cell">
          <td>${cell}</td>
        </g:each>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>
