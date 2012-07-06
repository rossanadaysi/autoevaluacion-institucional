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
        
        $("#formEditarEncu").validate({
            submitHandler: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController2?action=editarEncuestaCC",
                    data: $("#formEditarEncu").serialize(),
                    
                    success: function(){
                        $("a[href='#listarEncuestas']").click();
                    } //fin success
                }); //fin $.ajax    
            }
        });
        var removeValue = function(obj){
            var randid = obj.find("[type=hidden]").attr("randid");
            var inputid = elem.attr('id') + "_values";
            if ($("#" + inputid).length != 0) {
                try {
                    eval("json = " + $("#" + inputid).val() + ";");
                    var string = "{";
                    $.each(json, function(i, item){
                        if (i && item && i != randid) {
                            string += "\"" + i + "\":\"" + item + "\",";
                        }
                    });
                    //remove last ,
                    if (string.length > 2) {
                        string = string.substr(0, (string.length - 1));
                        string += "}"
                    }
                    else {
                        string = "";
                    }
                    $("#" + inputid).val(string);
                } 
                catch (e) {                
                }
            }
        }
        var seleccionados=[];
        elem = $("#fcbklist");
        $.each(elem.children("li").children(".fcbklist_item"), function(i, obj){
            obj = $(obj);
            if (obj.hasClass("itemselected")) {
                seleccionados.push(obj.find("[type=hidden]").attr("randid"));
            }
                
        })
        
        
        $("button[type='reset']").click(function(){
           
            elem = $("#fcbklist");
            $.each(elem.children("li").children(".fcbklist_item"), function(i, obj){
                obj = $(obj);
                var encontrado = false;
                for (var i = 0; i < seleccionados.length && !encontrado; i++)
                {
                    if(obj.find("[type=hidden]").attr("randid") == seleccionados[i]){
                        encontrado=true;
                      
                    }
                    
                }
                if(!encontrado){
                    if (obj.hasClass("itemselected")) {
                        obj.find("input:hidden").val("0");
                        $("#view_selected_count").text(parseInt($("#view_selected_count").text(), 10) - 1);
                        obj.parents("li").removeAttr("addedid");
                        //removeValue(obj);
                        obj.removeClass("itemselected");
                        obj.parents("li").removeClass("liselected");
                    }
                        
                }else{
                    if (!obj.hasClass("itemselected")) {
                        obj.find("input:hidden").val("1");
                        $("#view_selected_count").text(parseInt($("#view_selected_count").text(), 10) + 1);
                        obj.parents("li").attr("addedid","tester");
                        obj.addClass("itemselected");
                        obj.parents("li").addClass("liselected");
                    }
                    
                }
                    
                
                
                
            })
        })
        
        
        
    
    });         
</script>
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formEditarEncu" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar encuesta</legend>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value="${encuesta.getNombre()}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="objetivo" class="control-label">Objetivo</label>
                        <div class="controls">
                            <textarea rows="3" name="objetivo" id="objetivo" class="input-xxlarge {required:true}">${encuesta.getDescripcion()}</textarea>
                        </div>    
                    </div>
                    <div class="control-group">
                        <label for="instrucciones" class="control-label">Instrucciones</label>
                        <div class="controls">
                            <textarea rows="3" name="instrucciones" id="instrucciones" class="input-xxlarge {required:true}">${encuesta.getInstrucciones()}</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label  class="control-label">Asignar Preguntas</label>
                        <div class="controls">


                            <ul id="fcbklist">
                                <c:forEach items="${listpreguntas}" var="item" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${item.encuestaList.contains(encuesta)}">
                                            <li>
                                                <strong>${item.codigo}</strong><br/> 
                                                <span class="fcbkitem_text">${item.pregunta}</span>
                                                <input name="P${item.id}" type="hidden" checked="checked" value="1"/>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <strong>${item.codigo}</strong><br/> 
                                                <span class="fcbkitem_text">${item.pregunta}</span>
                                                <input name="P${item.id}" type="hidden" value="0"/>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <input name="idE" type="hidden" value="${encuesta.getId()}">

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Guardar cambios</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>
