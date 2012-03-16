<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        $("option[rel=popover]")
        .popover()
        .click(function(e) {
            e.preventDefault();
                    
        }); 
    });
</script>


<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formCrearPreg" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Pregunta</legend>
                    <div class="control-group">
                        <label for="pregunta" class="control-label">Pregunta</label>
                        <div class="controls">
                            <textarea rows="3" name="pregunta" id="pregunta" class="input-xlarge {required:true}"></textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="pregunta" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select>
                                <option data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/1-5.png'>" rel="popover" data-original-title="Elegir del 1 al 5" value="0">Elegir del 1 al 5</option>
                                <option data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/Si-No.png'>" rel="popover" data-original-title="Si/No" value="1">Si/No</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Pregunta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>