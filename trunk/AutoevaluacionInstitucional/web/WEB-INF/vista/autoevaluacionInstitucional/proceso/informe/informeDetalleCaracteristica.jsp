<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <h2>Detalle caracteristica</h2>
            <c:choose>
                <c:when test="${detalleCaracteristica.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Id caracteristica</th>
                        <th>Caracteristica</th>
                        <th>Id indicador</th>
                        <th>Indicador</th>
                        <th>Cumplimiento</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detalleCaracteristica.rowsByIndex}" var="row" varStatus="iter">
                                <tr>

                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td rowspan="${detalleCaracteristica.getRowCount()}">   
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td rowspan="${detalleCaracteristica.getRowCount()}">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleIndicador&${row[2]}">${row[3]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[4]}"/>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleIndicador&${row[2]}">${row[3]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[4]}"/>
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


