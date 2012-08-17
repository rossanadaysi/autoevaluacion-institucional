<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
<script type="text/javascript" src="jQuery/dragDrop/fcbklistselection.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        //id(ul id),width,height(element height),row(elements in row)        
        $.fcbkListSelection("#fcbklist","600","50","3");
        
        
        
        
        
    });         
   
</script>
<div id="div1">
    <c:if test="${aux_IniciarP == 0}">
        <c:if test="${aux_asignarE == 1}">
            <div id="alert">
                <div class="alert alert-info">
                    <a data-dismiss="alert" class="close">×</a>
                    <strong>Información!</strong>
                    Las encuestas ya han sido asignadas para la fuente seleccionada.
                </div>
            </div>
            <ul id="fcbklist">
                <c:forEach items="${encuestas.rowsByIndex}" var="item" varStatus="iter">
                    <c:set var="auxx" value="1"></c:set>
                    <c:forEach items="${encuestasSeleccionadas.rowsByIndex}" var="item2" varStatus="iter2">
                        <c:if test="${item[0] == item2[3]}">
                            <li>
                                <c:set var="auxx" value="0"></c:set>
                                <strong>${item[1]}</strong><br/> 
                                <span class="fcbkitem_text">${item[2]}</span>
                                <input name="${item[1]}" type="hidden" checked="checked" value="0"/>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${auxx == 1}">
                        <li>
                            <c:set var="auxx" value="0"></c:set>
                            <strong>${item[1]}</strong><br/> 
                            <span class="fcbkitem_text">${item[2]}</span>
                            <input name="${item[1]}" type="hidden" value="0"/>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
            <div class="form-actions">
                <button class="btn btn-primary" type="submit">Actualizar Encuesta Para Fuente Seleccionada</button>
            </div>
        </c:if>
        <c:if test="${aux_asignarE == 0}">
            <ul id="fcbklist">
                <c:forEach items="${encuestas.rowsByIndex}" var="item" varStatus="iter">
                    <li>
                        <strong>${item[1]}</strong><br/> 
                        <span class="fcbkitem_text">${item[2]}</span>
                        <input name="${item[1]}" type="hidden" value="0"/>
                    </li>
                </c:forEach>
            </ul>
            <div class="form-actions">
                <button class="btn btn-primary" type="submit">Asignar Encuesta Para Fuente Seleccionada</button>
            </div>
        </c:if>
    </c:if>
    <c:if test="${aux_IniciarP == 1 || aux_IniciarP == 2}">
        <c:if test="${aux_asignarE == 1}">
            <table class="table table-striped table-bordered table-condensed">
                <thead>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Fecha</th>
                <th></th>
                </thead>
                <tbody>
                <tbody>
                    <c:forEach items="${encuestas.rowsByIndex}" var="item" varStatus="iter">
                        <c:forEach items="${encuestasSeleccionadas.rowsByIndex}" var="item2" varStatus="iter2">
                            <c:if test="${item[0] == item2[3]}">
                                <tr>
                                    <td>${item[1]}</td>
                                    <td>${item[2]}</td>
                                    <td>${item[5]}</td>
                                    <td><a title="Vista previa" href="#verEncuesta&${item[0]}"><i class="icon-eye-open"></i></a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>   
                    </c:forEach>
                </tbody>
                </tbody>
            </table>   
        </c:if>
        <c:if test="${aux_asignarE == 0}">
            <div class="alert alert-error">
                <a class="close">×</a>
                <strong>Información!</strong>
                Niguna encuesta ha sido asignada para la fuente seleccionada.
            </div>
        </c:if>
    </c:if>
</div>
<br><br>