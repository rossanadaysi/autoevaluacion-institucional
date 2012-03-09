<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/jQuery/dragDrop/fcbklistselection.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jQuery/dragDrop/fcbklistselection.js"></script>
<script type="text/javascript" language="JavaScript">
    function presionSubmit(va)
    {
        
        if($("#select option:selected").val() == "--"){
            $("#resultados").hide();
        }
        else{
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=selectorAsignarEncuestasAI",
                data: $("#formAsigEnc").serialize(),
                success: function(){
                    $("#resultados").load("<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarEncuestasAI");
                    $("#resultados").show();
                } //fin success
                                            
            }); //fin $.ajax
        }
        return false;
    }          
</script>
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <form  id="formAsigEnc" method="post">
                <fieldset>
                    <legend>Asignacion de Encuestas</legend>
                    <p>Fuente</p>
                    <select id="select" name="fuente" onchange="presionSubmit(this)">
                        <option value="--">Seleccione una Fuente</option>
                        <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
                            <option value="${item2[0]}">${item2[1]}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <br> 
                    <div  id="resultados"></div>
                </fieldset>
            </form>     
        </div>
    </div>
</div>


