<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
<script type="text/javascript">
    var itemsxpagina=10;
    function pageselectCallback(page_index, jq){
        var num_entries = $("#preguntas tr").length;
        for(var i=0;i<num_entries;i++)
        {
            $('#preguntas tr:eq('+i+')').css("display", "none");
        }
        var max_elem = Math.min((page_index+1) * itemsxpagina, num_entries);
        for(var i=page_index*itemsxpagina;i<max_elem;i++)
        {
            $('#preguntas tr:eq('+i+')').css("display", "table-row");
                     
        }  
        return false;
    }

    function initPagination() {
        // count entries inside the hidden content
        var num_entries = jQuery('#preguntas tr').length;
                
        // Create content inside pagination element
        $(".pagination").pagination(num_entries, {
            callback: pageselectCallback,
            items_per_page:itemsxpagina,
            num_display_entries:4,
            num_edge_entries:2,
            prev_text:"&larr; Anterior",
            next_text:"Siguiente &rarr;",
            prev_show_always:false,
            next_show_always:false
        });
    }
    $(document).ready(function(){
        initPagination();
    });

</script>

<script type="text/javascript">
    $(function(){
        var inst =$("#ins").val();  
        inst = inst.replace(/\n/gi,"<br/>");
        
        $("#insp").append(inst);
        
        
               
        var validator = $("#formResponderE").bind("invalid-form.validate", function() {
            alert("usted ha dejado de contestar " + validator.numberOfInvalids() + " preguntas, por favor contestelas todas.");
        })
        .validate({
            ignore: "", 
                
            submitHandler: function(){
                $.ajax({
                    type: 'POST', 
                    url: "<%=request.getContextPath()%>/formController3?action=responderE",
                    data: $("#formResponderE").serialize(),
                    beforeSend: function(){
                        $("div.ui-layout-center").append("<div class='page_loading'>"
                            +"<span>Enviando</span>"
                            +"<img src='css/images/loading.gif' style='margin-left:6px;'>"
                            +"</div>");
                    },
                    success: function(){
                        $(".page_loading").hide();
                        $("#myModalGracias").modal();
                        $('#myModalGracias').on('hidden', function () {
                            location = "<%=request.getContextPath()%>/#inicio"
                        })
                        
                    } //fin success
                }); //fin $.ajax    
            }
        });
        
        $("button[rel=popover1]")
        .popover({placement:'left'})
        .click(function(e){
            e.preventDefault();
    
            
            $(this).popover('hide');
            $(this).button('loading');
                
            $.ajax({
                type: 'POST', 
                url: "<%=request.getContextPath()%>/formController3?action=guardarE",
                data: $("#formResponderE").serialize(),
                success: function(){
                    $("button[rel=popover1]").button('reset');
                    marcacion = new Date() 
                    Hora = marcacion.getHours() 
                    Minutos = marcacion.getMinutes() 
                    Segundos = marcacion.getSeconds() 
                    if (Hora<=9)
                        Hora = "0" + Hora
                    if (Minutos<=9)
                        Minutos = "0" + Minutos
                    if (Segundos<=9)
                        Segundos = "0" + Segundos
                    var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
                    var Mes = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
                    var Hoy = new Date();
                    var Anio = Hoy.getFullYear();
                    var Fecha = Dia[Hoy.getDay()] + " "+ Hoy.getDate() + " de " + Mes[Hoy.getMonth()] + " de " + Anio + ", a las " + Hora + ":" + Minutos + ":" + Segundos;
                    $("#hora2").html(" " + Fecha);
                } //fin success
            })
            
        });
        
        $("button[rel=popover2]")
        .popover({placement:'left'});
    });
</script>
<style type="text/css">
    label.error{
        color:#B94A48;
    }
