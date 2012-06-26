<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $("#criterio").change(function(){
        if($("#criterio option:selected").val() == "nuevoCriterio"){
            $("#descripcionCriterio").hide();
            $("#formCriterio").show();
            $("#aceptarConglomerado").hide();
            $("#nuevoCriterio").show();
        }  
        else if($("#criterio option:selected").val() != "--"){
            $("#formCriterio").hide();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=descripcionConglomerado",
                data: $('#formAsigMue3').serialize(),
                success: function(data){
                    $("#descripcionCriterio").html(data);
                    $("#descripcionCriterio").show();
                }
            }) 
        
        }
        else{
            $("#formCriterio").hide();
            $("#descripcionCriterio").hide();
        }
    });
</script>
<div class="control-group">
    <label class="control-label" for="criterio">Seleccion un conglomerado existente</label>
    <div class="controls">
        <select id="criterio" name="selectCriterio">
            <option value="--">--</option>
            <c:forEach items="${listCriterio.rowsByIndex}" var="item">
                <option value="${item[0]}">${item[1]}</option>
            </c:forEach>
            <option value="nuevoCriterio">Nuevo Criterio</option>
        </select>   
    </div>
</div>