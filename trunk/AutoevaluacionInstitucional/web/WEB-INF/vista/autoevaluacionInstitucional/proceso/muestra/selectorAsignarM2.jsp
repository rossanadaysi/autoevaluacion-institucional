<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="tablax" style="z-index: 1;">
    <button class="btn btn-primary" id="buton1"  type="button">Editar Muestra Asignada</button>
    <br>
    <br>
    <h4>Muestra Generada Para la Fuente Seleccionada</h4>
    <br>
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







