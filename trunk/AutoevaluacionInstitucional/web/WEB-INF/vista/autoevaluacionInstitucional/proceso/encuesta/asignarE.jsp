<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    
    function presionSubmit(va)
    {
        
        if($("#select option:selected").val() == "--"){
            
        }
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=selectorAsignarEncuestasAI",
            data: $("#formAsigEnc").serialize(),
            success: function(){
                $("#resultados").load("<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarEncuestasAI");
                $("#resultados").show();
            } //fin success
                                            
        }); //fin $.ajax
        return false;
    }

           
</script>
<h1>Asignar Encuesta</h1>
<br>
<form  id="formAsigEnc" method="post">
    <select id="select" name="fuente" onchange="presionSubmit(this)">
        <option value="--">Seleccione una Fuente</option>
        <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
            <option value="${item2[0]}">${item2[1]}</option>
        </c:forEach>
    </select>
    <br>
    <br>
     
    <div  id="resultados"></div>
</form>  

