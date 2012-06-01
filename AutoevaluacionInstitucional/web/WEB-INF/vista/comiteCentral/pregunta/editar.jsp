<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
<script type="text/javascript">
    $(function(){
        $("option[rel=popover]")
        .popover()
        .click(function(e) {
            e.preventDefault();
                    
        }); 
        
        $("#formEditarPreg").validate({
            submitHandler: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController2?action=editarPreguntaCC",
                    data: $("#formEditarPreg").serialize(),
                    success: function(){
                        location = "<%=request.getContextPath()%>/#listarPreguntas"
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
            <form id="formEditarPreg" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Pregunta</legend>
                    <div class="control-group">
                        <label for="codigo" class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input type="text" value="${pregunta.getCodigo()}" class="{required:true}" name="codigo" id="codigo" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="pregunta" class="control-label">Pregunta</label>
                        <div class="controls">
                            <textarea rows="3" name="pregunta" id="pregunta" class="input-xxlarge {required:true}">${pregunta.getPregunta()}</textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select name="tipo" id="tipo">
                                <c:choose>
                                        <c:when test="${pregunta.getTipo().equals('Elegir 1-5')}">
                                            <option selected="selected" data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/1-5.png'>" rel="popover" data-original-title="Elegir del 1 al 5" value="Elegir 1-5">Elegir del 1 al 5</option>
                                            <option data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/Si-No.png'>" rel="popover" data-original-title="Si/No" value="Si/No">Si/No</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option  data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/1-5.png'>" rel="popover" data-original-title="Elegir del 1 al 5" value="Elegir 1-5">Elegir del 1 al 5</option>
                                            <option selected="selected" data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/Si-No.png'>" rel="popover" data-original-title="Si/No" value="Si/No">Si/No</option>
                                        </c:otherwise>   
                                </c:choose>
                            </select>
                        </div>
                    </div>
                            
                    <div class="control-group">
                        <label for="indicador" class="control-label">Asignar Indicador</label>
                        <div class="controls">
                            <select id="indicador" name="indicador">
                                <option></option>
                                <c:forEach items="${listindicadores}" var="row" varStatus="iter">
                                <c:choose>
                                        <c:when test="${pregunta.getIndicadorId()!= row}">
                                            <option value="${row.id}">${row.nombre}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option selected="selected" value="${row.id}">${row.nombre}</option>
                                        </c:otherwise>       
                                </c:choose>    
                                    
                                </c:forEach>
                            </select>                
                                            
                        </div>
                    </div>

                        <input name="idP" type="hidden" value="${pregunta.getId()}">
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Guardar Cambios</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>