<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <h2>Detalle factor</h2>
            <c:choose>
                <c:when test="${detalleFactor.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Id factor</th>
                        <th>Factor</th>
                        <th>Ponderacion factor</th>
                        <th>Id caracteristica</th>
                        <th>Caracteristica</th>
                        <th>Nivel de importacia</th>
                        <th>Ponderacion caracteristica</th>
                        <th>Grado de Cumplimiento</th>
                        <th>Evaluacion teniendo en cuenta ponderacion</th>
                        <th>Logro ideal</th>
                        <th>Relacion con el logro ideal</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detalleFactor.rowsByIndex}" var="row" varStatus="iter">
                                <tr>

                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td rowspan="${detalleFactor.getRowCount()}">   
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td rowspan="${detalleFactor.getRowCount()}">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td rowspan="${detalleFactor.getRowCount()}">   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[3]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleCaracteristica&${row[3]}">${row[4]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[5]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[6]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[7]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[8]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[9]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[10]}%"/>
                                            </td>

                                        </c:when>
                                        <c:otherwise>
                                            <td>   
                                                <c:out value="${row[3]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleCaracteristica&${row[3]}">${row[4]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[5]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[6]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[7]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[8]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[9]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[10]}%"/>
                                            </td>
                                        </c:otherwise>    
                                    </c:choose>



                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    