</style>
<div class="container">  
    <table class="table table-bordered table-striped" style="font-weight: bold;">
        <tbody>
            <tr>
                <td rowspan="3" style="width: 25%; text-align: center;"><img style="width: 224px; height: 80px" src="/AutoevaluacionInstitucional/css/LogoUdeC.png"></td>
                <td style="width: 50%; text-align: center;">UNIVERSIDAD DE CARTAGENA</td>
                <td style="width: 25%;">CÓDIGO: FO-DO/AA-002</td>
            </tr>
            <tr>
                <td style="width: 50%; text-align: center;">AUTOEVALUACIÓN Y ACREDITACIÓN</td>
                <td>VERSIÓN:00</td>
            </tr>
            <tr>
                <td style="width: 50%; text-align: center;">${encuesta.rowsByIndex[0][0]}</td>
                <td>FECHA:18/04/2012</td>
            </tr>
        </tbody>
    </table>

    <div class="row">
        <div class="span12">
            <h3>Objetivo:</h3>
            <p style="text-align: justify;">${encuesta.rowsByIndex[0][1]}</p>
        </div>

    </div>
    <div class="row">
        <div class="span12">
            <h3>Instrucciones:</h3>
            <textarea id="ins" style="display: none;" rows="9" class="span8">${encuesta.rowsByIndex[0][2]}</textarea>
            <p id="insp" style="text-align: justify;"></p>
        </div>
    </div>
    <form id="formResponderE" method="POST">
        <table id="preguntas" class="table table-striped table-condensed" style="width: 100%;">
            <tbody>

                <c:choose>
                    <c:when test="${respuestas == null}">
                        <c:forEach items="${preguntas.rowsByIndex}" var="pregunta" varStatus="status">
                            <c:choose>
                                <c:when test="${pregunta[2] != 'Elegir 1-5'}">
                                    <tr>
                                        <td>${status.count}</td>   
                                        <td><p>${pregunta[1]}</p></td>
                                        <td>
                                            <select id="pregunta${pregunta[0]}" name="pregunta${pregunta[0]}" class="span1 {required:true}">
                                                <option></option>  
                                                <option value="Si">Si</option>  
                                                <option value="No">No</option>  
                                            </select>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>${status.count}</td>   
                                        <td><p>${pregunta[1]}</p></td>
                                        <td>
                                            <select id="pregunta${pregunta[0]}" name="pregunta${pregunta[0]}" class="span1 {required:true}">
                                                <option></option>  
                                                <option value="0">0</option>  
                                                <option value="1">1</option>  
                                                <option value="2">2</option>  
                                                <option value="3">3</option>  
                                                <option value="4">4</option>  
                                                <option value="5">5</option>  
                                            </select>
                                        </td>
                                    </tr>
                                </c:otherwise>    
                            </c:choose>
                        </c:forEach>        
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${respuestas.rowsByIndex}" var="respuesta" varStatus="status">
                            <c:choose>
                                <c:when test="${respuesta[2] != 'Elegir 1-5'}">
                                    <tr>
                                        <td>${status.count}</td>   
                                        <td><p>${respuesta[1]}</p></td>
                                        <td>
                                            <select id="pregunta${respuesta[0]}" name="pregunta${respuesta[0]}" class="span1 {required:true}">
                                                <option></option>  
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == 'Si'}">
                                                        <option selected="selected" value="Si">Si</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="Si">Si</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == 'No'}">
                                                        <option selected="selected" value="No">No</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="No">No</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>${status.count}</td>   
                                        <td><p>${respuesta[1]}</p></td>
                                        <td>
                                            <select id="pregunta${respuesta[0]}" name="pregunta${respuesta[0]}" class="span1 {required:true}">
                                                <option></option>  
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == '0'}">
                                                        <option selected="selected" value="0">0</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="0">0</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == '1'}">
                                                        <option selected="selected" value="1">1</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="1">1</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == '2'}">
                                                        <option selected="selected" value="2">2</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="2">2</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == '3'}">
                                                        <option selected="selected" value="3">3</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="3">3</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == '4'}">
                                                        <option selected="selected" value="4">4</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="4">4</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${respuesta[3] == '5'}">
                                                        <option selected="selected" value="5">5</option>      
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="5">5</option>      
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                </c:otherwise>    
                            </c:choose>
                        </c:forEach>        
                    </c:otherwise>
                </c:choose>

            </tbody>
        </table>
        <div class="row"> 
            <div class="span6">
                <div class="pagination"></div>
            </div>
            <div class="span6">
                <div style="text-align: right;margin-top: 18px;">
                    <div>
                        <div class="span1" style="margin-left: 90px;">
                            <span class="label label-info span1" style="margin-left: 0px;">Guardado:</span>
                        </div>
                        <div class="span3" style="margin-left: 0px;">
                            <p class="help-block" id="hora2"></p>
                        </div>
                        <div class="span1" style="margin-left: 0px;">
                            <button class="btn" id="guardar" data-content="<p style='text-align: justify'>Guarda la encuesta sin salir de ella, de esta manera usted podr&aacute; seguir contestando la encuesta cuando desee.<p>" rel="popover1"  value="1" data-original-title="Guardar encuesta" type="button" data-loading-text="Guardando..." autocomplete="off">Guardar</button>
                        </div>
                        <div class="span1" style="margin-left: 0px;">  
                            <button class="btn btn-primary" data-content="<p style='text-align: justify'>Env&iacute;a la encuesta evaluada. Verifique que todas las preguntas han sido respondidas correctamente. Esta operación no se podrá deshacer.<p>" rel="popover2"  value="1" data-original-title="Enviar encuesta" type="submit">Enviar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>