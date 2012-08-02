<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <h2>Matriz de Calidad de Factores</h2>
            <c:choose>
                <c:when test="${matrizFactores.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Id Factor</th>
                        <th>Factor</th>
                        <th>Ponderacion Factor</th>
                        <th>Grado de Cumplimiento</th>
                        <th>Evaluacion teniendo en cuenta ponderacion</th>
                        <th>Logro ideal</th>
                        <th>Relacion con el logro ideal</th>
                        </thead>
                        <tbody>
                            <c:set var="ponderacion" value="0" />
                            <c:set var="cumplimiento" value="0" />
                            <c:forEach items="${matrizFactores.rowsByIndex}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row[0]}"/>
                                    </td>
                                    <td>   
                                        <a href="#detalleFactor&${row[0]}">${row[1]}</a>
                                    </td>
                                    <td>   
                                        <c:out value="${row[2]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[3]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[4]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[5]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[6]}%"/>
                                    </td>
                                </tr>
                                <c:set var="ponderacion" value="${ponderacion + row[2]}" />
                                <c:set var="cumplimiento" value="${(row[2] * row[3])+cumplimiento}" />
                            </c:forEach>
                                <tr>
                                    <td>   
                                       Totales:
                                    </td>
                                    <td>   
                                        
                                    </td>
                                    <td>   
                                        
                                    </td>
                                    <td>   
                                        <fmt:formatNumber type="number"   maxFractionDigits="2" value="${cumplimiento/ponderacion}" />
                                    </td>
                                    <td>   

                                    </td>
                                    <td>   
                                        5.0
                                    </td>
                                    <td>   
                                        <fmt:formatNumber type="number"   maxFractionDigits="2" value="${(cumplimiento/ponderacion)*20}" />%
                                    </td>
                                </tr>
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
