<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formCrearIndic" method="post">
                <fieldset>
                    <legend>Crear Pregunta</legend>
                        <label for="pregunta">Pregunta</label>
                        <textarea rows="3" name="pregunta" id="pregunta" class="input-xlarge {required:true}"></textarea>
                        
                        <label for="pregunta">Tipo de la Pregunta</label>
                        <select>
                                                <option data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/f1.gif'>" rel="popover" data-original-title="Elegir del 1 al 5" value="0">Elegir del 1 al 5</option>
                                                <option value="1">Si/No</option>
                        </select>
                        
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Crear Pregunta</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>