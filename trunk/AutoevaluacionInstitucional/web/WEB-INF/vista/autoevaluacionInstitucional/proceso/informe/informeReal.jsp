<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/script/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/script/exporting.js"></script>
<script type="text/javascript">
    $(function () {
        var chart = new Array(${preguntas.getRowCount()});
        $(document).ready(function() {
                    
    <c:forEach items="${preguntas.rowsByIndex}" var="pregunta" varStatus="status" >
                $("#container").append("<div id='${pregunta[0]}' class='span10'></div>")
                chart[${status.index}] = new Highcharts.Chart({
                    chart: {
                        renderTo: '${pregunta[0]}',
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                                
                    },
                    title: {
                        text: null
                    },
                    subtitle: {
                        text: '${pregunta[1]}'
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
                                    return '<b>'+ this.point.name +'</b>: '+ this.y +'';
                                }
                            }
                        }
                    },
                    tooltip: {
                        formatter: function() {
                            return ''+
                                this.point.name +': '+ this.percentage +' %';
                        }
                    },
                    series: [{
                            type: 'pie',
                            name: 'Personas',
                            data: [
                                ['0',  45],
                                ['1',  0],
                                ['2',  5],
                                ['3',  8],
                                ['4',  2],
                                {
                                    name: '5',
                                    y: 50,
                                    sliced: true,
                                    selected: true
                                },
    
                            ]
                        }]
                    
                    
                });        
    </c:forEach>
                    
                    
                    
            });
    
        });
</script>


<div id="container"></div>
