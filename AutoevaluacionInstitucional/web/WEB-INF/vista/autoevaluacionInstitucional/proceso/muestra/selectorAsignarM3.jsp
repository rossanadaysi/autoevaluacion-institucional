<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4> Editar Muestra Generada Para la Fuente Seleccionada</h4>
<br>
<ul id="fcbklist" >

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
        <%--
          <c:if test="${auxx == 1}">
              <li>
                  <c:set var="auxx" value="0"></c:set>
                  <strong>${item[2]} ${item[3]}</strong><br/> 
                  <span class="fcbkitem_text">${item[1]}</span>
                  <input name="${item[0]}" type="hidden" value="0"/>
              </li>
          </c:if>
        --%>
    </c:forEach>

</ul>
<div class="form-actions">
    <button class="btn btn-primary" type="submit">Actualizar Muestra Para Fuente Seleccionada</button>
</div>