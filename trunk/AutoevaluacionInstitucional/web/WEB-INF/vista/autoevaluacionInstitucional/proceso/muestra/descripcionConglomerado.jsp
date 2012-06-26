<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<table class="table table-striped table-bordered table-condensed">
    <thead>
    <th>Elementos del criterio de conglomerado: </th>
</thead>
<tbody>
<c:forEach items="${listDescripcionCriterio.rowsByIndex}" var="item">
    <tr>
        <td>
            ${item[0]}
        </td>
    </tr>  
</c:forEach>
</tbody>
</table>