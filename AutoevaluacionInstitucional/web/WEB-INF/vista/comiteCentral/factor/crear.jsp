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
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formCrearFact" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Factor</legend>
                    <div class="control-group">
                    <label for="nombre"  class="control-label">Nombre</label>
                    <div class="controls">
                    <input type="text" id="nombre" class="input-xlarge" value=""/>
                    </div>
                    </div>
                    <div class="control-group">
                    <label for="descripcion" class="control-label">Descripcion</label>
                    <div class="controls">
                    <textarea rows="3" name="descripcion" id="descripcion" class="input-xlarge {required:true}"></textarea>
                    </div>    
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Asignar Caracteristicas</label>
                        <div class="controls">
                            <ul id="fcbklist">
                        <c:forEach items="${listcaracteristicas}" var="row" varStatus="iter">
                            <li>
                                <strong>${row.nombre}</strong><br/> 
                                <span class="fcbkitem_text">${row.descripcion}</span>
                                <input name="${row.nombre}" type="hidden" value="0"/>
                            </li>
                        </c:forEach>
                    </ul>
                        </div>
                    </div>
                    
                    

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Factor</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>