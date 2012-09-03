<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <fieldset>
                <legend>Listado de evaluación información numérica</legend>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>C&oacute;digo del indicador</th>
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
                            <tr>
                                <td>${item[8]}</td>
                                <td>${item[2]}</td>
                                <td>${item[3]}</td>
                                <td>${item[4]}</td>
                                <td>${item[5]}</td>
                                <td>${item[6]}</td>
                                <td>${item[7]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
            </fieldset>
        </div>
    </div>
</div>    
