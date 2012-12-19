<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span12">
            <br/>
            <fieldset>
                <legend>Listado de evaluación información documental</legend>
                <table class="table table-bordered table-condensed">
                    <thead>  
                        <tr>
                            <th>C&oacute;digo</th>
                            <th>Indicador</th>
                            <th>Documento</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acci&oacute;n a implementar u observaci&oacute;n</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tbody>
                        <c:forEach items="${listEvaluacionDocs.rowsByIndex}" var="item" varStatus="iter">
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
