<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
    $(function () {
        var chart;
        var indicad ="${detalleIndicador.getRowsByIndex()[0][1]}".split(" ");
        var max2 = 70;
        var acom2=0;
        var finali2 = "";
        for(var i=0;i<indicad.length;i++){
            acom2+=indicad[i].length;
            if(acom2<max2){
                finali2 += indicad[i]+" ";
            }else{
                acom2=0;
                finali2 += "<br/>"+indicad[i]+" ";
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
                    text: ""+ finali2  +""
                },

                xAxis: {
                    categories: [
    <c:forEach items="${detalleIndicador.rowsByIndex}" var="detalleI" varStatus="status">
        <c:choose>
                <c:when test="${detalleIndicador.getRowCount()!=status.index+1}">
                                    '${detalleI[2]}-${detalleI[3]}',
            </c:when>
            <c:otherwise>
                                    '${detalleI[2]}-${detalleI[3]}'
            </c:otherwise>
        </c:choose>             
            
    </c:forEach>
                        ],
                        
                        labels: {
                            formatter: function() {
                                var partes = this.value.split("-");
                                
                                return "Pregunta "+partes[0];
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
                            var pregunta = this.x.split(" ");
                            var max = 50;
                            var acom=0;
                            var finali = "";
                            for(var i=0;i<pregunta.length;i++){
                                acom+=pregunta[i].length;
                                if(acom<max){
                                    finali += pregunta[i]+" ";
                                }else{
                                    acom=0;
                                    finali += "</b><br/><b>"+pregunta[i]+" ";
                                }
                               
                            }
                            
                            return '<b>'+ finali  +'</b><br/>'+
                                'Cumplimiento: '+ Highcharts.numberFormat(this.y, 2) +
                                '';
                        }
                    },
                    series: [{
                            name: 'Preguntas',
                            data: [
    <c:forEach items="${detalleIndicador.rowsByIndex}" var="detalleI2" varStatus="status33">
        <c:choose>
            <c:when test="${detalleFactor.getRowCount()!=status33.index+1}">
                <c:choose>
                    <c:when test="${detalleI2[4]>=4.5}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#89A54E'
                                                    },
                                                
                    </c:when>
                    <c:when test="${detalleI2[4]<4.5 && detalleI2[4]>=4.0}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#B5CA92'
                                                    },
                                                
                    </c:when>
                    <c:when test="${detalleI2[4]<4.0 && detalleI2[4]>=3.5}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#3D96AE'
                                                    },
                                                
                    </c:when>
                    <c:when test="${detalleI2[4]<3.5 && detalleI2[4]>=3.0}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#DB843D'
                                                    },
                                                
                    </c:when>
                    <c:otherwise>
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#AA4643'
                                                    },
                    </c:otherwise>
                </c:choose>
                                            
                                            
                                            
                
                     
                
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${detalleI2[4]>=4.5}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#89A54E'
                                                    }
                                                
                    </c:when>
                    <c:when test="${detalleI2[4]<4.5 && detalleI2[4]>=4.0}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#B5CA92'
                                                    }
                                                
                    </c:when>
                    <c:when test="${detalleI2[4]<4.0 && detalleI2[4]>=3.5}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#3D96AE'
                                                    }
                                                
                    </c:when>
                    <c:when test="${detalleI2[4]<3.5 && detalleI2[4]>=3.0}">
                                                    {
                                                        y: ${detalleI2[4]},
                                                        color: '#DB843D'
                                                    }
                                                
                    </c:when>
                    <c:otherwise>
                                                    {
                                                        y: ${detalleI2[4]},
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
            <legend>Detalle indicador</legend>
            <c:choose>
                <c:when test="${detalleIndicador.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Id indicador</th>
                        <th>Indicador</th>
                        <th>Id Pregunta</th>
                        <th>Pregunta</th>
                        <th>Promedio respuesta</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detalleIndicador.rowsByIndex}" var="row" varStatus="iter">
                                <tr>

                                    <c:choose>
                                        <c:when test="${iter.index == 0}">
                                            <td rowspan="${detalleIndicador.getRowCount()}">   
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td rowspan="${detalleIndicador.getRowCount()}">   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[2]}"/>
                                            </td>
                                            <td>   
                                                <a href="#detallePregunta&${row[2]}" data="${row[3]}">${row[3]}</a> 
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
                                                <a href="#detallePregunta&${row[2]}" data="${row[3]}">${row[3]}</a> 
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
