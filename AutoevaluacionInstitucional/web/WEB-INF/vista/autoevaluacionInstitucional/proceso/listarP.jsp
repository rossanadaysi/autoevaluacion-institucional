<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function() {
        $('.verProceso').click(function(ev){
            ev.stopPropagation();
            ev.preventDefault();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=verProceso&idPro="+$(this).attr("rel"),
                success: function(){
                    $.ajax({
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/ControllerAI?action=menuAI",
                        success: function(data){
                            $("#menu").html(data);
                            setTimeout(function(){
                                location = "<%=request.getContextPath()%>/#detalleProceso";
                            }, 200);
                        
                        }
                    });              
                } //fin success
                                            
            }); //fin $.ajax
        }
    )})
 

</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <fieldset>
                <legend>Listado de  procesos</legend>
                <c:choose>
                    <c:when test="${listProceso.getRowCount() != 0}">
                        <table class="table table-striped table-bordered table-condensed">
                            <thead>
                            <th>Descripción</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Cierre</th>
                            <th>Programa</th>
                            <th></th>
                            </thead>
                            <tbody>
                                <c:forEach items="${listProceso.rowsByIndex}" var="item" varStatus="iter">
                                    <tr>
                                        <td>${item[3]}</td>
                                        <td>${item[1]}</td>
                                        <td>${item[2]}</td>
                                        <td>${programa.getNombre()}</td>
                                        <td><a class="verProceso" href="" rel="${item[0]}"> Ir al proceso.</a></td>
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
