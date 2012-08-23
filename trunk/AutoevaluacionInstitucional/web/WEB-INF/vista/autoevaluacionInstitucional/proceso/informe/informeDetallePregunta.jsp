<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">

    .table td {
        text-align: right;
    }
</style>
<script type="text/javascript">
    $(function () {
        var chart;
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
                                                    color: '#B5CA92'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<4.0 && detalleP2[2]>=3.5}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#3D96AE'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<3.5 && detalleP2[2]>=3.0}">
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
                                                    color: '#B5CA92'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<4.0 && detalleP2[2]>=3.5}">
                                                {
                                                    y: ${detalleP2[2]},
                                                    color: '#3D96AE'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleP2[2]<3.5 && detalleP2[2]>=3.0}">
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
            <legend>Pregunta: ${detalleIndicador.rowsByIndex[0][1]}</legend>
            <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatriz">Matriz de Calidad de Características</a> <span class="divider">/</span></li>
                <li><a href="#detalleFactor&${detalleFactor.rowsByIndex[0][0]}">Factor ${detalleCaracteristica.rowsByIndex[0][5]}</a> <span class="divider">/</span></li>
                <li><a href="#detalleCaracteristica&${detalleCaracteristica.rowsByIndex[0][0]}" data="${detalleCaracteristica.rowsByIndex[0][1]}">Característica ${detalleCaracteristica.rowsByIndex[0][0]}</a><span class="divider">/</span></li>
                <li><a href="#detalleIndicador&${detalleIndicador.rowsByIndex[0][0]}" data="${detalleIndicador.rowsByIndex[0][1]}">Indicador ${detalleIndicador.rowsByIndex[0][0]}</a><span class="divider">/</span></li>
                <li class="active">Pregunta ${detallePregunta.rowsByIndex[0][3]}</li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${detallePregunta.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
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
                                                <c:out value="${row[2]}"/>
                                            </td>

                                        </c:when>
                                        <c:otherwise>
                                            <td style="text-align: left">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                        </c:otherwise>    
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <div id="grafica" style="min-width: 400px; height: 600px; margin: 0 auto"></div>             
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
