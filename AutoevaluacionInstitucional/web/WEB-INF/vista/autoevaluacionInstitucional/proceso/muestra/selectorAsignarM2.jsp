<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="border: 1px solid #FFF; height: 10px;">
    <p>Muestra Generada Para la Fuente Seleccionada</p>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <th>Cedula</th>
        <th>Código</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Semestre</th>
        </thead>
        <tbody>
            <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                <tr>
                    <td>${item[0]}</td>
                    <td>${item[1]}</td>
                    <td>${item[2]}</td>
                    <td>${item[3]}</td>
                    <td>${item[4]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>