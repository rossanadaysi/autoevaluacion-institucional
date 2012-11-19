<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>
<script type="text/javascript">
    $(function () {
        var chart;
        $(document).ready(function() {
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'grafica',
                    type: 'column',
                    margin: [ 50, 30, 100, 50]
                },
                title: {
                    text: 'Matriz de calidad de factores'
                },

                xAxis: {
                    categories: [
    <c:forEach items="${matrizFactores1.rowsByIndex}" var="factor" varStatus="status">
        <c:choose>
            <c:when test="${matrizFactores1.getRowCount()!=status.index+1}">
                                    '${factor[0]}-${factor[1]}',
            </c:when>
            <c:otherwise>
                                    '${factor[0]}-${factor[1]}'
            </c:otherwise>
        </c:choose>             
            
    </c:forEach>
                        ],
                        
                        labels: {
                            formatter: function() {
                                var partes = this.value.split("-");
                                
                                return "Factor "+partes[0];
                            },
                            rotation:-45,
                            align: 'right',
                            style: {
                                fontSize: '12px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        }
                    },
                    
                    plotOptions: {
                        series: {
                            colorByPoint: true,
                            cursor: 'pointer',
                            point: {
                                events: {
                                    click: function() {
                                        var partes2 = this.category.split("-");
                                        var a = $("a[data='"+partes2[1]+"']");
                                        location = a.attr("href");
                                    }
                                }
                            }
                        }
                    },
                    
                    
                    yAxis: {
                        min: 0,
                        max: 5,
                        title: {
                            text: 'Grado de cumplimiento'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    tooltip: {
                        formatter: function() {
                            return '<b>'+ this.x +'</b><br/>'+
                                'Cumplimiento: '+ Highcharts.numberFormat(this.y, 1) +
                                '';
                        }
                    },
                    series: [{
                            name: 'Factores',
                            data: [     
    <c:forEach items="${matrizFactores1.rowsByIndex}" var="factor2" varStatus="status33">
        <c:choose>
            <c:when test="${matrizFactores1.getRowCount()!=status33.index+1}">
                <c:choose>
                    <c:when test="${factor2[3]>=4.5}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#89A54E'
                                                },
                                                
                    </c:when>
                    <c:when test="${factor2[3]<4.5 && factor2[3]>=4.0}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#80699B'
                                                },
                                                
                    </c:when>
                    <c:when test="${factor2[3]<4.0 && factor2[3]>=3.0}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#3D96AE'
                                                },
                                                
                    </c:when>
                    <c:when test="${factor2[3]<3.0 && factor2[3]>=2.0}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#DB843D'
                                                },
                                                
                    </c:when>
                    <c:otherwise>
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#AA4643'
                                                },
                    </c:otherwise>
                </c:choose>
                                            
                                            
                                            
                
                     
                
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${factor2[3]>=4.5}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#89A54E'
                                                }
                                                
                    </c:when>
                    <c:when test="${factor2[3]<4.5 && factor2[3]>=4.0}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#80699B'
                                                }
                                                
                    </c:when>
                    <c:when test="${factor2[3]<4.0 && factor2[3]>=3.0}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#3D96AE'
                                                }
                                                
                    </c:when>
                    <c:when test="${factor2[3]<3.0 && factor2[3]>=2.0}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#DB843D'
                                                }
                                                
                    </c:when>
                    <c:otherwise>
                                                {
                                                    y: ${factor2[3]},
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
            <legend>Matriz de Calidad de Factores</legend>
            <ul class="breadcrumb">
                <li class="active">Matriz de Calidad de Factores  <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatriz">Matriz de Calidad de Características</a></li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${matrizFactores1.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Id Factor</th>
                        <th>Factor</th>
                        <th>Ponderacion Factor</th>
                        <th>Grado de Cumplimiento</th>
                        <th>Evaluacion teniendo en cuenta ponderacion</th>
                        <th>Logro ideal</th>
                        <th>Relacion con el logro ideal</th>
                        </thead>
                        <tbody>
                            <c:set var="ponderacion" value="0" />
                            <c:set var="cumplimiento" value="0" />
                            <c:forEach items="${matrizFactores1.rowsByIndex}" var="row" varStatus="iter">
                                <tr>
                                    <td style="text-align: left">   
                                        <c:out value="${row[0]}"/>
                                    </td>
                                    <td style="text-align: left">   
                                        <a href="#detalleFactor&${row[0]}" data="${row[1]}">${row[1]}</a>
                                    </td>
                                    <td>   
                                        <c:out value="${row[2]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[3]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[4]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[5]}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row[6]}%"/>
                                    </td>
                                </tr>
                                <c:set var="ponderacion" value="${ponderacion + row[2]}" />
                                <c:set var="cumplimiento" value="${(row[2] * row[3])+cumplimiento}" />
                            </c:forEach>
                            <tr>
                                <td>   
                                    Totales:
                                </td>
                                <td>   

                                </td>
                                <td>   

                                </td>
                                <td>   
                                    <fmt:formatNumber type="number"   maxFractionDigits="1" value="${cumplimiento/ponderacion}" />
                                </td>
                                <td>   

                                </td>
                                <td>   
                                    
                                </td>
                                <td>   
                                    <fmt:formatNumber type="number"   maxFractionDigits="1" value="${(cumplimiento/ponderacion)*20}" />%
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br/>          
                    <div id="grafica" style="min-width: 400px; height: 500px; margin: 0 auto">

                    </div>             
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
</div>    
