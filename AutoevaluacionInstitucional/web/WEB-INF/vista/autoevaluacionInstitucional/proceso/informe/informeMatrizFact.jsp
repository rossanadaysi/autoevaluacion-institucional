<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    $(function () {
        var chart;
        $(document).ready(function() {
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'grafica',
                    type: 'column',
                    margin: [ 50, 30, 100, 120]
                },
                title: {
                    text: 'Matriz de calidad de factores'
                },

                xAxis: {
                    categories: [
    <c:forEach items="${matrizFactores.rowsByIndex}" var="factor" varStatus="status">
        <c:choose>
            <c:when test="${matrizFactores.getRowCount()!=status.index+1}">
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
                                'Cumplimiento: '+ Highcharts.numberFormat(this.y, 2) +
                                '';
                        }
                    },
                    series: [{
                            name: 'Factores',
                            data: [     
    <c:forEach items="${matrizFactores.rowsByIndex}" var="factor2" varStatus="status33">
        <c:choose>
            <c:when test="${matrizFactores.getRowCount()!=status33.index+1}">
                <c:choose>
                        <c:when test="${factor2[3]>4}">
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#BF0B23'
                                                },
                                                
                    </c:when>
                        <c:otherwise>
                                                {
                                                    y: ${factor2[3]},
                                                    color: '#BF0B23'
                                                },
                    </c:otherwise>
                </c:choose>
                                            
                                            
                                            
                
                     
                
            </c:when>
            <c:otherwise>
                
                
                                        { y:${factor2[3]}}
                
        
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
            <c:choose>
                <c:when test="${matrizFactores.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
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
                            <c:forEach items="${matrizFactores.rowsByIndex}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row[0]}"/>
                                    </td>
                                    <td>   
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
                                    <fmt:formatNumber type="number"   maxFractionDigits="2" value="${cumplimiento/ponderacion}" />
                                </td>
                                <td>   

                                </td>
                                <td>   
                                    5.0
                                </td>
                                <td>   
                                    <fmt:formatNumber type="number"   maxFractionDigits="2" value="${(cumplimiento/ponderacion)*20}" />%
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="grafica" style="min-width: 400px; height: 600px; margin: 0 auto"></div>             
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
