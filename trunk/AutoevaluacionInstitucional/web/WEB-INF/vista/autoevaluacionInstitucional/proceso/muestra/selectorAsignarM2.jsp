<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
<script type="text/javascript" language="JavaScript">
    
    
    /*   $("#buton1").click(function(){
        $("#opciones").hide(); 
        $("#filtro").hide();
        $("#filtro2").show();  
        var a = $("#select3 option:selected").index();
      
        $("#select5 option:eq("+a+")").attr("selected", "selected");
        
        $("#select4 option:eq(0)").attr("selected", "selected");
        
        $("#tablax").hide(); 
        $("#selectorx").show(); 
        
        
    })*/
    $("#buton5").click(function(){
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestra3AI",
            data: $("#formAsigMue").serialize(),
            beforeSend:function(){
            
                // $("#resultados4").hide();
            },
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra3AI",
                    success: function(data){
                        $("#resultados4").html(data);
                        $("#resultados4").show();
                    }
                })
            } //fin success
                                            
        }); //fin $.ajax
            
    }) 
</script>
<div id="tablax" style="z-index: 1;">
    <button class="btn btn-primary" id="buton5"  type="button">Editar Muestra Asignada</button>
    <br>
    <br>
    <h4>Muestra Generada Para la Fuente Seleccionada</h4>
    <br>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <th>Cedula</th>
        <th>Código</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Semestre</th>
        </thead>
        <tbody>
            <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                <tr>
                    <td>${item[0]}</td>
                    <td>${item[1]}</td>
                    <td>${item[2]}</td>
                    <td>${item[3]}</td>
                    <td>${item[4]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>







