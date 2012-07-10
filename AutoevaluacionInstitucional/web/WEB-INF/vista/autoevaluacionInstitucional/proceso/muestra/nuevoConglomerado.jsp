<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
<script type="text/javascript">
    $(function(){
        $("#formAsigMue3").validate({
            submitHandler: function(){
                $('#myModalNuevoConglomerado').modal('hide');
                $('#myModalNuevoConglomerado').on('hidden', function () {
                    $("#selectConglomerado option[value='nuevoCriterio']").attr("selected", "selected");
                    $("#enlace").show();
                });
            }
        }); 
        
    });
    
    $("#criterio").change(function(){
        $("#aceptarConglomerado").show();
        $("#nuevoCriterio").hide();
        if($("#criterio option:selected").val() == "nuevoCriterio"){
            $("#descripcionCriterio").hide();
            $("#formCriterio").show();
            $("#aceptarConglomerado").hide();
            $("#nuevoCriterio").show();
        }  
        else if($("#criterio option:selected").val() != ""){
            $("#formCriterio").hide();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=descripcionConglomerado",
                data: $('#formAsigMue3').serialize(),
                success: function(data){
                    $("#descripcionCriterio").html(data);
                    $("#descripcionCriterio").show();
                    
                }
            }) 
        
        }
        else{
            $("#formCriterio").hide();
            $("#descripcionCriterio").hide();
        }
    });
    
    function removeFormField(id) {
        $(id).remove();
    }
    

    function addFormField() {
        var id = document.getElementById("id").value;
        $("#divTxt").append("<p id='row" + id + "'><label for='txt" + id + "'>Nuevo Elemento: <input type='text' size='20' name='criterio" + id + "' id='txt" + id + "'> <a href='#' onClick='removeFormField(\"#row" + id + "\"); return false;'>Borrar</a><p>");

        $("#row" + id).highlightFade({
            speed:1000
        });

        id = (id - 1) + 2;
        document.getElementById("id").value = id;
    }
    
    $("#nuevoCriterio").click(function(){    
        var formularioA=$('#formAsigMue').serialize();
        var formularioB=$('#formAsigMue3').serialize();
        var Todo=formularioA+"&"+formularioB;
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=pnuevoConglomeradoAI",
            data: $('#formAsigMue3').serialize(),
            success: function(data){
                $("#aceptarConglomerado").show();
                $("#nuevoCriterio").hide();
                $("#formCriterio").hide();
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=conglomeradoExistenteAI",
                    data: $('#formAsigMue3').serialize(),
                    success: function(data){
                        $("#selectConglomerado2").html(data);
                    }
                }) 
            }
        }) 
    });
    
 
</script>
<form  id="formAsigMue3" method="post">
    <fieldset>
        <div class="modal-header">
            <a data-dismiss="modal" class="close">×</a>
            <h3>Nuevo Conglomerado.</h3>
        </div>
        <div class="modal-body">
            <div id="selectConglomerado2">
                <div class="control-group">
                    <label class="control-label" for="criterio">Seleccion un conglomerado existente</label>
                    <div class="controls">
                        <select id="criterio" name="selectCriterio" class="{required:true}">
                            <option></option>
                            <c:forEach items="${listCriterio.rowsByIndex}" var="item">
                                <option value="${item[0]}">${item[1]}</option>
                            </c:forEach>
                            <option value="nuevoCriterio">Nuevo Criterio</option>
                        </select>   
                    </div>
                </div>
            </div>
            <div id="formCriterio" style="display: none">
                <div class="control-group">
                    Nuevo criterio de comglomerado:
                    <label class="control-label" for="input">Nombre:</label>
                    <div class="controls">
                        <input type="text" id="input" name="nombreCriterio">
                    </div>
                    <label class="control-label" for="input01">Descripción:</label>
                    <div class="controls">
                        <textarea id="input01" name="descripcionCriterio"></textarea>
                    </div>
                    Elementos del nuevo criterio:
                    <label class="control-label" for="input1">Nuevo Elemento </label>
                    <div class="controls">
                        <input type="text" id="input1" name="criterio0">
                    </div>
                    <input type="hidden" id="id" value="1" name="countCriterio">
                    <div id="divTxt"></div>
                </div>
                <p><a href="#" onClick="addFormField(); return false;">Añadir</a></p>
            </div>
            <div id="descripcionCriterio" style="display: none">
            </div>
        </div>
        <div class="modal-footer">
            <a class="btn btn-secundary" name="cancelarConglomerado" data-dismiss="modal" href="#">Cerrar</a>
            <button id="aceptarConglomerado" name="aceptarConglomerado" class="btn btn-primary" type="submit">Aceptar</button>
            <a id="nuevoCriterio" class="btn btn-primary"  href="#" style="display: none">Crear</a>
        </div>
    </fieldset>
</form>
