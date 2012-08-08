<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    
    $(document).ready(function() {$('#botonGenerarMuestra').button()})
 
    function presionSubmitGenerarMuestra()
    {
        $("#filtro").hide();
        $("#select3 option:eq(0)").attr("selected", "selected");
        $("#select4 option:eq(0)").attr("selected", "selected");
        $("#select5 option:eq(0)").attr("selected", "selected");
        $("#select6 option:eq(0)").attr("selected", "selected");
        $("#select7 option:eq(0)").attr("selected", "selected");
        $("#select8 option:eq(0)").attr("selected", "selected");
        $("#select9 option:eq(0)").attr("selected", "selected");
        $("#select10 option:eq(0)").attr("selected", "selected");
            
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=generarMuestra",
            data: $("#formAsigMue").serialize(),
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestraAI",
                    data: $("#formAsigMue").serialize(),
                    success: function(data){
                        $.ajax({
                            type: 'POST',
                            url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestraAI",
                            success: function(data){
                                $("#cargando").hide();
                                setTimeout(function(){
                                    $(".page_loading").hide();
                                    $("#filtro").html(data);
                                },200);     
                                
                            }
                        })
                    } //fin success                              
                }); //fin $.ajax
            } //fin success                               
        });
    }
    
    
    function presionSubmitConfigurarFormula()
    {  
        var formularioA=$('#formAsigMue').serialize();
        var formularioB=$('#formAsigMue2').serialize();
        var Todo=formularioA+"&"+formularioB;
          
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=configurarParametrosMuestraAI",
            data: Todo,
            success: function(data){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=configurarParametrosMuestraAI",
                    success: function(data){
                        setTimeout(function(){
                            $(".page_loading").hide();
                        },200);         
                        $("#myModalConfigurarMuestra").html(data);
                        $('#myModalConfigurarMuestra').modal();
                    }
                })
            }
        })
    }
    
    
    function presionSubmitFormula()
    {
        
        $("#resultados2").hide();
        $("#resultados3").hide();
        $("#resultados4").hide();
        $("#enlace").hide();
        $("#conglomerado").hide();
        $("#filtro").hide();
        $("#botonGenerarMuestra").hide();
        $("#botonCalcularMuestra2").hide();
        $("#botonCalcularMuestra").show();
            
        if($("#select2 option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#filtro").hide();
            $("#conglomerado").hide();
        }
        else{
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=conglomerado",
                success: function(data){
                    $("#conglomerado").html(data);
                    setTimeout(function(){
                        $(".page_loading").hide();
                        $("#conglomerado").show();
                    },200);     
                    
                }
            })
            $("#conglomerado").show();
            // $("#enlace").show();
        }
    }
    
    function presionSubmitConglomerado()
    {
        
        $("#resultados2").hide();
        $("#resultados3").hide();
        $("#resultados4").hide();
        $("#enlace").hide();
        $("#filtro").hide();
        $("#botonGenerarMuestra").hide();
        $("#botonCalcularMuestra2").hide();
        $("#botonCalcularMuestra").show();
         
        if($("#selectConglomerado option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#filtro").hide();
        }   
        else if($("#selectConglomerado option:selected").val() == "nuevoCriterio"){
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=nuevoConglomeradoAI",
                data: $("#formAsigMue").serialize(),
                success: function(data){
                    $("#myModalNuevoConglomerado").html(data);
                    setTimeout(function(){
                        $(".page_loading").hide();
                    },200);     
                    $('#myModalNuevoConglomerado').modal({
                        keyboard: false
                    })
                    $('#myModalNuevoConglomerado').on('hide', function () {
                        $("#selectConglomerado option:eq(0)").attr("selected", "selected");
                        $("#enlace").hide();
                        $(".page_loading").hide();
                    });
                }
            })
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
            
        if($("#select2 option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#filtro").hide();
        }
        else{
            var formularioA=$('#formAsigMue').serialize();
            var formularioB=$('#formAsigMue2').serialize();
            var formularioC=$('#formAsigMue3').serialize();
            var Todo=formularioA+"&"+formularioB;
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=calcularMuestraAI",
                data: Todo,
                success: function(){
                    $.ajax({
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/ControllerAI?action=muestraCalculada",
                        success: function(data){
                            $("#resultados3").html(data);
                            setTimeout(function(){
                                $(".page_loading").hide();
                                $("#resultados3").show(); 
                                $("#resultados2").hide()
                                $("#enlace").show();
                            },200);     
                            
                        }
                    })
                } //fin success                                          
            }); //fin $.ajax
        }
        
    }
    
        
    function presionSubmitFuente()
    {
        $("#inputAux").attr('value', 'automatic');
        if($("#select option:selected").val() == "--"){
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#enlace").hide();
            $("#conglomerado").hide();
            $("#filtro").hide();
            $("#resultadoAlert").hide();
            $("#infoMuestra").hide();
            $("#formula").hide();
           
        }
        else{
            $("#select2 option:eq(0)").attr("selected", "selected");
            $("#select3 option:eq(0)").attr("selected", "selected");
            $("#select4 option:eq(0)").attr("selected", "selected");
            $("#select5 option:eq(0)").attr("selected", "selected");
            $("#select6 option:eq(0)").attr("selected", "selected");
            $("#select7 option:eq(0)").attr("selected", "selected");
            $("#select8 option:eq(0)").attr("selected", "selected"); 
            $("#select9 option:eq(0)").attr("selected", "selected");
            $("#select10 option:eq(0)").attr("selected", "selected");
            
            $("#resultados2").hide();
            $("#resultados3").hide();
            $("#resultados4").hide();
            $("#formula").hide();
            $("#enlace").hide();
            $("#conglomerado").hide();
            $("#filtro").hide();
            $("#resultadoAlert").hide();
            $("#infoMuestra").hide();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestraAI",
                data: $("#formAsigMue").serialize(),
                success: function(data){
                    $.ajax({
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestraAI",
                        success: function(data){
                            setTimeout(function(){
                                $(".page_loading").hide();
                                $("#filtro").html(data); 
                            },200);     
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
                        setTimeout(function(){
                            $(".page_loading").hide();
                            $("#resultados4").show(); 
                        },200);     
                            
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
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra3AI",
                    success: function(data){
                        $("#resultados4").hide();
                        $("#resultados4").html(data);
                        $(".contenido").show(200, function(){
                            $(".page_loading").hide();
                            $("#resultados4").show();
                        })
                        
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
    $("#checkbox2").change(function(){
        if( $(this).is(':checked')){
            $("#metodo").attr('value', 'aleatorio');
        }else{
            $("#metodo").attr('value', 'normal');
        }  
    });
       
</script>
<br>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form  id="formAsigMue" method="post">
                <fieldset>
                    <legend>
                        Asignación de Muestra
                    </legend>
                    <div  style="border: 1px solid #FFF;">
                        <table>
                            <tbody>
                                <tr>
                                    <td class="span8">
                                        <p>Fuente</p>
                                        <select class="span3" id="select" name="fuente" onchange="presionSubmitFuente()">
                                            <option value="--">Seleccione una Fuente</option>
                                            <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
                                                <option value="${item2[0]}">${item2[1]}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="span6" valign="top">
                                        <div class="span6">
                                            <div  class="span6" id="resultados3" class="accordion" style="position: absolute;"></div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div id="infoMuestra" style="display: none">
                            <div class="alert alert-error" >
                                <a class="close">×</a>
                                <strong>Información!</strong>
                                La muestra no fue asignada para la fuente seleccionada durante el proceso de configuración.
                            </div>
                        </div>
                        <div id="resultadoAlert" style="display: none">
                            <div class="alert alert-info">
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
                        <div id="conglomerado" style="display: none;">
                        </div>
                        <div id="enlace" style="display: none;">
                            <button style="display: none" id="botonCalcularMuestra2" class="btn btn-secundary" onclick="presionSubmitCalcularMuestra()" type="button">Calcular Muestra</button>
                            <button id="botonCalcularMuestra" class="btn btn-primary" onclick="presionSubmitCalcularMuestra()" type="button">Calcular Muestra</button>
                            <button id="botonConfigurarFormula" class="btn btn-secundary" onclick="presionSubmitConfigurarFormula()" type="button">Configurar Parametros de Fórmula</button>
                            <p class="help-block">Utilize la opción de configurar parámetro de fórmula si desea aginar tamaño de población  manualmente.</p>
                            <br>
                            <div style="display: none" id="botonGenerarMuestra">
                                <button  class="btn btn-primary" onclick="presionSubmitGenerarMuestra()" data-loading-text="loading..." type="button">Generar Muestra</button>
                                <br> <input type="checkbox" id="checkbox2"> Generar Usuarios Aleatorios.<br>
                                <p id="help1" class="help-block">Active ésta opción si desea generar usuarios y contraseñas aleatorias. Ésta opción puede tardar varios segundos.</p>
                                <input type="text" id="metodo" name="metodo"  style="display: none"  value="normal">
                            </div>
                            <br>
                        </div>
                        <div class="alert alert-info" id="cargando" style="display: none;" class="span10">
                            <a class="close">×</a>
                            <img src="<%=request.getContextPath()%>/css/images/wait.gif" style="float:left; padding: 1px 10px 0 0;">
                            Generando Muestra...
                        </div>
                        <div id="filtro" style="display: none;"></div>
                        <div  id="resultados2"></div>
                        <div  class="span10">
                            <div id="resultados4" class="accordion" style="margin-left: -30px;"></div> 
                        </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>


