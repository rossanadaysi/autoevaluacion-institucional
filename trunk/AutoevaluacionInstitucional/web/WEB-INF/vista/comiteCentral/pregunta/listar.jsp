<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        var actual = "";
        $("a.delete").click(function(e){
            e.preventDefault();
            actual = $(this).attr("id"); 
            $('#myModalElimP').modal();
            
        })   
            
        $("a[href='#eliminarPregunta']").click(function(){
           
           
        });
    })
</script>



<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <h2>Listado de  Preguntas</h2>
            <c:choose>
                <c:when test="${fn:length(listpreguntas)!= 0}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Pregunta</th>
                        <th>Tipo</th>
                        <th></th>
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
                                    <td class="action icon16">
                                        <a title="Editar" href="#editarPregunta&${row.id}" class="edit"></a>
                                        <a title="Eliminar" class="delete" id="${row.id}" href=""></a>
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

