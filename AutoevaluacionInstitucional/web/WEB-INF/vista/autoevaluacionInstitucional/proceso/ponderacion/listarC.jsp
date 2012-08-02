<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <br/>
            <fieldset>
                <legend>Listado de ponderación de características</legend>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>#</th>
                    <th>Caracteristica</th>
                    <th>Nivel de importancia</th>
                    <th>Ponderación</th>
                    <th>Justificación</th>
                    </thead>
                    <tbody>
                    <tbody>
                        <c:forEach items="${listPonderacionCaracteristica.rowsByIndex}" var="item" varStatus="iter">
                            <tr>
                                <td>${item[0]}</td>
                                <td>${item[1]}</td>
                                <td>${item[4]}</td>
                                <td>${item[2]}</td>
                                <td>${item[3]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    </tbody>
                </table>  
            </fieldset>
        </div>
    </div>
</div>    
