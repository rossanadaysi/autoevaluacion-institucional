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
        $(document).ready(function() {
            
            $('.tool').tooltip().click(function(e){
                $(this).tooltip('hide');
            })
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'grafica',
                    type: 'column',
                    margin: [ 50, 30, 100, 50]
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
                <c:choose>
                    <c:when test="${detalleF2[7]>=4.5}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#89A54E'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleF2[7]<4.5 && detalleF2[7]>=4.0}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#B5CA92'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleF2[7]<4.0 && detalleF2[7]>=3.5}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#3D96AE'
                                                },
                                                
                    </c:when>
                    <c:when test="${detalleF2[7]<3.5 && detalleF2[7]>=3.0}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#DB843D'
                                                },
                                                
                    </c:when>
                    <c:otherwise>
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#AA4643'
                                                },
                    </c:otherwise>
                </c:choose>
                                            
                                            
                                            
                
                     
                
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${detalleF2[7]>=4.5}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#89A54E'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleF2[7]<4.5 && detalleF2[7]>=4.0}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#B5CA92'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleF2[7]<4.0 && detalleF2[7]>=3.5}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#3D96AE'
                                                }
                                                
                    </c:when>
                    <c:when test="${detalleF2[7]<3.5 && detalleF2[7]>=3.0}">
                                                {
                                                    y: ${detalleF2[7]},
                                                    color: '#DB843D'
                                                }
                                                
                    </c:when>
                    <c:otherwise>
                                                {
                                                    y: ${detalleF2[7]},
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
            <legend>Factor: ${detalleFactor.rowsByIndex[0][1]}</legend>
            <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatriz">Matriz de Calidad de Características</a> <span class="divider">/</span></li>
                <li class="active tool" data-placement="top" rel="tooltip" data-original-title="${detalleFactor.rowsByIndex[0][1]}">Factor ${detalleFactor.rowsByIndex[0][0]}</li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${detalleFactor.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>C&oacute;digo</th>
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


                                    <td style="text-align: left">   
                                        <c:out value="${row[3]}"/>
                                    </td>
                                    <td style="text-align: left">   
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

