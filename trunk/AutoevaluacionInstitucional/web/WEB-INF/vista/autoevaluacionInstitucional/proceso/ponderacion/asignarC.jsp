<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${auxAsignarC == 0}">
    <br>
    <div class="subnav">
        <ul class="nav nav-pills">
            <li><a>Ponderación de Características</a></li>
            <c:forEach items="${caracteristicas.rowsByIndex}" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${((iter.index + 1) % 5 == 0) || (iter.index == 0)}">
                        <c:choose>
                            <c:when test="${(iter.index == 0)}">
                                <li class="active"><a href="#cara${iter.index}">${iter.index + 1} - ${iter.index + 5}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#cara${iter.index}">${iter.index + 2} - ${iter.index + 6}</a></li>
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
                <form id="formPondeCara" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Ponderación  de Características</legend>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Caracteristica</th>
                                    <th>Ponderación</th>
                                    <th>Justificaciòn</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${caracteristicas.rowsByIndex}" var="row" varStatus="iter">

                                    <tr id="cara${iter.index}">   
                                        <td>
                                            <c:out value="${row[0]}"/>
                                        </td>
                                        <td>   
                                            <c:out value="${row[1]}"/>
                                        </td>
                                        <td>
                                            <select class="span1" name="ponderacion${row[0]}">
                                                <option value="NULL">--</option>
                                                <option value="0">0</option>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                            </select>
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
                        <input type="hidden" name="count" value="${iterador}">
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
<c:if test="${auxAsignarC == 1}">
    <br>
    <div class="subnav">
        <ul class="nav nav-pills">
            <li><a>Ponderación de Características</a></li>
            <c:forEach items="${caracteristicas.rowsByIndex}" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${((iter.index + 1) % 5 == 0) || (iter.index == 0)}">
                        <c:choose>
                            <c:when test="${(iter.index == 0)}">
                                <li class="active"><a href="#cara${iter.index}">${iter.index + 1} - ${iter.index + 5}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#cara${iter.index}">${iter.index + 2} - ${iter.index + 6}</a></li>
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
                <form id="formPondeCara" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Ponderación de Características</legend>

                        <a href="<%=request.getContextPath()%>/#listarPonderacionCaracteristica">Detalle de Ponderación de Características Asignada.</a>
                        <br>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Característica</th>
                                    <th>Ponderacion</th>
                                    <th>Justificacion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pondeCaracteristicas.rowsByIndex}" var="row2" varStatus="iter">
                                    <tr id="cara${iter.index}">    
                                        <td>   
                                            <c:out value="${row2[4]}"/>
                                        </td>
                                        <td>   
                                            <c:out value="${row2[5]}"/>
                                        </td>
                                        <td>
                                            <select class="span1" name="ponderacion${row2[4]}">
                                                <option value="NULL">--</option>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 0}">
                                                        <option selected="selected" value="0">0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="0">0</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 1}">
                                                        <option selected="selected" value="1">1</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="1">1</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 2}">
                                                        <option selected="selected" value="2">2</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="2">2</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 3}">
                                                        <option selected="selected" value="3">3</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="3">3</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 4}">
                                                        <option selected="selected" value="4">4</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="4">4</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 5}">
                                                        <option selected="selected" value="5">5</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="5">5</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 6}">
                                                        <option selected="selected" value="6">6</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="6">6</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 7}">
                                                        <option selected="selected" value="7">7</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="7">7</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 8}">
                                                        <option selected="selected" value="8">8</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="8">8</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[1] == 9}">
                                                        <option selected="selected" value="9">9</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="9">9</option>
                                                    </c:otherwise>
                                                </c:choose>  
                                                <c:choose>
                                                    <c:when test="${row2[1] == 10}">
                                                        <option selected="selected" value="10">10</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="10">10</option>
                                                    </c:otherwise>
                                                </c:choose>  
                                            </select>
                                            <input type="hidden"  value="${row2[4]}" name="id${row2[4]}">
                                        </td>
                                        <td>
                                            <textarea name="justificacion${row2[4]}" rows="3" class="span5">${row2[2]}</textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
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

