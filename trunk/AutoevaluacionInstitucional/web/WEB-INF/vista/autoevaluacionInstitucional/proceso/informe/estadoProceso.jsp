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
        
        $("#actEnlaceEstado").click( function() {
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
        }); //fin $.ajax
        
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
            </fieldset>
        </div>
    </div>
</div>    
