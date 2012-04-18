<%-- 
    Document   : listarProceso
    Created on : 18-abr-2012, 16:18:51
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Procesos</title>
    </head>
    <body>
        <table class="table table-striped table-bordered table-condensed">
            <thead>
            <th>Programa</th>
            <th>Muestra</th>
        </thead>
        <tbody>
        <c:forEach items="${muestraCalculada.rowsByIndex}" var="item2" varStatus="iter">
            <tr>
                <td>${item2[0]}</td>
            <c:choose>
                <c:when test="${item2[1]==0}">
                    <td>1</td>
                </c:when>
                <c:otherwise>
                    <td>${item2[1]}</td>
                </c:otherwise>
            </c:choose>
            </tr>
            <c:set var="iterador" value="${iterador + item2[1]}" />
        </c:forEach>
    </tbody>
</table>   
</body>
</html>
