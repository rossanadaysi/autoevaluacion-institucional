<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                    margin: [ 50, 30, 100, 120]
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
                ${detalleP2[2]},
            </c:when>
            <c:otherwise>
                ${detalleP2[2]}
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
            <h2>Detalle pregunta</h2>
            <c:choose>
                <c:when test="${detallePregunta.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Pregunta</th>
                        <th>Encuesta</th>
                        <th>Promedio respuesta</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detallePregunta.rowsByIndex}" var="row" varStatus="iter">
                                <tr>

                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td rowspan="${detallePregunta.getRowCount()}">   
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>

                                        </c:when>
                                        <c:otherwise>
                                            <td>   
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
                    <div id="grafica" style="min-width: 400px; height: 600px; margin: 0 auto"></div>             
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
