<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <fieldset>
                <legend>Listado de evaluación información documental</legend>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>  
                        <tr>
                            <th>Código del indicador</th>
                            <th>Documento asociado</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acción a implementar u observación</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tbody>
                        <c:forEach items="${listEvaluacionDocs.rowsByIndex}" var="item" varStatus="iter">
                            <tr>
                                <td>${item[0]}</td>
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
