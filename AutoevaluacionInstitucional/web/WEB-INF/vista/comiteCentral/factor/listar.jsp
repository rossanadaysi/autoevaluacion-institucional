<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        var actual="";
        $("a.delete").click(function(e){
            e.preventDefault();
            actual = $(this).attr("id");
            actual = actual.replace("#eliminarFactor","");
            $('#myModalElimF').modal();  
        });
        
        $("a[href='#eliminarFactor']").click(function(e){
            $.ajax({ 
                type: "POST", 
                url: "ControllerCC?action=eliminarFactorCC", 
                data:"idF="+actual,
                beforeSend :function(){
                    $("div.ui-layout-center").hide();
                },
                success: function(data) 
                {
                    $("div.ui-layout-center").html(data);
                    $("div.ui-layout-center").show(100);
                                        
                } //fin success
            }); //fin del $.ajax
        });  
    });
</script>

<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <br/>
            <h2>Listado de  Factores</h2>
            <c:choose>
                <c:when test="${listfactores.size() != 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Nombre</th>
                        <th>Descripci&oacute;n</th>
                        <th></th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listfactores}" var="row" varStatus="iter">
                                <tr>    
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td>
                                        <c:out value="${row.descripcion}"/>
                                    </td>
                                    <td class="action icon16">
                                        <a title="Editar" href="#editarFactor&${row.id}" class="edit"></a>
                                        <a title="Eliminar" class="delete" href="" id="#eliminarFactor${row.id}"></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Factores Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
