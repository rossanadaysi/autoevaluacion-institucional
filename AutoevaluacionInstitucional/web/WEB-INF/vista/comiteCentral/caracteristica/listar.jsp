<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        var actual = "";
        $("a.delete").click(function(e){
            e.preventDefault();
            actual = $(this).attr("id"); 
            $('#myModalElimC').modal();
            
        })
    })
</script> 
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <h2>Listado de  Caracter&iacute;sticas</h2>
            <c:choose>
                <c:when test="${fn:length(listcaracteristicas)!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>C&oacute;digo</th>
                        <th>Caracter&iacute;stica</th>
                        <th></th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listcaracteristicas}" var="row" varStatus="iter">
                                <tr>    
                                    <td>   
                                        <c:out value="${row.factorId.id}.${row.id}"/>
                                    </td>
                           
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td class="action icon16">
                                        <a title="Editar" href="#editarCaracteristica&${row.id}" class="edit"></a>
                                        <a title="Eliminar" class="delete" href=""></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Caracteristicas Registradas en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
