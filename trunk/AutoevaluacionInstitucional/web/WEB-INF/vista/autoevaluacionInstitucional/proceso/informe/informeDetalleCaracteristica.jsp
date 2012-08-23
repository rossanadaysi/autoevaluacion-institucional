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
                    text: '${detalleCaracteristica.getRowsByIndex()[0][1]}'
                },

                xAxis: {
                    categories: [
    <c:forEach items="${detalleCaracteristica.rowsByIndex}" var="detalleC" varStatus="status">
        <c:choose>
            <c:when test="${detalleCaracteristica.getRowCount()!=status.index+1}">
                                        '${detalleC[2]}-${detalleC[3]}',
            </c:when>
            <c:otherwise>
                                    '${detalleC[2]}-${detalleC[3]}'
            </c:otherwise>
        </c:choose>             
            
    </c:forEach>
                        ],
                        
                        labels: {
                            formatter: function() {
                                var partes = this.value.split("-");
                                
                                return "Indicador "+partes[0];
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
                            var indicador = this.x.split(" ");
                            var max = 50;
                            var acom=0;
                            var finali = "";
                            for(var i=0;i<indicador.length;i++){
                                acom+=indicador[i].length;
                                if(acom<max){
                                    finali += indicador[i]+" ";
                                }else{
                                    acom=0;
                                    finali += "</b><br/><b>"+indicador[i]+" ";
                                }
                               
                            }
                            
                            return '<b>'+ finali  +'</b><br/>'+
                                'Cumplimiento: '+ Highcharts.numberFormat(this.y, 2) +
                                '';
                        }
                    },
                    series: [{
                            name: 'Indicadores',
                            data: [
    <c:forEach items="${detalleCaracteristica.rowsByIndex}" var="detalleC2" varStatus="status33">
        <c:choose>
            <c:when test="${detalleCaracteristica.getRowCount()!=status33.index+1}">
                ${detalleC2[4]},
            </c:when>
            <c:otherwise>
                ${detalleC2[4]}
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
            <legend>Detalle caracteristica</legend>
            <c:choose>
                <c:when test="${detalleCaracteristica.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Id caracteristica</th>
                        <th>Caracteristica</th>
                        <th>Id indicador</th>
                        <th>Indicador</th>
                        <th>Cumplimiento</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detalleCaracteristica.rowsByIndex}" var="row" varStatus="iter">
                                <tr>

                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td rowspan="${detalleCaracteristica.getRowCount()}">   
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td rowspan="${detalleCaracteristica.getRowCount()}">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleIndicador&${row[2]}" data="${row[3]}">${row[3]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[4]}"/>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detalleIndicador&${row[2]}"data="${row[3]}">${row[3]}</a> 
                                            </td>
                                            <td>   
                                                <c:out value="${row[4]}"/>
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


