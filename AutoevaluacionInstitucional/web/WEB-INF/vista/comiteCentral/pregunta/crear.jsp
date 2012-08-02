<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        $("option[rel=popover]")
        .popover()
        .click(function(e) {
            e.preventDefault();
                    
        });
        
        $("option[rel=popover2]")
        .popover({
            placement: 'left'
        });
        
        
        $("#formCrearPreg").validate({
            submitHandler: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController2?action=crearPreguntaCC",
                    data: $("#formCrearPreg").serialize(),
                    success: function(){
                        location = "<%=request.getContextPath()%>/#listarPreguntas"
                    } //fin success
                }); //fin $.ajax    
            }
        });
        
        
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearPreg" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear pregunta</legend>
                    <div class="control-group">
                        <label for="codigo" class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input type="text" value="" class="{required:true}" name="codigo" id="codigo" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="pregunta" class="control-label">Pregunta</label>
                        <div class="controls">
                            <textarea rows="3" name="pregunta" id="pregunta" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select name="tipo" id="tipo">
                                <option data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/1-5.png'>" rel="popover" data-original-title="Elegir del 1 al 5" value="Elegir 1-5">Elegir del 1 al 5</option>
                                <option data-content="<img style='text-align: center;  margin:0 auto;' src='<%=request.getContextPath()%>/css/images/Si-No.png'>" rel="popover" data-original-title="Si/No" value="Si/No">Si/No</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="indicador" class="control-label">Asignar Indicador</label>
                        <div class="controls">
                            <select id="indicador" name="indicador" class="span">
                                <option></option>
                                <c:forEach items="${listindicadores}" var="row" varStatus="iter">
                                    <option data-content="${row.nombre}" rel="popover2" data-original-title="Indicador" value="${row.id}">${row.codigo}</option>
                                </c:forEach>
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