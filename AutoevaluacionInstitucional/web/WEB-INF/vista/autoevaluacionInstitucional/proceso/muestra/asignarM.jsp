<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    
    $(document).ready(function() {$('#boton1').button()})
    
    
    function presionSubmit3()
    {
        
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=generarMuestra",
            data: $("#formAsigMue").serialize(),
            beforeSend:function(){
                $("#enlace").hide();
                $("#cargando").show();
                // $("#resultados2").show();
            },
            success: function(){
                //   
                $("#cargando").hide();
                $("#select3 option:eq(0)").attr("selected", "selected");
                $("#select4 option:eq(0)").attr("selected", "selected");
                $("#filtro").show();
                $("#resultados2").show();
                  
            } //fin success
                                            
        }); //fin $.ajax
            
    }
    function presionSubmit2(va)
    {
        
        if($("#select2 option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#filtro").hide();
        }
        else{
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=calcularMuestraAI",
                data: $("#formAsigMue").serialize(),
                beforeSend:function(){
                    // $("#resultados2").show();
                },
                success: function(){
                    $.ajax({
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/ControllerAI?action=muestraCalculada",
                        success: function(data){
                            $("#resultados3").html(data);
                            $("#resultados3").show();
                            $("#resultados2").hide()
                            $("#enlace").show();
                        }
                    })
                
                
               
                  
                } //fin success
                                            
            }); //fin $.ajax
        }
    }
        
    function presionSubmit(va)
    {
        $("option[rel=popover]")
        if($("#select option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#formula").hide();
            $("#enlace").hide();
            $("#filtro").hide();
            $("#resultadoAlert").hide();
           
        }
        else{
            $("#formula").hide();  
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestraAI",
                data: $("#formAsigMue").serialize(),
                beforeSend:function(){
                },
                success: function(data){
                    $.ajax({
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestraAI",
                        success: function(data){
                            $("#resultados2").html(data);
                        }
                    })
                } //fin success
                                            
            }); //fin $.ajax
            //  }
        }
        return false;
    }
    
    function presionSubmit4()
    {     
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestra2AI",
            data: $("#formAsigMue").serialize(),
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra2AI",
                    success: function(data){
                        $("#resultados4").html(data);
                        $("#resultados4").show();
                    }
                })
            } //fin success                                          
        }); //fin $.ajax
        return false;
    }

    function presionSubmit6(va)
    {
        
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestra3AI",
            data: $("#formAsigMue").serialize(),
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
        return false;
         
        return false;
    }
    
    
    
    
    $("#buton2").click(function(){
        $("#resultados2").hide(); 
        $("#resultadoAlert").hide();
        $("#filtro").hide(); 
        $("#resultados3").hide(); 
        $("#resultados4").hide();
        $("#select2 option:eq(0)").attr("selected", "selected");
        $("#formula").show();
        $("option[rel=popover]")
        .popover()
        .click(function(e) {
            e.preventDefault()
                    
        })
    })  
        
  
</script>
<br>
<div class="hero-unit">
    <div class="row">
        <form  id="formAsigMue" method="post">
            <fieldset>
                <legend>Asignación de Muestra</legend>
                <div class="span5" style="border: 1px solid #FFF;">

                    <p>Fuente</p>
                    <select class="span3" id="select" name="fuente" onchange="presionSubmit(this)">
                        <option value="--">Seleccione una Fuente</option>
                        <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
                            <option value="${item2[0]}">${item2[1]}</option>
                        </c:forEach>
                    </select>
                    <div id="resultadoAlert" style="display: none">
                        <div class="alert alert-info" i style="width: 450px;">
                            <a class="close">×</a>
                            <strong>Información!</strong>
                            La muestra ya ha sido asignada para la fuente seleccionada.
                        </div>
                        <button class="btn btn-secundary" id="buton2"  type="button">Generar Nueva Muestra</button>
                        <br>
                        <br>
                    </div>
                    <div id="formula" style="display: none;">
                        <p>Calcular Muestra</p>
                        <select class="span3" id="select2" name="formula" onchange="presionSubmit2(this)">
                            <option value="--">Seleccione una Formula</option>
                            <option data-content="<p> <div><img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/f1.gif'></div></p><br><p> n = Tamaño de la muestra. </p><p> Z = Nivel de confianza aplicado al estudio. Basados en la tabla Z. </p><p> p = probabilidad de ocurrencia del evento previsto. </p><p> q = Probabilidad de no ocurrencia del evento previsto. </p><p> EE = Error de tolerancia máxima permitida. </p><p> N = Tamaño de la población." rel="popover"  value="1" data-original-title="Detalle Formula">Muestreo aleatorio por conglomerado</option>
                        </select>
                    </div>
                    <div id="enlace" style="display: none;">
                        <button id="boton1" class="btn btn-primary" onclick="presionSubmit3()" data-loading-text="loading..." type="button">Generar Muestra Aleatoriamente</button>
                    </div>
                    <div class="alert alert-info" id="cargando" style="display: none; width: 221px;">
                        <a class="close">×</a>
                        <img src="<%=request.getContextPath()%>/css/images/wait.gif" style="float:left; padding: 1px 10px 0 0;">
                        Generando Muestra...
                    </div>
                    <div id="filtro" style="display: none;">
                        <p>Filtros 1</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select3" name="programas" onchange="presionSubmit4()">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="select4" name="semestres" onchange="presionSubmit4()">
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
                        <div style=""><p>Seleccione un programa para ver la muestra asignada al mismo.</p></div>
                    </div>
                    <div id="filtro2" style="display: none;">
                        <p>Filtros 2</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select5" name="programas2" onchange="presionSubmit6(this)">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="select6" name="semestres2" onchange="presionSubmit6(this)">
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
                        <div style="width: 500px;"><p>Seleccione un programa para ver la muestra asignada al mismo.</p></div>
                    </div>
                    <div  id="resultados2"></div>
                    <div  class="span10">
                        <div id="resultados4" class="accordion" style="margin-left: -30px;"></div> 
                    </div>

                </div>
                <div  class="span4">
                    <div  id="resultados3" class="accordion" style="position: absolute;"></div>
                </div>
            </fieldset>
        </form>
    </div>
</div>


