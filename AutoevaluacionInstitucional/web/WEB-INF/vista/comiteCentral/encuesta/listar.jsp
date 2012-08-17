<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <h2>Listado de  Encuestas</h2>
            <c:choose>
                <c:when test="${fn:length(listencuestas)!= 0}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Nombre</th>
                        <th>Objetivo</th>
                        <th>Vista previa</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listencuestas}" var="row" varStatus="iter">
                                <tr>    
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td>
                                        <c:out value="${row.descripcion}"/>
                                    </td>
                                    <td class="action">
                                        <a title="Editar" href="#editarEncuesta&${row.id}"><i class="icon-edit"></i></a>
                                        <!--<a title="Imprimir" href="#imprimirEncuesta&${row.id}" class="icon-print"></a>-->
                                        <a title="Vista previa" href="#verEncuesta&${row.id}"><i class="icon-eye-open"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Encuestas Registradas en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    

