<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="border: 1px solid #FFF; height: 10px;">
    <p>Muestra Calculada Para la Fuente Seleccionada</p>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <th>Programa</th>
        <th>Muestra</th>
        </thead>
        <tbody>
            <c:forEach items="${muestraCalculada.rowsByIndex}" var="item" varStatus="iter">
                <tr>
                    <td>${item[0]}</td>
                    <td>${item[1]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>