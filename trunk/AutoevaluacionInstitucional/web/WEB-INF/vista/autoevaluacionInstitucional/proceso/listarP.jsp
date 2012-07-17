<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
   

</script>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <fieldset>
                <legend>Listado de  procesos</legend>
                <c:choose>
                <c:when test="${fn:length(listProceso)!= 0}">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Cierre</th>
                    <th>Programa</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listProceso}" var="row" varStatus="iter">
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
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
                </c:when>
                 <c:otherwise>
                    No Existen Procesos Registrados en el Sistema.
                </c:otherwise>
                </c:choose>
            </fieldset>
        </div>
    </div>
</div>    
