<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="z-index: 1;">
    <br>
    <h4>Lista de Factores</h4>
    <br>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <th>Nombre</th>
        <th>Descripci&oacute;n</th>
        </thead>
        <tbody>
            <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                <tr>
                    <td>${item[0]}</td>
                    <td>${item[1]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
