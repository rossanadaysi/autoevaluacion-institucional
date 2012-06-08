<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/script/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/script/exporting.js"></script>
<script type="text/javascript">
    $(function () {
        var chart = new Array(${preguntas.getRowCount()});
        $(document).ready(function() {
                    
        <c:forEach items="${preguntas.rowsByIndex}" var="pregunta" varStatus="status" >
                                $("#container").append("<div id='${pregunta[0]}'></div>")
                                chart[${status.index}] = new Highcharts.Chart({
                            chart: {
                                renderTo: '${pregunta[0]}',
                                type: 'bar'
                            },
                            title: {
                                text: 'Historic World Population by Region'
                            },
                            subtitle: {
                                text: 'Source: Wikipedia.org'
                            },
                            xAxis: {
                                categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
                                title: {
                                    text: null
                                }
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: 'Population (millions)',
                                    align: 'high'
                                }
                            },
                            tooltip: {
                                formatter: function() {
                                    return ''+
                                        this.series.name +': '+ this.y +' millions';
                                }
                            },
                            plotOptions: {
                                bar: {
                                    dataLabels: {
                                        enabled: true
                                    }
                                }
                            },
                            legend: {
                                layout: 'vertical',
                                align: 'right',
                                verticalAlign: 'top',
                                x: -100,
                                y: 100,
                                floating: true,
                                borderWidth: 1,
                                backgroundColor: '#FFFFFF',
                                shadow: true
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{
                                    name: 'Year 1800',
                                    data: [107, 31, 635, 203, 2]
                                }]
                        });        
    </c:forEach>
                    
                    
                    
                    });
    
                });
</script>


<div id="container"></div>
