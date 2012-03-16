<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <br/>
            <h2>Listado de  Preguntas</h2>
            <c:choose>
                <c:when test="${listpreguntas.size() != 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Pregunta</th>
                        <th>Tipo</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listpreguntas}" var="row" varStatus="iter">
                                <tr>    
                                    <td>   
                                        <c:out value="${row.pregunta}"/>
                                    </td>
                                    <td>
                                        <c:out value="${row.tipo}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Preguntas Registradas en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    

