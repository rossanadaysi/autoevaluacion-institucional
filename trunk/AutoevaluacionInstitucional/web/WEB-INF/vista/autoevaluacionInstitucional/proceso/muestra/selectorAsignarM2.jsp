<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $("#botonEditarMuestra").click(function(){
        var a = $("#select3 option:selected").index();
        var b = $("#select4 option:selected").index();
        $("#select5 option:eq("+a+")").attr("selected", "selected");
        $("#select6 option:eq("+b+")").attr("selected", "selected");
        $("#filtro").hide();
        $("#filtro2").show();
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestra3AI",
            data: $("#formAsigMue").serialize(),
            beforeSend:function(){
                $("#resultados4").html("<div class='alert alert-info' id='cargando' style='width: 221px;'>"
                    +"<a class='close'>×</a>"
                    +"<img src='<%=request.getContextPath()%>/css/images/wait.gif' style='float:center; padding: 1px 10px 0 0;'>"
                    +"Cargando..."
                    +"</div>");
            },
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra3AI",
                    
                    success: function(data){
                        $("#resultados4").hide();
                        $("#resultados4").html(data);
                        setTimeout(function(){$("#resultados4").show()}, 50);  
                    }
                })
            } //fin success                    
        }); //fin $.ajax
    }) 
</script>
<div id="tablax" style="z-index: 1;">
    <button class="btn btn-primary" id="botonEditarMuestra"  type="button">Editar Muestra Asignada</button>
    <br>
    <br>
    <h4>Muestra Generada Para la Fuente Seleccionada</h4>
    <br>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <th>Código</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Semestre</th>
        </thead>
        <tbody>
            <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                <tr>
                    <td>${item[1]}</td>
                    <td>${item[2]}</td>
                    <td>${item[3]}</td>
                    <td>${item[4]}</td>
                </tr>
                <c:set var="iterador" value="${iter.index + 1}" />
            </c:forEach>
        </tbody>
    </table>
    <p><strong>Total Estudiantes: ${iterador}</strong></p>
</div>







