<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <br/>
            <h2>Listado de  Encuestas</h2>
            <c:choose>
                <c:when test="${listencuestas.size() != 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Nombre</th>
                        <th>Objetivo</th>
                        <th></th>
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
                                    <td class="action icon16">
                                        <a title="Editar" href="#editarEncuesta&${row.id}" class="edit"></a>
                                        <a title="Eliminar" class="delete" href=""></a>
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

