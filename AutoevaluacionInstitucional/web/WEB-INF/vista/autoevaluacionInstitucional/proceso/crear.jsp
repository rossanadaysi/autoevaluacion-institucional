<%-- 
    Document   : newProceso
    Created on : 23-sep-2011, 9:51:55
    Author     : Usuario
--%>

<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Proceso</title>
    </head>
    <body>
        <br>
        <div class="hero-unit">
        <div class="row">
        <div class="span8">
            
        <form id="formCrearProc" class="form-horizontal" method="post">
        <fieldset>
          <legend>Nuevo Proceso de Autoevaluación</legend>
          <div class="control-group">
            <label for="programa"  class="control-label">Programa</label>
            <div class="controls">
              <span type="text" id="programa"  class="input-xlarge uneditable-input">${programa.nombre}</span>
            </div>
          </div>
          <div class="control-group">
            <label for="fechaI" class="control-label">fecha de Inicio</label>
            <div class="controls">
                <span id="fechaI" class="input-xlarge uneditable-input">Proceso en Configuraci&oacute;n</span>
            </div>
          </div>
          <div class="control-group">
            <label for="descripcion" class="control-label">Descripcion</label>
            <div class="controls">
              <textarea rows="3" id="descripcion" name="descripcion" class="input-xlarge"></textarea>
            </div>
          </div>
            <div class="form-actions">
            <button class="btn btn-primary" type="submit">Crear Proceso</button>
            <button class="btn" type="reset">Cancelar</button>
            </div>
         </fieldset>
        </form>
        </div><!--/span-->        
        </div><!--/row-->    
        </div><!--/hero-unit-->    
        
      </body>
</html>
