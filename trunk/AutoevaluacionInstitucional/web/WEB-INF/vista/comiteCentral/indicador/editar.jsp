<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        //id(ul id),width,height(element height),row(elements in row)        
        $.fcbkListSelection("#fcbklist","600","50","3");       
        
        $("#formEditarIndic").validate({
            submitHandler: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController2?action=editarIndicadorAI",
                    data: $("#formEditarIndic").serialize(),
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
                        <label for="nombre" class="control-label">Nombre</label>
                        <div class="controls">
                        <input type="text" id="nombre" class="input-xxlarge {required:true}" value="${indicador.getNombre()}"/>
                        </div>
                    </div>
                        
                        
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripcion</label>
                        <div class="controls">
                        <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}">${indicador.getDescripcion()}</textarea>
                        </div>
                    </div>
                        
                        
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Guardar cambios</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>