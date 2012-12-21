<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).ready(function() { 
        $("#printEnlace").click( function() {
            $('#conte').jqprint();
            return false;
        }); 
    });
    
</script>
<div class="hero-unit">
    <a  class="span10" style="text-align: right; margin-left: 0px; text-align: right; cursor: pointer" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <fieldset>
                <legend>Listado de evaluación información numérica</legend>
                <table class="table table-bordered table-condensed">
                    <thead>
                    <th>C&oacute;digo</th>
                    <th>Indicador</th>
                    <th>Documento asociado</th>
                    <th>Responsable</th>
                    <th>Medio</th>
                    <th>Lugar</th>
                    <th>Estado</th>
                    <th>Acci&oacute;n a implementar u observaci&oacute;n</th>
                    </thead>
                    <tbody>
                    <tbody>
                        <c:forEach items="${listEvaluacionNum.rowsByIndex}" var="item" varStatus="iter">
                            <c:choose>
                                <c:when test="${item[6]<4}">
                                    <tr class="error">
                                        <td>${item[8]}</td>
                                        <td>${item[1]}</td>
                                        <td>${item[2]}</td>
                                        <td>${item[3]}</td>
                                        <td>${item[4]}</td>
                                        <td>${item[5]}</td>
                                        <td>${item[6]}</td>
                                        <td>${item[7]}</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>${item[8]}</td>
                                        <td>${item[1]}</td>
                                        <td>${item[2]}</td>
                                        <td>${item[3]}</td>
                                        <td>${item[4]}</td>
                                        <td>${item[5]}</td>
                                        <td>${item[6]}</td>
                                        <td>${item[7]}</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tbody>
                </table>  
            </fieldset>
        </div>
    </div>
</div>    
