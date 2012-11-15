<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>
<script type="text/javascript">
    $(function () {
        var chart;
        var chart2 = new Array(${detallePregunta.getRowCount()});
        var pregunta ="${detallePregunta.getRowsByIndex()[0][0]}".split(" ");
        var max = 70;
        var acom=0;
        var finali = "";
        for(var i=0;i<pregunta.length;i++){
            acom+=pregunta[i].length;
            if(acom<max){
                finali += pregunta[i]+" ";
            }else{
                acom=0;
                finali += "<br/>"+pregunta[i]+" ";
            }
        }
                            
        $(document).ready(function() {
            $('.tool').tooltip().click(function(e){
                $(this).tooltip('hide');
            })
            
            
            <c:forEach items="${detallePregunta.rowsByIndex}" var="encuesta" varStatus="status" >
                chart2[${status.index}] = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container${encuesta[4]}',
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                                
                    },
                    title: {
                        text: null
                    },
                    subtitle: {
                        text: '${encuesta[1]}'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                color: '#000000',
                                connectorColor: '#000000',
                                formatter: function() {
                                    var igv = this.percentage;
                                    igv = igv.toFixed(2);
                                    return '<b>'+ this.point.name +'</b>: '+ igv +' %';
                                }
                            }
                        }
                    },
                    tooltip: {
                        formatter: function() {
                            return ''+
                                this.point.name +': '+ this.y +' personas';
                        }
                    },
                    series: [{
                            type: 'pie',
                            name: 'Personas',
                            data: [
                                ['0',  ${encuesta[5]}],
                                ['1',  ${encuesta[6]}],
                                ['2',  ${encuesta[7]}],
                                ['3',  ${encuesta[8]}],
                                ['4',  ${encuesta[9]}],
                                {
                                    name: '5',
                                    y: ${encuesta[10]},
                                    sliced: true,
                                    selected: true
                                },
    
                            ]
                        }]
                    
                    
                });        
       
       
                    
    </c:forEach>
            
            
            
            
            
            
            
            
            
            
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'grafica',
                    type: 'column',
                    margin: [ 50, 30, 100, 50]
                },
                title: {
                    text: ""+ finali  +""
                },

                xAxis: {
                    categories: [
    <c:forEach items="${detallePregunta.rowsByIndex}" var="detalleP" varStatus="status">
        <c:choose>
            <c:when test="${detallePregunta.getRowCount()!=status.index+1}">
                                    '${detalleP[1]}',
            </c:when>
            <c:otherwise>
                                    '${detalleP[1]}'
            </c:otherwise>
        </c:choose>             
            
    </c:forEach>
                        ],
                        
                        labels: {
                            formatter: function() {
                                return this.value;
                            },
                            align: 'right',
                            style: {
                                fontSize: '10px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        }
                    },
                    
                    yAxis: {
                        min: 0,
                        max: 5,
                        title: {
                            text: 'Promedio de respuesta'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    tooltip: {
                        formatter: function() {
                            return '<b>'+ this.x  +'</b><br/>'+
                                'Promedio de respuestas: '+ Highcharts.numberFormat(this.y, 2) +
                                '';
                        }
                    },
                    series: [{
                            name: 'Encuesta',
                            data: [
    <c:forEach items="${detallePregunta.rowsByIndex}" var="detalleP2" varStatus="status33">
        <c:choose>
            <c:when test="${detallePregunta.getRowCount()!=status33.index+1}">
                <c:choose>
                    <c:when test="${detalleP2[2]>=4.5}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#89A54E'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<4.5 && detalleP2[2]>=4.0}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#80699B'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<4.0 && detalleP2[2]>=3.0}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#3D96AE'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<3.0 && detalleP2[2]>=2.0}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#DB843D'
                                                },
                                                
                    </c:when>
                    <c:otherwise>
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#AA4643'
                                                },
                    </c:otherwise>
                </c:choose>
                                            
                                            
                                            
                
                     
                
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${detalleP2[2]>=4.5}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#89A54E'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<4.5 && detalleP2[2]>=4.0}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#80699B'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<4.0 && detalleP2[2]>=3.0}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#3D96AE'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<3.0 && detalleP2[2]>=2.0}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#DB843D'
                                                }
                                                
                    </c:when>
                    <c:otherwise>
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#AA4643'
                                                }
                    </c:otherwise>
                </c:choose>
                
                
                                        
                
        
            </c:otherwise>
        </c:choose>             
            
    </c:forEach>
                                
                                
                            ],
                            
                            dataLabels: {
                                enabled: true,
                                rotation: -90,
                                color: '#FFFFFF',
                                align: 'right',
                                x: -3,
                                y: 10,
                                formatter: function() {
                                    return this.y;
                                },
                                style: {
                                    fontSize: '13px',
                                    fontFamily: 'Verdana, sans-serif'
                                }
                            }
                        }]
                });
            });
    
        });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <legend>Pregunta: ${detallePregunta.getRowsByIndex()[0][0]}</legend>
            <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatriz">Matriz de Calidad de Características</a> <span class="divider">/</span></li>
                <li><a class="tool" data-placement="top" rel="tooltip" data-original-title="${detalleCaracteristica.rowsByIndex[0][7]}" href="#detalleFactor&${detalleCaracteristica.rowsByIndex[0][5]}">Factor ${detalleCaracteristica.rowsByIndex[0][5]}</a> <span class="divider">/</span></li>
                <li><a class="tool" data-placement="top" rel="tooltip" data-original-title="${detalleCaracteristica.rowsByIndex[0][1]}" href="#detalleCaracteristica&${detalleCaracteristica.rowsByIndex[0][0]}" data="${detalleCaracteristica.rowsByIndex[0][1]}">Característica ${detalleCaracteristica.rowsByIndex[0][0]}</a><span class="divider">/</span></li>
                <li><a class="tool" data-placement="top" rel="tooltip" data-original-title="${detalleIndicador.rowsByIndex[0][1]}" href="#detalleIndicador&${detalleIndicador.rowsByIndex[0][0]}" data="${detalleIndicador.rowsByIndex[0][1]}">Indicador ${detalleIndicador.rowsByIndex[0][7]}</a><span class="divider">/</span></li>
                <li class="active tool" data-placement="top" rel="tooltip" data-original-title="${detallePregunta.rowsByIndex[0][0]}">Pregunta ${detallePregunta.rowsByIndex[0][3]}</li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${detallePregunta.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Encuesta</th>
                        <th>Promedio respuesta</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detallePregunta.rowsByIndex}" var="row" varStatus="iter">
                                <tr>
                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td style="text-align: left">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <div class="btn-group pull-right">
                                                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                                        ${row[2]}
                                                    </a>
                                                    <ul class="dropdown-menu" style="padding-top: 0px;">
                                                        <div id="container${row[4]}" style="min-width: 850px; height: 400px; margin: 0 auto"></div>
                                                    </ul>
                                                </div>
                                            </td>

                                        </c:when>
                                        <c:otherwise>
                                            <td style="text-align: left">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <div class="btn-group pull-right">
                                                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                                        ${row[2]}
                                                    </a>
                                                    <ul class="dropdown-menu" style="padding-top: 0px;">
                                                        <div id="container${row[4]}" style="min-width: 850px; height: 400px; margin: 0 auto"></div>
                                                    </ul>
                                                </div>
                                            </td>
                                        </c:otherwise>    
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <div id="grafica" style="min-width: 400px; height: 500px; margin: 0 auto"></div>             
                      <table class="table">
                        <thead>
                            <tr>
                                <th>Escala</th>
                                <th>Descripci&oacute;n</th>
                                <th>Grado de cumplimiento</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="background-color: #89A54E;">
                                <td>
                                    4.5 a 5.0
                                </td>
                                <td>
                                    Se cumple plenamente
                                </td>
                                <td>
                                    90% a 100%
                                </td>
                            </tr>
                            <tr style="background-color: #80699B;">
                                <td>
                                    4.0 a 4.4
                                </td>
                                <td>
                                    Se cumple en alto grado
                                </td>
                                <td>
                                     80% a 89%
                                </td>
                            </tr>
                            <tr style="background-color: #3D96AE;">
                                <td>
                                    3.0 a 3.9
                                </td>
                                <td>
                                    Se cumple en mediano grado
                                </td>
                                <td>
                                     60% a 79%
                                </td>
                            </tr>
                            <tr style="background-color: #DB843D;">
                                <td>
                                    2.0 a 2.9
                                </td>
                                <td>
                                    Se cumple en bajo grado
                                </td>
                                <td>
                                     40% - 59%
                                </td>
                            </tr>
                            <tr style="background-color: #AA4643;">
                                <td>
                                    1.0 a 1.9
                                </td>
                                <td>
                                    No se cumple
                                </td>
                                <td>
                                     0% - 39%
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
