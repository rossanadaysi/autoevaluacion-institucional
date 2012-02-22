<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    
    function presionSubmit(va)
    {
        
        if($("#select option:selected").val() == "--"){
            $("#resultados2").hide();
        }
        
        else{
            if($("#select option:selected").val() == "1"){
                $("#filtro").show();
            }
            else{
                $("#filtro").hide(); 
            }
                
            
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestraAI",
                data: $("#formAsigMue").serialize(),
                beforeSend:function(){
                    $("#resultados2").html('CARGANDO...');
                    $("#resultados2").show();
                },
                success: function(){
                    $("#resultados2").load("<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestraAI");
                    $("#resultados2").show();
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
            <form  id="formAsigMue" method="post">
                <fieldset>
                    <legend>Asignación de Muestra</legend>

                    <select id="select" name="fuente" onchange="presionSubmit(this)">
                        <option value="--">Seleccione una Fuente</option>
                        <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
                            <option value="${item2[0]}">${item2[1]}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <div id="filtro" style="display: none;">
                        <p>Filtros</p>
                        <table>
                            <tr>
                                <td>
                                    <select id="select" name="programas" onchange="presionSubmit(this)">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="select" name="semestres" onchange="presionSubmit(this)">
                                        <option value="--">Seleccione Semestre</option>
                                        <option value="1">Primer Semestre</option
                                        <option value="2">Segundo Semestre</option>
                                        <option value="3">Tercero Semestre</option>
                                        <option value="4">Cuarto Semestre</option>
                                        <option value="5">Quinto Semestre</option>
                                        <option value="6">Sexto Semestre</option>
                                        <option value="7">Septimo Semestre</option>
                                        <option value="8">Octavo Semestre</option>
                                        <option value="9">Noveno Semestre</option>
                                        <option value="10">Decimo Semestre</option>                                      
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br> 
                    <div  id="resultados2"></div>
                </fieldset>
        </div>
    </div>
</div>
</form>  

