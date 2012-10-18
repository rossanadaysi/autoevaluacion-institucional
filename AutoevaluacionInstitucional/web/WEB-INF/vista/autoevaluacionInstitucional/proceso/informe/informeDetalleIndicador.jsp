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
        var chart2 = new Array(${detalleIndicador.getRowCount()});
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
            $('.tool').tooltip().click(function(e){
                $(this).tooltip('hide');
            })
            
    <c:forEach items="${detalleIndicador.rowsByIndex}" var="pregunta" varStatus="status" >
                chart2[${status.index}] = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container${pregunta[2]}',
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                                
                    },
                    title: {
                        text: null
                    },
                    subtitle: {
                        text: '${pregunta[3]}'
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
                                ['0',  ${pregunta[8]}],
                                ['1',  ${pregunta[9]}],
                                ['2',  ${pregunta[10]}],
                                ['3',  ${pregunta[11]}],
                                ['4',  ${pregunta[12]}],
                                {
                                    name: '5',
                                    y: ${pregunta[13]},
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
            <c:when test="${detalleIndicador.getRowCount()!=status33.index+1}">
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
            <legend>Indicador: ${detalleIndicador.rowsByIndex[0][1]}</legend>
            <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatriz">Matriz de Calidad de Características</a> <span class="divider">/</span></li>
                <li><a class="tool" data-placement="top" rel="tooltip" data-original-title="${detalleCaracteristica.rowsByIndex[0][7]}" href="#detalleFactor&${detalleCaracteristica.rowsByIndex[0][5]}">Factor ${detalleCaracteristica.rowsByIndex[0][5]}</a> <span class="divider">/</span></li>
                <li><a class="tool" data-placement="top" rel="tooltip" data-original-title="${detalleCaracteristica.rowsByIndex[0][1]}"  href="#detalleCaracteristica&${detalleCaracteristica.rowsByIndex[0][0]}" data="${detalleCaracteristica.rowsByIndex[0][1]}">Característica ${detalleCaracteristica.rowsByIndex[0][0]}</a><span class="divider">/</span></li>
                <li class="active tool" data-placement="top" rel="tooltip" data-original-title="${detalleIndicador.rowsByIndex[0][1]}">Indicador ${detalleIndicador.rowsByIndex[0][7]}</li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${detalleIndicador.getRowCount()!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>C&oacute;digo</th>
                        <th>Pregunta</th>
                        <th>Promedio respuesta</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${detalleIndicador.rowsByIndex}" var="row" varStatus="iter">
                                <tr>
                                    <td style="text-align: left">   
                                        <c:out value="${row[6]}"/>
                                    </td>
                                    <td style="text-align: left">   
                                        <a href="#detallePregunta&${row[2]}" data="${row[3]}">${row[3]}</a> 
                                    </td>
                                    <td>
                                        <div class="btn-group pull-right">
                                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                                ${row[4]}
                                            </a>
                                            <ul class="dropdown-menu" style="padding-top: 0px;">
                                                <div id="container${row[2]}" style="min-width: 850px; height: 400px; margin: 0 auto"></div>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <div id="grafica" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
                    <div id="container"></div>
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
