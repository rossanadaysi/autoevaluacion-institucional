<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                    text: '${detalleFactor.getRowsByIndex()[0][1]}'
                },

                xAxis: {
                    categories: [
    <c:forEach items="${detalleFactor.rowsByIndex}" var="detalleF" varStatus="status">
        <c:choose>
                <c:when test="${detalleFactor.getRowCount()!=status.index+1}">
                                        '${detalleF[3]}-${detalleF[4]}',
            </c:when>
            <c:otherwise>
                                    '${detalleF[3]}-${detalleF[4]}'
            </c:otherwise>
        </c:choose>             
            
    </c:forEach>
                        ],
                        
                        labels: {
                            formatter: function() {
                                var partes = this.value.split("-");
                                
                                return "Característica "+partes[0];
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
                            name: 'Caracteristicas',
                            data: [
    <c:forEach items="${detalleFactor.rowsByIndex}" var="detalleF2" varStatus="status33">
        <c:choose>
            <c:when test="${detalleFactor.getRowCount()!=status33.index+1}">
                ${detalleF2[7]},
            </c:when>
            <c:otherwise>
                ${detalleF2[7]}
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
            <legend>Detalle factor</legend>
            <c:choose>
                <c:when test="${detalleFactor.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Id factor</th>
                        <th>Factor</th>
                        <th>Ponderacion factor</th>
                        <th>Id caracteristica</th>
                        <th>Caracteristica</th>
                        <th>Nivel de importacia</th>
                        <th>Ponderacion caracteristica</th>
                        <th>Grado de Cumplimiento</th>
                        <th>Evaluacion teniendo en cuenta ponderacion</th>
                        <th>Logro ideal</th>
                        <th>Relacion con el logro ideal</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detalleFactor.rowsByIndex}" var="row" varStatus="iter">
                                <tr>

                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td rowspan="${detalleFactor.getRowCount()}">   
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td rowspan="${detalleFactor.getRowCount()}">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td rowspan="${detalleFactor.getRowCount()}">   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[3]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleCaracteristica&${row[3]}" data="${row[4]}">${row[4]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[5]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[6]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[7]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[8]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[9]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[10]}%"/>
                                            </td>

                                        </c:when>
                                        <c:otherwise>
                                            <td>   
                                                <c:out value="${row[3]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleCaracteristica&${row[3]}" data="${row[4]}">${row[4]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[5]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[6]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[7]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[8]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[9]}%"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[10]}%"/>
                                            </td>
                                        </c:otherwise>    
                                    </c:choose>



                                </tr>
                            </c:forEach>
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

