<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div class="container">  
            <div class="row">
                <div class="span8">
                    <br/>
                    <h2>Listado de  Encuestas Disponibles</h2>
                    <c:choose>
                            <c:when test="${listaEncuestasDisponibles.getRowCount()>0}">

                                <table class="table table-striped table-bordered table-condensed">
                                    <thead>
                                        <th>Encuesta</th>
                                        <th>Programa</th>
                                        <th></th>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listaEncuestasDisponibles.rowsByIndex}" var="item" varStatus="iter">
                                            <tr>    
                                                <td>   
                                                    <c:out value="${item[1]}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${proceso.programaId.nombre}"/>
                                                </td>
                                                <td class="action">
                                                    <a title="Responder Encuesta" href="#responderEncuesta&${item[0]}">Responder encuesta</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                No Existen Encuestas Disponibles.
                            </c:otherwise>
                        </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
