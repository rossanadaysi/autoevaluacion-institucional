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
                <legend>Listado de ponderación de factores</legend>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>#</th>
                    <th>Factor</th>
                    <th>Ponderación</th>
                    <th>Justificación</th>
                    </thead>
                    <tbody>
                    <tbody>
                        <c:forEach items="${listPonderacionFactores.rowsByIndex}" var="item" varStatus="iter">
                            <tr>
                                <td>${item[0]}</td>
                                <td>${item[1]}</td>
                                <td>${item[2]}</td>
                                <td>${item[3]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
            </fieldset>
        </div>
    </div>
</div>    
