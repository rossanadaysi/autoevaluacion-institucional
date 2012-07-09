<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        $("#encuesta").change(function(){
            $.ajax({ 
                type: "POST", 
                url: "<%=request.getContextPath()%>/ControllerAI?action=informeRealAI",
                data: "encuesta="+$("#encuesta option:selected").val(),
                beforeSend :function(){
                    $("div.ui-layout-center").append("<div id='contenido'></div>");
                    $("#contenido").hide();
                    $("div.ui-layout-center").append("<div class='page_loading'>"
                        +"<span>Cargando</span>"
                        +"<img src='css/images/loading.gif' style='margin-left:6px;'>"
                        +"</div>");
                                
                },
                success: function(data) 
                {
                    $("#contenido").append(data);
                    $("#contenido").show(200, function(){
                        $(".page_loading").hide();
                    })     
                                
                } //fin success
            }); //fin del $.ajax    
        });
    })
</script>
<br>
<div class="hero-unit">
    <div class="row">
        <div class="span8">
            <form id="formCrearProc" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Informe de resultados por encuesta</legend>
                    <div class="control-group">
                        <label for="encuesta" class="control-label">Seleccione una encuesta</label>
                        <div class="controls">
                            <select id="encuesta" name="encuesta">
                                <option></option>
                                <c:forEach items="${listencuestas.rowsByIndex}" var="row" varStatus="iter">
                                    <option value="${row[0]}">${row[1]}</option>
                                </c:forEach>
                            </select>                
                        </div>
                    </div>
                </fieldset>

            </form>
        </div>
    </div>
</div>