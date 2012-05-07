<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <fieldset>
                <legend>Listado de evaluación información numérica</legend>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Código</th>
                    <th>Indicador</th>
                    <th>Evaluación</th>
                    <th>Nombre de información numérica donde está ubicado</th>
                    <th>Acción</th>
                    <th>Responsable</th>
                    </thead>
                    <tbody>
                    <tbody>
                    <c:forEach items="${listEvaluacionNum.rowsByIndex}" var="item" varStatus="iter">
                        <tr>
                            <td>${item[0]}</td>
                            <td>${item[1]}</td>
                            <td>${item[2]}</td>
                            <td>${item[3]}</td>
                            <td>${item[4]}</td>
                            <td>${item[5]}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    </tbody>
                </table>  
            </fieldset>
        </div>
    </div>
</div>    
