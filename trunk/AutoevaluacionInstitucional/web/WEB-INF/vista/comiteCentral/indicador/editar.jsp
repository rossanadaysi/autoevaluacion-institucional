<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
<link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
<script type="text/javascript" src="jQuery/dragDrop/fcbklistselection.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        //id(ul id),width,height(element height),row(elements in row)        
        $.fcbkListSelection("#fcbklist","600","50","3");       
        
        $("#formEditarIndic").validate({
            submitHandler: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController2?action=editarIndicadorCC",
                    data: $("#formEditarIndic").serialize(),
                    error: function(){
                        console.log("ocurrio un error");
                    },
                    success: function(){
                        location = "<%=request.getContextPath()%>/#listarIndicadores"
                    } //fin success
                }); //fin $.ajax    
            }
        });
  });      
</script>
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formEditarIndic" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Indicador</legend>
                    <div class="control-group">
                        <label for="codigo" class="control-label">Codigo</label>
                        <div class="controls">
                        <input type="text" id="codigo" name="codigo" class="input-large {required:true}" value="${indicador.getCodigo()}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nombre" class="control-label">Nombre</label>
                        <div class="controls">
                        <input type="text" id="nombre" name="nombre" class="input-xxlarge {required:true}" value="${indicador.getNombre()}"/>
                        </div>
                    </div>
                        
                        
                       <div class="control-group">
                        <label for="caracteristica" class="control-label">Asignar Caracteristica</label>
                        <div class="controls">
                            <select id="caracteristica" name="caracteristica">
                                <option></option>
                                <c:forEach items="${listcaracteristicas}" var="row" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${row != indicador.getCaracteristicaId()}">
                                            <option value="${row.id}">${row.getFactorId().getId()}.${row.id} ${row.nombre}</option>    
                                        </c:when>
                                        <c:otherwise>
                                            <option selected="selected" value="${row.id}">${row.getFactorId().getId()}.${row.id} ${row.nombre}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>            
                        </div>
                    </div>
                         <div class="control-group">
                        <label  class="control-label">Asignar Preguntas</label>
                        <div class="controls">
                       <ul id="fcbklist">
                                <c:forEach items="${listpreguntas}" var="item" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${item.indicadorId != indicador}">
                                            <li>
                                                <strong>${item.codigo}</strong><br/> 
                                                <span class="fcbkitem_text">${item.pregunta}</span>
                                                <input name="P${item.id}" type="hidden" value="0"/>
                                            </li>

                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <strong>${item.codigo}</strong><br/> 
                                                <span class="fcbkitem_text">${item.pregunta}</span>
                                                <input name="P${item.id}" type="hidden" checked="checked" value="0"/>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>   
                        <input name="idI" type="hidden" value="${indicador.getId()}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Guardar cambios</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>