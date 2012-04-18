<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    
    $(document).ready(function() {$('#botonGenerarMuestra').button()})
 
    function presionSubmitGenerarMuestra()
    {
       
        $("#select3 option:eq(0)").attr("selected", "selected");
        $("#select4 option:eq(0)").attr("selected", "selected");
        $("#select5 option:eq(0)").attr("selected", "selected");
        $("#select6 option:eq(0)").attr("selected", "selected");
        $("#select7 option:eq(0)").attr("selected", "selected");
        $("#select8 option:eq(0)").attr("selected", "selected");
            
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=generarMuestra",
            data: $("#formAsigMue").serialize(),
            beforeSend:function(){
                //$("#enlace").hide();
                $("#cargando").show();
                // $("#resultados2").show();
            },
            success: function(){
                //   
                $("#cargando").hide();
                
                $("#select3 option:eq(0)").attr("selected", "selected");
                $("#select4 option:eq(0)").attr("selected", "selected");
                $("#select8 option:eq(0)").attr("selected", "selected");
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
            
    }
    
    
    function presionSubmitConfigurarFormula()
    {
        $("#configuracionFormula").show();
    }
    
    
    function presionSubmitFormula()
    {
        
        $("#resultados2").hide();
        $("#resultados3").hide();
        $("#resultados4").hide();
        $("#enlace").hide();
        $("#filtro").hide();
        $("#filtro1").hide();
        $("#filtro2").hide();
        $("#filtro3").hide();
        $("#filtro4").hide();
        $("#botonGenerarMuestra").hide();
        $("#botonCalcularMuestra2").hide();
        $("#botonCalcularMuestra").show();
        $("#configuracionFormula").hide();
     
            
        if($("#select2 option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#filtro").hide();
            $("#filtro1").hide();
            $("#filtro2").hide();
            $("#filtro3").hide();
            $("#filtro4").hide();
        }
        else{
         
            $("#enlace").show();
        }
    }
    
    
    
    function presionSubmitCalcularMuestra()
    {
        
        $("#resultados2").hide();
        $("#resultados3").hide();
        $("#resultados4").hide();
        $("#enlace").hide();
        $("#filtro").hide();
        $("#filtro1").hide();
        $("#filtro2").hide();
        $("#filtro3").hide();
        $("#filtro4").hide();
            
        if($("#select2 option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#filtro").hide();
            $("#filtro1").hide();
            $("#filtro2").hide();
            $("#filtro3").hide();
            $("#filtro4").hide();
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
                            $("#botonGenerarMuestra").show();
                            $("#botonCalcularMuestra2").show();
                            $("#botonCalcularMuestra").hide();
                        }
                    })
                
                
               
                  
                } //fin success
                                            
            }); //fin $.ajax
        }
    }
    
        
    function presionSubmitFuente()
    {
        
        if($("#select option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#formula").hide();
            $("#enlace").hide();
            $("#filtro").hide();
            $("#filtro1").hide();
            $("#filtro2").hide();
            $("#filtro3").hide();
            $("#filtro4").hide();
            $("#resultadoAlert").hide();
            $("#configuracionFormula").hide();
           
        }
        else{
            $("#select2 option:eq(0)").attr("selected", "selected");
            $("#select3 option:eq(0)").attr("selected", "selected");
            $("#select4 option:eq(0)").attr("selected", "selected");
            $("#select5 option:eq(0)").attr("selected", "selected");
            $("#select6 option:eq(0)").attr("selected", "selected");
            $("#select7 option:eq(0)").attr("selected", "selected");
            $("#select8 option:eq(0)").attr("selected", "selected"); 
            $("#configuracionFormula").hide();
                
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#formula").hide();
            $("#enlace").hide();
            $("#filtro").hide();
            $("#filtro2").hide();
            $("#filtro1").hide();
            $("#filtro3").hide();
            $("#filtro4").hide();
            $("#resultadoAlert").hide();
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
    
    function presionSubmitFiltro()
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

    function presionSubmitFiltro2()
    {
        
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
        return false;
         
        return false;
    }
       
    $("#botonNuevaMuestra").click(function(){
    
        $("#resultados2").hide(); 
        $("#resultadoAlert").hide();
        $("#filtro").hide(); 
        $("#filtro2").hide(); 
        $("#filtro3").hide();
        $("#filtro4").hide();
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
                    <select class="span3" id="select" name="fuente" onchange="presionSubmitFuente()">
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
                        <button class="btn btn-secundary" id="botonNuevaMuestra"  type="button">Generar Nueva Muestra</button>
                        <br>
                        <br>
                    </div>
                    <div id="formula" style="display: none;">
                        <p>Calcular Muestra</p>
                        <select class="span3" id="select2" name="formula" onchange="presionSubmitFormula()">
                            <option value="--">Seleccione una Formula</option>
                            <option data-content="<p> <div><img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/f1.gif'></div></p><br><p> n = Tamaño de la muestra. </p><p> Z = Nivel de confianza aplicado al estudio. Basados en la tabla Z. </p><p> p = probabilidad de ocurrencia del evento previsto. </p><p> q = Probabilidad de no ocurrencia del evento previsto. </p><p> EE = Error de tolerancia máxima permitida. </p><p> N = Tamaño de la población." rel="popover"  value="1" data-original-title="Detalle Formula">Muestreo aleatorio por conglomerado</option>
                        </select>
                    </div>
                    <div id="configuracionFormula" style="display: none">
                        <p>Tamaño de la Población: </p>
                        <input name="tamanioPobla" type="text">
                    </div>
                    <div id="enlace" style="display: none;">
                        <button style="display: none" id="botonGenerarMuestra" class="btn btn-primary" onclick="presionSubmitGenerarMuestra()" data-loading-text="loading..." type="button">Generar Muestra</button>
                        <button style="display: none" id="botonCalcularMuestra2" class="btn btn-secundary" onclick="presionSubmitCalcularMuestra()" type="button">Calcular Muestra</button>
                        <button id="botonCalcularMuestra" class="btn btn-primary" onclick="presionSubmitCalcularMuestra()" type="button">Calcular Muestra</button>
                        <button id="botonConfigurarFormula" class="btn btn-secundary" onclick="presionSubmitConfigurarFormula()" type="button">Configurar Parametros de Fórmula</button>
                        <br>
                        <br>
                    </div>
                    <div class="alert alert-info" id="cargando" style="display: none; width: 221px;">
                        <a class="close">×</a>
                        <img src="<%=request.getContextPath()%>/css/images/wait.gif" style="float:left; padding: 1px 10px 0 0;">
                        Generando Muestra...
                    </div>
                    <div id="filtro" style="display: none;">
                        <p>Filtros</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select3" name="programas" onchange="presionSubmitFiltro()">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="select4" name="semestres" onchange="presionSubmitFiltro()">
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
                        <p>Filtros</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select5" name="programas2" onchange="presionSubmitFiltro2()">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="select6" name="semestres2" onchange="presionSubmitFiltro2()">
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
                    <div id="filtro3" style="display: none;">
                        <p>Filtros</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select7" name="programas3" onchange="presionSubmitFiltro()">
                                        <option value="--">Seleccione Programa</option>
                                        <c:forEach items="${programas.rowsByIndex}" var="item2" varStatus="iter">
                                            <option value="${item2[0]}">${item2[1]}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div style="width: 500px;"><p>Seleccione un programa para ver la muestra asignada al mismo.</p></div>
                    </div>
                    <div id="filtro4" style="display: none;">
                        <p>Filtros</p>
                        <table>
                            <tr>
                                <td>
                                    <select  class="span3" id="select8" name="programas4" onchange="presionSubmitFiltro()">
                                        <option value="--">Seleccione Opción</option>
                                        <option value="Todos">Todos</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div style="width: 500px;"><p>Seleccione una opción para ver la muestra asignada.</p></div>
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


