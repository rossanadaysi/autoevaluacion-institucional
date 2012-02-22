<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        //id(ul id),width,height(element height),row(elements in row)        
        $.fcbkListSelection("#fcbklist","600","50","3");       
    });
            
</script>
<div id="div1">
    <c:if test="${aux_asignarM == 1}">
        <div id="alert">
            <div class="alert alert-info">
                <a data-dismiss="alert" class="close">×</a>
                <strong>Información!</strong>
                La muestra ya han sido asignada para la fuente seleccionada.
            </div>
        </div>
        
        
        
        <ul id="fcbklist">
            <c:forEach items="${muestras.rowsByIndex}" var="item" varStatus="iter">
                <c:set var="auxx" value="1"></c:set>
                <c:forEach items="${muestrasSeleccionadas.rowsByIndex}" var="item2" varStatus="iter2">
                    <c:if test="${item[0] == item2[2]}">
                        <li>
                            <c:set var="auxx" value="0"></c:set>
                            <strong>${item[2]} ${item[3]}</strong><br/> 
                            <span class="fcbkitem_text">${item[1]}</span>
                            <input name="${item[0]}" type="hidden" checked="checked" value="0"/>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${auxx == 1}">
                    <li>
                        <c:set var="auxx" value="0"></c:set>
                        <strong>${item[2]} ${item[3]}</strong><br/> 
                        <span class="fcbkitem_text">${item[1]}</span>
                        <input name="${item[0]}" type="hidden" value="0"/>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
        <div class="form-actions">
            <button class="btn btn-primary" type="submit">Actualizar Muestra Para Fuente Seleccionada</button>
        </div>
    </c:if>
    <c:if test="${aux_asignarM == 0}">
        
        
        <ul id="fcbklist">
            <c:forEach items="${muestras.rowsByIndex}" var="item" varStatus="iter">
                <li>
                    <strong>${item[2]} ${item[3]}</strong><br/> 
                    <span class="fcbkitem_text">${item[1]}</span>
                    <input name="${item[0]}" type="hidden" value="0"/>
                </li>
            </c:forEach>
        </ul>
        
        
        <div class="form-actions">
            <button class="btn btn-primary" type="submit">Asignar Muestra Para Fuente Seleccionada</button>
        </div>
    </c:if>
</div>
<br><br>