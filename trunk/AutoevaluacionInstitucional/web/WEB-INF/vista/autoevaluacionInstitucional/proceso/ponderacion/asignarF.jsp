<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${auxAsignarF == 0}">
    <br  id="PonderacionFactores">
    <div class="subnav">
        <ul class="nav nav-pills">
            <li><a>Ponderación de Factores</a></li>
            <c:forEach items="${factores.rowsByIndex}" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${((iter.index) % 5 == 0) || (iter.index == 0)}">
                        <c:choose>
                            <c:when test="${(iter.index == 0)}">
                                <li class="active"><a href="#PonderacionFactores${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#PonderacionFactores${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
    <div class="hero-unit">
        <div class="row">
            <div class="span10">
                <form id="formPondeFa" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Ponderación de Factores</legend>
                        <c:if test="${auxAsignarF1 == 0}">
                            <div>
                                <div class="alert alert-error">
                                    <a data-dismiss="alert" class="close">×</a>
                                    <strong>Error!</strong>
                                    La sumatoria de la ponderación asignada a los factores no debe ser mayor de 100.
                                </div>
                            </div>
                        </c:if>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Factor</th>
                                    <th>Ponderacion</th>
                                    <th>Justificacion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${factores.rowsByIndex}" var="row" varStatus="iter">
                                    <tr id="PonderacionFactores${iter.index+1}">    
                                        <td>   
                                            <c:out value="${row[0]}"/>
                                        </td>
                                        <td>   
                                            <c:out value="${row[1]}"/>
                                        </td>
                                        <td>
                                            <input name="ponderacion${row[0]}" class="span1" type="text">
                                            <input type="hidden"  value="${row[0]}" name="id${row[0]}">
                                        </td>
                                        <td>
                                            <textarea name="justificacion${row[0]}" rows="3" class="span5"></textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Asignar Ponderación</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
<c:if test="${auxAsignarF == 1}">
    <br id="PonderacionFactores">
    <div class="subnav" >
        <ul class="nav nav-pills">
            <li><a>Ponderación de Factores</a></li>
            <c:forEach items="${pondeFactores.rowsByIndex}" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${((iter.index) % 5 == 0) || (iter.index == 0)}">
                        <c:choose>
                            <c:when test="${(iter.index == 0)}">
                                <li class="active"><a href="#PonderacionFactores${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#PonderacionFactores${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
    <div class="hero-unit" >
        <div class="row">
            <div class="span10">
                <form id="formPondeFa" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Ponderación de Factores</legend>
                        <c:if test="${auxAsignarF1 == 0}">
                            <div>
                                <div class="alert alert-error">
                                    <a data-dismiss="alert" class="close">×</a>
                                    <strong>Error!</strong>
                                    La sumatoria de la ponderación asignada a los factores no debe ser mayor de 100.
                                </div>
                            </div>
                        </c:if>
                        <div id="alert">
                            <div class="alert alert-info">
                                <a data-dismiss="alert" class="close">×</a>
                                <strong>Información!</strong>
                                La ponderación de factores ya ha sido asignada.
                            </div>
                        </div>
                        <a href="<%=request.getContextPath()%>/#listarPonderacionFactor">Detalle de Ponderación de Factores Asignada.</a>
                        <br>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Factor</th>
                                    <th>Ponderacion</th>
                                    <th>Justificacion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pondeFactores.rowsByIndex}" var="row2" varStatus="iter">
                                    <tr id="PonderacionFactores${iter.index+1}">
                                        <td>   
                                            <c:out value="${row2[4]}"/>
                                        </td>
                                        <td>   
                                            <c:out value="${row2[5]}"/>
                                        </td>
                                        <td>
                                            <input class="span1" name="ponderacion${row2[4]}" type="text" value="${row2[1]}"/>
                                            <input type="hidden"  value="${row2[4]}" name="id${row2[4]}">
                                        </td>
                                        <td>
                                            <textarea name="justificacion${row2[4]}" rows="3" class="span5">${row2[2]}</textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}"/>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Actualizar Ponderación</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
