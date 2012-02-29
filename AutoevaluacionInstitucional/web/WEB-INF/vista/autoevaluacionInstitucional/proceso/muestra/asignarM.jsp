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
                $("#formula").show();
                $("option[rel=popover]")
                .popover()
                .click(function(e) {
                    e.preventDefault()
                }) 


            }
            else{
                $("#formula").hide(); 
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
            <a ></a>
            <form  id="formAsigMue" method="post">
                <fieldset>
                    <legend>Asignación de Muestra</legend>
                    <p>Fuente</p>
                    <select class="span3" id="select" name="fuente" onchange="presionSubmit(this)">
                        <option value="--">Seleccione una Fuente</option>
                        <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
                            <option value="${item2[0]}">${item2[1]}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <div id="formula" style="display: none;">
                        <p>Formular Calculo Muestra</p>

                        <select class="span3" id="select" name="formula" onchange="presionSubmit(this)">
                            <option value="--">Seleccione una Formula</option>
                            <option data-content="<p> <div><img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/f1.gif'></div></p><br><p> n = Tamaño de la muestra. </p><p> Z = Nivel de confianza aplicado al estudio. Basados en la tabla Z. </p><p> p = probabilidad de ocurrencia del evento previsto. </p><p> q = Probabilidad de no ocurrencia del evento previsto. </p><p> EE = Error de tolerancia máxima permitida. </p><p> N = Tamaño de la población." rel="popover"  value="${item2[0]}" data-original-title="Detalle Formula">Muestreo aleatorio por conglomerado</option>
                        </select>
                    </div>
                    <br>
                    <div id="filtro" style="display: none;">
                        <p>Filtros</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select" name="programas" onchange="presionSubmit(this)">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="select" name="semestres" onchange="presionSubmit(this)">
                                        <option value="--">Seleccione Semestre</option>
                                        <option value="1">Primer Semestre</option>
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
                        </form>
        </div>
    </div>
</div>