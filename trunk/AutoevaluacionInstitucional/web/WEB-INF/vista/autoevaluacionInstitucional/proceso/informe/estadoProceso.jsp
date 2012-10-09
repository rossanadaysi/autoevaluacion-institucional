<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() { 
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
        $("#horaEstado").html(" " + Fecha);
        
        if(${aux_index2 == 2}){
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=recargarEstado",
                success: function(data){
                    $("#estado").empty();
                    $("#estado").html(data);
                }
            });
        }
        $("#actEnlaceEstado").click( function() {
            $("div.ui-layout-center").empty();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=estadoProcesoAI",
                success: function(data){             
                    $(".contenido").html(data);
                    setTimeout(function(){
                        $(".page_loading").hide();
                    },200)
                } //fin success
            }); //fin $.ajaxF          
            
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=recargarEstado",
                success: function(data){
                    $("#estado").empty();
                    $("#estado").html(data);
                }
            });
            
        }); //fin eventoClick #actEnlaceEstado
        
        $(".printEnlace").click( function() {
            $('#conte').jqprint();
            return false;
        }); 
    });                     
</script>
<div class="hero-unit">
    <a  class="span10 printEnlace" style="text-align: right; margin-left: 0px; text-align: right; cursor: pointer"><i class="icon-print"></i> Imprimir</a>  
    <div class="row">
        <div id="conte" class="span10">
            <br>
            <fieldset>
                <legend>
                    Estado del proceso en ejecución
                </legend>
                <c:if test="${aux_index2 != 3}">
                    <div class="span10" style="margin-left: 0px;">
                        <div class="span1" style="margin-left: 0px;"><span class="label label-info span1" id="spanActualizadoEstado" style="margin-left: 0px;">Actualizado</span></div>
                        <div class="span7" style="margin-left: 30px;"><p class="help-block" id="horaEstado"></p></div>
                        <div class="span2" style="margin-left: 30px; text-align: right;"><a style="cursor: pointer" id="actEnlaceEstado"><i class="icon-refresh"></i> Actualizar</a></div>
                    </div>
                </c:if>
                <p>
                    Detalle:
                </p>

                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Cierre</th>
                    <th>Programa</th>
                    <th>Informes</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${detailProceso.rowsByIndex}" var="item" varStatus="iter">
                            <tr> 
                                <td>${item[3]}</td>
                                <td>${item[1]}</td>
                                <td>${item[2]}</td>
                                <td>Autoevaluación Institucional</td>
                                <td>   
                                   <!-- <a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#informe1">Informe resultado por encuestas</a><br/>-->
                                    <a  id="informeEncuesta2"  href="<%=request.getContextPath()%>/#informeMatriz">Matriz de calidad por caracteristicas</a><br/>
                                    <a  id="informeMatrizFact"  href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de calidad por factores</a><br/>
                                    <a  id="encuestaAleatoria"  href="<%=request.getContextPath()%>/#encuestaAleatoria">Ver encuesta aleatoria respondida</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
                <p>Estado general del proceso:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${tabla1.rowsByIndex}" var="row" varStatus="iter">
                            <tr>  
                                <td>   
                                    <c:out value="${row[0]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[1]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[2]}%"/>
                                </td>
                                <td>   
                                    <c:out value="${row[3]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[4]}%"/>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>



                <p>Estado por fuente del proceso:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Fuente</th>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                ESTUDIANTE
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][0]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][1]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][0]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][1] * 100 /tabla2.rowsByIndex[0][0]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][0]-tabla2.rowsByIndex[0][1]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][0]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][1] * 100 /tabla2.rowsByIndex[0][0])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                DOCENTE
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][2]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][3]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][2]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][3] * 100 /tabla2.rowsByIndex[0][2]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][2]-tabla2.rowsByIndex[0][3]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][2]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][3] * 100 /tabla2.rowsByIndex[0][2])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                ADMINISTRATIVOS
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][4]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][5]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][4]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][5] * 100 /tabla2.rowsByIndex[0][4]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][4]-tabla2.rowsByIndex[0][5]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][4]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][5] * 100 /tabla2.rowsByIndex[0][4])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                DIRECTIVOS
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][6]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][7]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][6]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][7] * 100 /tabla2.rowsByIndex[0][6]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][6]-tabla2.rowsByIndex[0][7]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][6]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][7] * 100 /tabla2.rowsByIndex[0][6])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                EGRESADOS
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][8]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][9]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][8]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][9] * 100 /tabla2.rowsByIndex[0][8]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][8]-tabla2.rowsByIndex[0][9]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][8]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][9] * 100 /tabla2.rowsByIndex[0][8])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>

                        <tr>
                            <td>
                                EMPLEADORES
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][10]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][11]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][10]!=0}">

                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][11] * 100 /tabla2.rowsByIndex[0][10]}"/>%
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    0%
                                </c:otherwise>
                            </c:choose>
                            <td>
                                ${tabla2.rowsByIndex[0][10]-tabla2.rowsByIndex[0][11]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][10]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][11] * 100 /tabla2.rowsByIndex[0][10])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                GUBERNAMENTALES
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][12]}
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][13]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][12]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${tabla2.rowsByIndex[0][13] * 100 /tabla2.rowsByIndex[0][12]}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${tabla2.rowsByIndex[0][12]-tabla2.rowsByIndex[0][13]}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${tabla2.rowsByIndex[0][12]!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 - (tabla2.rowsByIndex[0][13] * 100 /tabla2.rowsByIndex[0][12])}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </tbody>
                </table>
                <ul class="nav nav-tabs" id="Fuentes">
                    <li class="active"><a href="#estudiantes" data-toggle="tab">Estudiantes</a></li>
                    <li><a href="#docentes" data-toggle="tab">Docentes</a></li>
                    <li><a href="#egresados" data-toggle="tab">Egresados</a></li>
                    <li><a href="#administrativos" data-toggle="tab">Administrativos</a></li>
                    <li><a href="#directivos" data-toggle="tab">Directivos</a></li>
                    <li><a href="#empleadores" data-toggle="tab">Empleadores</a></li>
                    <li><a href="#gubernamentales" data-toggle="tab">A. Gubernamentales</a></li>
                </ul> 

                <div class="tab-content">
                    <div class="tab-pane active" id="estudiantes">
                        <div class="accordion" id="accordion2">
                            <c:set var="fac" value=""></c:set>
                            <c:forEach items="${estudiantesPorFac.rowsByIndex}" var="itemEstudPorFac" varStatus="iterEstPorFac">
                                <c:set var="porce" value="${itemEstudPorFac[4]*100/itemEstudPorFac[3]}"></c:set>
                                <c:choose>
                                    <c:when test="${itemEstudPorFac[0]!=fac && itemEstudPorFac[0]!=null}">
                                        <c:set var="fac" value="${itemEstudPorFac[0]}"></c:set>


                                        <div class="accordion-group">
                                            <div class="accordion-heading" >
                                                <table style="width:100%">
                                                    <tbody>
                                                        <tr >
                                                            <td><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse${itemEstudPorFac[5]}">
                                                                    ${itemEstudPorFac[0]}
                                                                </a></td>   
                                                            <td id="heading${itemEstudPorFac[5]}" style="width:300px;text-align: right"></td>   
                                                        </tr>

                                                    </tbody>

                                                </table>
                                            </div>
                                            <div id="collapse${itemEstudPorFac[5]}" class="accordion-body collapse">
                                                <div class="accordion-inner">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>Programa</th>
                                                                <th>Total Seleccionados</th>
                                                                <th>Total respondidos</th>
                                                                <th>Porcentaje de cumplimiento</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>${itemEstudPorFac[2]}</td>
                                                                <td>${itemEstudPorFac[3]}</td>
                                                                <td>${itemEstudPorFac[4]}</td>
                                                                <td><c:choose>
                                                                        <c:when test="${porce<=25.0}">
                                                                            <div class="progress progress-danger progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porce>25.0 && porce<=50.0}">
                                                                            <div class="progress progress-warning progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porce>50.0 && porce<=75.0}">
                                                                            <div class="progress progress-info progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="progress progress-success progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose></td>
                                                            </tr>


                                                        </c:when>
                                                        <c:when test="${itemEstudPorFac[0]==fac && itemEstudPorFac[2]!=null}">
                                                            <tr>
                                                                <td>${itemEstudPorFac[2]}</td>
                                                                <td>${itemEstudPorFac[3]}</td>
                                                                <td>${itemEstudPorFac[4]}</td>

                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${porce<=25.0}">
                                                                            <div class="progress progress-danger progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porce>25.0 && porce<=50.0}">
                                                                            <div class="progress progress-warning progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porce>50.0 && porce<=75.0}">
                                                                            <div class="progress progress-info progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="progress progress-success progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porce}%" style="width: ${porce}%"></div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>

                                                        </c:when>
                                                        <c:when test="${itemEstudPorFac[0]==null}">
                                                        </c:when>
                                                        <c:otherwise>
                                                        <script type="text/javascript">
                                                            $(function(){
                                                                $("#heading${itemEstudPorFac[5]}").prev().children("a").append(" (${itemEstudPorFac[3]}/${itemEstudPorFac[4]})");
                                                            <c:choose>
                                                                <c:when test="${porce<=25.0}">
                                                                        $("#heading${itemEstudPorFac[5]}").append("<div class='progress progress-danger progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porce}%' style='width: ${porce}%'></div>"
                                                                            +"</div>")   
                                                                            
                                                                </c:when>
                                                                <c:when test="${porce>25.0 && porce<=50.0}">
                                                                        $("#heading${itemEstudPorFac[5]}").append("<div class='progress progress-warning progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porce}%' style='width: ${porce}%'></div>"
                                                                            +"</div>")   
                                                                            
                                                                </c:when>
                                                                <c:when test="${porce>50.0 && porce<=75.0}">
                                                                        $("#heading${itemEstudPorFac[5]}").append("<div class='progress progress-info progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porce}%' style='width: ${porce}%'></div>"
                                                                            +"</div>")
                                                                    
                                                                </c:when>
                                                                <c:otherwise>
                                                                        $("#heading${itemEstudPorFac[5]}").append("<div class='progress progress-success progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porce}%' style='width: ${porce}%'></div>"
                                                                            +"</div>")
                                                                        
                                                                </c:otherwise>
                                                            </c:choose>
                                                                });
                                                        </script>
                                                        </tbody></table>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>    
                                </c:choose>
                            </c:forEach>



                        </div>              
                    </div>
                    <div class="tab-pane" id="docentes">
                        <div class="accordion" id="accordion2D">
                            <c:set var="facD" value=""></c:set>
                            <c:forEach items="${docentesPorFac.rowsByIndex}" var="itemDocePorFac" varStatus="iterDocePorFac">
                                <c:set var="porceD" value="${itemDocePorFac[4]*100/itemDocePorFac[3]}"></c:set>
                                <c:choose>
                                    <c:when test="${itemDocePorFac[0]!=facD && itemDocePorFac[0]!=null}">
                                        <c:set var="facD" value="${itemDocePorFac[0]}"></c:set>

                                        <div class="accordion-group">
                                            <div class="accordion-heading" >
                                                <table style="width:100%">
                                                    <tbody>
                                                        <tr >
                                                            <td><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2D" href="#collapseD${itemDocePorFac[5]}">
                                                                    ${itemDocePorFac[0]}
                                                                </a></td>   
                                                            <td id="headingD${itemDocePorFac[5]}" style="width:300px;text-align: right"></td>   
                                                        </tr>

                                                    </tbody>

                                                </table>
                                            </div>
                                            <div id="collapseD${itemDocePorFac[5]}" class="accordion-body collapse">
                                                <div class="accordion-inner">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>Programa</th>
                                                                <th>Total Seleccionados</th>
                                                                <th>Total respondidos</th>
                                                                <th>Porcentaje de cumplimiento</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>${itemDocePorFac[2]}</td>
                                                                <td>${itemDocePorFac[3]}</td>
                                                                <td>${itemDocePorFac[4]}</td>
                                                                <td><c:choose>
                                                                        <c:when test="${porceD<=25.0}">
                                                                            <div class="progress progress-danger progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceD>25.0 && porceD<=50.0}">
                                                                            <div class="progress progress-warning progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceD>50.0 && porceD<=75.0}">
                                                                            <div class="progress progress-info progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="progress progress-success progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose></td>
                                                            </tr>


                                                        </c:when>
                                                        <c:when test="${itemDocePorFac[0]==facD && itemDocePorFac[2]!=null}">
                                                            <tr>
                                                                <td>${itemDocePorFac[2]}</td>
                                                                <td>${itemDocePorFac[3]}</td>
                                                                <td>${itemDocePorFac[4]}</td>

                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${porceD<=25.0}">
                                                                            <div class="progress progress-danger progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceD>25.0 && porceD<=50.0}">
                                                                            <div class="progress progress-warning progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceD>50.0 && porceD<=75.0}">
                                                                            <div class="progress progress-info progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="progress progress-success progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceD}%" style="width: ${porceD}%"></div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>

                                                        </c:when>
                                                        <c:when test="${itemDocePorFac[0]==null}">
                                                        </c:when>
                                                        <c:otherwise>
                                                        <script type="text/javascript">
                                                            $(function(){
                                                                $("#headingD${itemDocePorFac[5]}").prev().children("a").append(" (${itemDocePorFac[3]}/${itemDocePorFac[4]})");
                                                            <c:choose>
                                                                <c:when test="${porceD<=25.0}">
                                                                        $("#headingD${itemDocePorFac[5]}").append("<div class='progress progress-danger progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceD}%' style='width: ${porceD}%'></div>"
                                                                            +"</div>")   
                                                                            
                                                                </c:when>
                                                                <c:when test="${porceD>25.0 && porceD<=50.0}">
                                                                        $("#headingD${itemDocePorFac[5]}").append("<div class='progress progress-warning progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceD}%' style='width: ${porceD}%'></div>"
                                                                            +"</div>")   
                                                                            
                                                                </c:when>
                                                                <c:when test="${porceD>50.0 && porceD<=75.0}">
                                                                        $("#headingD${itemDocePorFac[5]}").append("<div class='progress progress-info progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceD}%' style='width: ${porceD}%'></div>"
                                                                            +"</div>")
                                                                    
                                                                </c:when>
                                                                <c:otherwise>
                                                                        $("#headingD${itemDocePorFac[5]}").append("<div class='progress progress-success progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceD}%' style='width: ${porceD}%'></div>"
                                                                            +"</div>")
                                                                        
                                                                </c:otherwise>
                                                            </c:choose>
                                                                });
                                                        </script>
                                                        </tbody></table>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>    
                                </c:choose>
                            </c:forEach>



                        </div>              
                    </div>
                    <div class="tab-pane" id="egresados">
                        <div class="accordion" id="accordion2Eg">
                            <c:set var="facEg" value=""></c:set>
                            <c:forEach items="${egresadosPorFac.rowsByIndex}" var="itemEgrePorFac" varStatus="iterEgrePorFac">
                                <c:set var="porceEg" value="${itemEgrePorFac[4]*100/itemEgrePorFac[3]}"></c:set>
                                <c:choose>
                                    <c:when test="${itemEgrePorFac[0]!=facEg && itemEgrePorFac[0]!=null}">
                                        <c:set var="facEg" value="${itemEgrePorFac[0]}"></c:set>

                                        <div class="accordion-group">
                                            <div class="accordion-heading" >
                                                <table style="width:100%">
                                                    <tbody>
                                                        <tr >
                                                            <td><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2Eg" href="#collapseEg${itemEgrePorFac[5]}">
                                                                    ${itemEgrePorFac[0]}
                                                                </a></td>   
                                                            <td id="headingEg${itemEgrePorFac[5]}" style="width:300px;text-align: right"></td>   
                                                        </tr>

                                                    </tbody>

                                                </table>
                                            </div>
                                            <div id="collapseEg${itemEgrePorFac[5]}" class="accordion-body collapse">
                                                <div class="accordion-inner">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>Programa</th>
                                                                <th>Total Seleccionados</th>
                                                                <th>Total respondidos</th>
                                                                <th>Porcentaje de cumplimiento</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>${itemEgrePorFac[2]}</td>
                                                                <td>${itemEgrePorFac[3]}</td>
                                                                <td>${itemEgrePorFac[4]}</td>
                                                                <td><c:choose>
                                                                        <c:when test="${porceEg<=25.0}">
                                                                            <div class="progress progress-danger progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceEg>25.0 && porceEg<=50.0}">
                                                                            <div class="progress progress-warning progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceEg>50.0 && porceEg<=75.0}">
                                                                            <div class="progress progress-info progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="progress progress-success progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose></td>
                                                            </tr>


                                                        </c:when>
                                                        <c:when test="${itemEgrePorFac[0]==facEg && itemEgrePorFac[2]!=null}">
                                                            <tr>
                                                                <td>${itemEgrePorFac[2]}</td>
                                                                <td>${itemEgrePorFac[3]}</td>
                                                                <td>${itemEgrePorFac[4]}</td>

                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${porceEg<=25.0}">
                                                                            <div class="progress progress-danger progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceEg>25.0 && porceEg<=50.0}">
                                                                            <div class="progress progress-warning progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:when test="${porceEg>50.0 && porceEg<=75.0}">
                                                                            <div class="progress progress-info progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="progress progress-success progress-striped active">
                                                                                <div class="bar" rel="tooltip" title="${porceEg}%" style="width: ${porceEg}%"></div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>

                                                        </c:when>
                                                        <c:when test="${itemEgrePorFac[0]==null}">
                                                        </c:when>
                                                        <c:otherwise>
                                                        <script type="text/javascript">
                                                            $(function(){
                                                                $("#headingEg${itemEgrePorFac[5]}").prev().children("a").append(" (${itemEgrePorFac[3]}/${itemEgrePorFac[4]})");
                                                            <c:choose>
                                                                <c:when test="${porceEg<=25.0}">
                                                                        $("#headingEg${itemEgrePorFac[5]}").append("<div class='progress progress-danger progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceEg}%' style='width: ${porceEg}%'></div>"
                                                                            +"</div>")   
                                                                            
                                                                </c:when>
                                                                <c:when test="${porceEg>25.0 && porceEg<=50.0}">
                                                                        $("#headingEg${itemEgrePorFac[5]}").append("<div class='progress progress-warning progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceEg}%' style='width: ${porceEg}%'></div>"
                                                                            +"</div>")   
                                                                            
                                                                </c:when>
                                                                <c:when test="${porceEg>50.0 && porceEg<=75.0}">
                                                                        $("#headingEg${itemEgrePorFac[5]}").append("<div class='progress progress-info progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceEg}%' style='width: ${porceEg}%'></div>"
                                                                            +"</div>")
                                                                    
                                                                </c:when>
                                                                <c:otherwise>
                                                                        $("#headingEg${itemEgrePorFac[5]}").append("<div class='progress progress-success progress-striped active' style='margin-bottom: 0px;'>"
                                                                            +"<div class='bar' rel='tooltip' title='${porceEg}%' style='width: ${porceEg}%'></div>"
                                                                            +"</div>")
                                                                        
                                                                </c:otherwise>
                                                            </c:choose>
                                                                });
                                                        </script>
                                                        </tbody></table>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>    
                                </c:choose>
                            </c:forEach>



                        </div>              
                    </div>
                    <div class="tab-pane" id="administrativos">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>${administrativosPorFac.rowsByIndex[0][0]}</th>
                                    <th>Total Seleccionados</th>
                                    <th>Total respondidos</th>
                                    <th>Porcentaje de cumplimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${administrativosPorFac.rowsByIndex}" var="itemAdmPorFac">
                                    <c:set var="porceAd" value="${itemAdmPorFac[3]*100/itemAdmPorFac[2]}"></c:set>
                                    <tr>
                                        <td>${itemAdmPorFac[1]}</td>
                                        <td>${itemAdmPorFac[2]}</td>
                                        <td>${itemAdmPorFac[3]}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${porceAd<=25.0}">
                                                    <div class="progress progress-danger progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAd}%" style="width: ${porceAd}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceAd>25.0 && porceAd<=50.0}">
                                                    <div class="progress progress-warning progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAd}%" style="width: ${porceAd}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceAd>50.0 && porceAd<=75.0}">
                                                    <div class="progress progress-info progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAd}%" style="width: ${porceAd}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="progress progress-success progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAd}%" style="width: ${porceAd}%"></div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody></table>
                    </div>
                    <div class="tab-pane" id="directivos">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>${directivosPorFac.rowsByIndex[0][0]}</th>
                                    <th>Total Seleccionados</th>
                                    <th>Total respondidos</th>
                                    <th>Porcentaje de cumplimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${directivosPorFac.rowsByIndex}" var="itemDirPorFac">
                                    <c:set var="porceDir" value="${itemDirPorFac[3]*100/itemDirPorFac[2]}"></c:set>
                                    <tr>
                                        <td>${itemDirPorFac[1]}</td>
                                        <td>${itemDirPorFac[2]}</td>
                                        <td>${itemDirPorFac[3]}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${porceDir<=25.0}">
                                                    <div class="progress progress-danger progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceDir}%" style="width: ${porceDir}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceDir>25.0 && porceDir<=50.0}">
                                                    <div class="progress progress-warning progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceDir}%" style="width: ${porceDir}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceDir>50.0 && porceDir<=75.0}">
                                                    <div class="progress progress-info progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceDir}%" style="width: ${porceDir}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="progress progress-success progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceDir}%" style="width: ${porceDir}%"></div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody></table>
                    </div>
                    <div class="tab-pane" id="empleadores">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>${empleadoresPorFac.rowsByIndex[0][0]}</th>
                                    <th>Total Seleccionados</th>
                                    <th>Total respondidos</th>
                                    <th>Porcentaje de cumplimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${empleadoresPorFac.rowsByIndex}" var="itemEmpPorFac">
                                    <c:set var="porceEm" value="${itemEmpPorFac[3]*100/itemEmpPorFac[2]}"></c:set>
                                    <tr>
                                        <td>${itemEmpPorFac[1]}</td>
                                        <td>${itemEmpPorFac[2]}</td>
                                        <td>${itemEmpPorFac[3]}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${porceEm<=25.0}">
                                                    <div class="progress progress-danger progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceEm}%" style="width: ${porceEm}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceEm>25.0 && porceEm<=50.0}">
                                                    <div class="progress progress-warning progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceEm}%" style="width: ${porceEm}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceEm>50.0 && porceEm<=75.0}">
                                                    <div class="progress progress-info progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceEm}%" style="width: ${porceEm}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="progress progress-success progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceEm}%" style="width: ${porceEm}%"></div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody></table>
                    </div>
                    <div class="tab-pane" id="gubernamentales">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>${agenciasPorFac.rowsByIndex[0][0]}</th>
                                    <th>Total Seleccionados</th>
                                    <th>Total respondidos</th>
                                    <th>Porcentaje de cumplimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${agenciasPorFac.rowsByIndex}" var="itemAgePorFac">
                                    <c:set var="porceAg" value="${itemAgePorFac[3]*100/itemAgePorFac[2]}"></c:set>
                                    <tr>
                                        <td>${itemAgePorFac[1]}</td>
                                        <td>${itemAgePorFac[2]}</td>
                                        <td>${itemAgePorFac[3]}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${porceAg<=25.0}">
                                                    <div class="progress progress-danger progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAg}%" style="width: ${porceAg}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceAg>25.0 && porceAg<=50.0}">
                                                    <div class="progress progress-warning progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAg}%" style="width: ${porceAg}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${porceAg>50.0 && porceAg<=75.0}">
                                                    <div class="progress progress-info progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAg}%" style="width: ${porceAg}%"></div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="progress progress-success progress-striped active">
                                                        <div class="bar" rel="tooltip" title="${porceAg}%" style="width: ${porceAg}%"></div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody></table>
                    </div>
                </div>            



            </fieldset>
        </div>
    </div>
</div>                        
<script type="text/javascript">
    $(function(){
        $(".bar").tooltip();
    })
</script>