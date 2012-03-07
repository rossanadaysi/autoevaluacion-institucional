<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
<script type="text/javascript" src="jQuery/dragDrop/fcbklistselection.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
  
  
  
  
    });
            
    $("#buton1").click(function(){
        $("#opciones").hide(); 
        $("#filtro").hide();
        $("#filtro2").show();  
        var a = $("#select3 option:selected").index();
      
        $("#select5 option:eq("+a+")").attr("selected", "selected");
        
        $("#select4 option:eq(0)").attr("selected", "selected");
        
        $("#tablax").hide(); 
        $("#selectorx").show(); 
        
        
    })
    
                
</script>
<div id="div1">
    <c:if test="${aux_asignarM == 1}">
        <div id="opciones">
            <div class="alert alert-info" i style="width: 450px;">
                <a class="close">×</a>
                <strong>Información!</strong>
                La muestra ya ha sido asignada para la fuente seleccionada.
            </div>
        </div>
    </c:if>
</div>
<br><br>