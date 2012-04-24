<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formCrearEncu" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Encuesta</legend>
                    <div class="control-group">
                        <label for="nombre" class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" id="nombre" class="input-xlarge" value=""/>
                        </div>
                    </div>
                    

                    <div class="control-group">
                        <label for="descripcion" class="control-label">Objetivo</label>
                        <div class="controls">
                        <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="instrucciones" class="control-label">Instrucciones</label>
                        <div class="controls">
                        <textarea rows="3" name="instrucciones" id="instrucciones" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>


                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Encuesta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>