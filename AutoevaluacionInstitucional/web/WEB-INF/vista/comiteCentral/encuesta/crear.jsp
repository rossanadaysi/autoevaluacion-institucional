<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        //id(ul id),width,height(element height),row(elements in row)        
        $.fcbkListSelection("#fcbklist","600","50","3");       
        
        $("#formCrearEncu").validate({
            submitHandler: function(){
                $.ajax({
                    type: 'POST', 
                    url: "<%=request.getContextPath()%>/formController2?action=crearEncuestaCC",
                    data: $("#formCrearEncu").serialize(),
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
        $("button[type='reset']").click(function(){
        elem = $("#fcbklist");
            $.each(elem.children("li").children(".fcbklist_item"), function(i, obj){
                obj = $(obj);
                
                if (obj.hasClass("itemselected")) {
                    obj.find("input:hidden").val("0");
                    $("#view_selected_count").text(parseInt($("#view_selected_count").text(), 10) - 1);
                    obj.parents("li").removeAttr("addedid");
                    removeValue(obj);
                }
                obj.removeClass("itemselected");
                obj.parents("li").removeClass("liselected");
            })
        })
        
        
    }); //fin (document).ready        
</script>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formCrearEncu" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear encuesta</legend>
                    <div class="control-group">
                        <label for="nombre" class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" id="nombre" name="nombre" class="input-xlarge {required:true}" value=""/>
                         </div>
                    </div>
                    

                    <div class="control-group">
                        <label for="objetivo" class="control-label">Objetivo</label>
                        <div class="controls">
                        <textarea rows="3" name="objetivo" id="objetivo" class="input-xxlarge {required:true}"></textarea>
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