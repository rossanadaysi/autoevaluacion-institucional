<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <fieldset>
                <legend>Estado del proceso en ejecución</legend>
                <p>Detalle:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Cierre</th>
                    <th>Programa</th>
                    <th>Informes</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${detailProceso2}" var="row" varStatus="iter">
                            <tr>  
                                <td>   
                                    <c:out value="${row.descripcion}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.fechainicio}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.fechacierre}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.programaId.nombre}"/>
                                </td>
                                <td>   
                                    <a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#informe1">Informe resultado por encuestas</a><br/>
                                    <a  id="informeEncuesta2"  href="<%=request.getContextPath()%>/#informeMatriz">Matriz de calidad por caracteristicas</a><br/>
                                    <a  id="informeMatrizFact"  href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de calidad por factores</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
                <p>Estado general del proceso:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${tabla1.rowsByIndex}" var="row" varStatus="iter">
                            <tr>  
                                <td>   
                                    <c:out value="${row[0]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[1]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[2]}%"/>
                                </td>
                                <td>   
                                    <c:out value="${row[3]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[4]}%"/>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>



                <p>Estado por fuente del proceso:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Fuente</th>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Procentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                ESTUDIANTE
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][0]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][1]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][0]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][1] * 100 /tabla2.rowsByIndex[0][0]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][0]-tabla2.rowsByIndex[0][1]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][0]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][1] * 100 /tabla2.rowsByIndex[0][0])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                DOCENTE
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][2]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][3]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][2]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][3] * 100 /tabla2.rowsByIndex[0][2]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][2]-tabla2.rowsByIndex[0][3]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][2]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][3] * 100 /tabla2.rowsByIndex[0][2])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                ADMINISTRATIVOS
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][4]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][5]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][4]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][5] * 100 /tabla2.rowsByIndex[0][4]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][4]-tabla2.rowsByIndex[0][5]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][4]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][5] * 100 /tabla2.rowsByIndex[0][4])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                DIRECTIVOS
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][6]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][7]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][6]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][7] * 100 /tabla2.rowsByIndex[0][6]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][6]-tabla2.rowsByIndex[0][7]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][6]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][7] * 100 /tabla2.rowsByIndex[0][6])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                EGRESADOS
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][8]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][9]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][8]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][9] * 100 /tabla2.rowsByIndex[0][8]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][8]-tabla2.rowsByIndex[0][9]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][8]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][9] * 100 /tabla2.rowsByIndex[0][8])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>

                        <tr>
                            <td>
                                EMPLEADORES
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][10]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][11]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][10]!=0}">

                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][11] * 100 /tabla2.rowsByIndex[0][10]}"/>%
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    0%
                                </c:otherwise>
                            </c:choose>
                            <td>
                                ${tabla2.rowsByIndex[0][11]-tabla2.rowsByIndex[0][10]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][10]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][11] * 100 /tabla2.rowsByIndex[0][10])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                GUBERNAMENTALES
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][12]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][13]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][12]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][13] * 100 /tabla2.rowsByIndex[0][12]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][13]-tabla2.rowsByIndex[0][12]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][12]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][13] * 100 /tabla2.rowsByIndex[0][12])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
</div>    
