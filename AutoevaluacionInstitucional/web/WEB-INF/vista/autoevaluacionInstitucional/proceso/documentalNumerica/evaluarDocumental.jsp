<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br>
<c:if test="${auxInfoDocumental == 0}"><!--Si no se ha asignado nada-->
    <div class="hero-unit">
        <div class="row">
            <div class="span10">
                <form id="formInfoDoc" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluar información documental</legend>
                        <table class="table table-striped">
                            <thead>
                            <th>Cod.</th>
                            <th>Documento asociado</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acción a implementar u observación</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${indicadoresDocumental.rowsByIndex}" var="row" varStatus="iter">
                                    <tr id="InformacionDocumental${iter.index+1}">    
                                        <td>   
                                            <c:out value="${row[1]}"/>
                                        </td>
                                        <td>
                                            <textarea name="nombreDocumento${row[0]}" class="span2"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="responsableDocumento${row[0]}" class="span2"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="medioDocumento${row[0]}" class="span1"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="lugarDocumento${row[0]}" class="span2"></textarea>
                                        </td>
                                        <td>
                                            <select class="span1"  name="evaluacionDoc${row[0]}">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                            </select>
                                            <input type="hidden"  value="${row[0]}" name="idIndicadorDoc${row[0]}">
                                        </td>
                                        <td>
                                            <textarea name="accionDocumento${row[0]}" rows="4" class="span2"></textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Evaluar Información Documental</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
<c:if test="${auxInfoDocumental == 1}">
    <div class="hero-unit" >
        <div class="row">
            <div class="span10">
                <form id="formInfoDoc" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluar información documental</legend>
                        <div class="alert alert-info">
                            <a data-dismiss="alert" class="close">×</a>
                            <strong>Información!</strong>
                            La evaluación de información documental ya ha sido asignada. <a href="<%=request.getContextPath()%>/#listarEvaluarDoc">Detalle de información documental Evaluada.</a>
                        </div>
                        <br>
                        <table class="table table-striped">
                            <thead>
                            <th>Código del indicador</th>
                            <th>Documento asociado</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acción a implementar u observación</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${evaluarcionDocumental.rowsByIndex}" var="row2" varStatus="iter">
                                    <tr id="InformacionDocumental${iter.index+1}">
                                        <td>   
                                            <c:out value="${row2[0]}"/>
                                        </td>
                                        <td>
                                            <textarea name="nombreDocumento${row2[0]}" rows="4" class="span2">${row2[2]}</textarea>
                                        </td>
                                        <td>
                                            <textarea name="responsableDocumento${row2[0]}" rows="4" class="span2">${row2[3]}</textarea>
                                        </td>
                                        <td>
                                            <textarea name="medioDocumento${row2[0]}" rows="4" class="span2">${row2[4]}</textarea>
                                        </td>
                                        <td>
                                            <textarea name="lugarDocumento${row2[0]}" rows="4" class="span2">${row2[5]}</textarea>
                                        </td>
                                        <td>
                                            <select  class="span1" name="evaluacionDoc${row2[0]}">
                                                <c:choose>
                                                    <c:when test="${row2[6] == 1.0}">
                                                        <option selected="selected" value="1.0">1.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="1.0">1.0</option>
                                                    </c:otherwise>
                                                </c:choose>                                        
                                                <c:choose>
                                                    <c:when test="${row2[6] == 2.0}">
                                                        <option selected="selected" value="2.0">2.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="2.0">2.0</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[6] == 3.0}">
                                                        <option selected="selected" value="3.0">3.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="3.0">3.0</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[6] == 4.0}">
                                                        <option selected="selected" value="4.0">4.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="4.0">4.0</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${row2[6] == 5.0}">
                                                        <option selected="selected" value="5.0">5.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="5.0">5.0</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <input type="hidden"  value="${row2[0]}" name="idIndicadorDoc${row2[0]}">
                                        </td>
                                        <td>
                                            <textarea name="accionDocumento${row2[0]}" rows="4" class="span2">${row2[6]}</textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}"/>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Actualizar Evaluación</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
