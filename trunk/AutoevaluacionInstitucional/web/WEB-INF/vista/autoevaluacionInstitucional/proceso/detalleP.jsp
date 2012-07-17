<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
   

</script>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <fieldset>
                <legend>Detalle de Proceso</legend>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Cierre</th>
                    <th>Programa</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${detailProceso.rowsByIndex}" var="item" varStatus="iter">
                            <tr> 
                                <td>${item[3]}</td>
                                <td>${item[1]}</td>
                                <td>${item[2]}</td>
                                <td>Autoevaluación Institucional</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
            </fieldset>
        </div>
    </div>
</div>    
