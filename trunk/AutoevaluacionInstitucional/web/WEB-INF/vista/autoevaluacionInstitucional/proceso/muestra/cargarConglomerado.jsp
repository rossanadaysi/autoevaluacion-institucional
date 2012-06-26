<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p>Seleccionar Conglomerado</p>
<select class="span3" id="selectConglomerado" name="conglomerado" onchange="presionSubmitConglomerado()">
    <option value="--">Seleccione un conglomerado</option>
    <c:if test="${idFuenteMuestra == 1}">
        <option value="programa">Programa</option>
        <%-- <option value="semestre">Semestre</option>--%>
    </c:if>
    <c:if test="${idFuenteMuestra == 2}">
        <option value="programa">Programa</option>
        <option value="tipo">Tipo contratación</option>
    </c:if>
    <c:if test="${idFuenteMuestra == 3}">
        <option value="cargo">Cargo</option>
        <option value="ninguno">Sin conglomerado</option>
    </c:if>
    <c:if test="${idFuenteMuestra == 4}">
        <option value="programa">Programa</option>
        <option value="ninguno">Sin conglomerado</option>
    </c:if>
    <c:if test="${idFuenteMuestra == 5}">
        <option value="programa">Programa</option>
        <option value="ninguno">Sin conglomerado</option>
    </c:if>
    <c:if test="${idFuenteMuestra == 6}">
        <option value="sectorempresarial">Sector empresarial</option>
        <option value="ninguno">Sin conglomerado</option>
    </c:if>
    <c:if test="${idFuenteMuestra == 7}">
        <option value="ninguno">Sin conglomerado</option>
    </c:if>
    <option value="nuevoCriterio">Nuevo conglomerado</option>
</select>