<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
<script type="text/javascript" src="jQuery/dragDrop/fcbklistselection.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() { 
        $.fcbkListSelection("#fcbklist", "1000","50","6");     
    });
    $("#botonActualizarMuestra").click(function(){

        var a = $("#select5 option:selected").index();
        var b = $("#select6 option:selected").index();
        $("#select3 option:eq("+a+")").attr("selected", "selected");
        $("#select4 option:eq("+b+")").attr("selected", "selected");
        $("#filtro01").show();
        $("#filtro02").hide();
        
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=asignarMuestraAIp",
            data: $("#formAsigMue").serialize(),
            beforeSend: function(){
                $("#resultados4").html("<div class='alert alert-info' id='cargando' style='display: none; width: 221px;'>"
                    +"<a class='close'>×</a>"
                    +"<img src='<%=request.getContextPath()%>/css/images/wait.gif' style='float:left; padding: 1px 10px 0 0;'>"
                    +"Cargando..."
                    +"</div>");
            },
            error: function(error1, error2, error3){
                console.log("eeror");
        
                console.log(error1.responseText);
            },
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestra2AI",
                    data: $("#formAsigMue").serialize(),
                    success: function(){
                        $.ajax({
                            type: 'POST',
                            url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra2AI",
                            success: function(data){
                                $("#resultados4").hide();
                                $("#resultados4").html(data);
                                $("#resultados4").show(); 
                            }
                        })
                    } //fin success                                          
                }); //fin $.ajax
            } //fin success                    
        }); //fin $.ajax
    }) 
    $("#botonCancelar").click(function(){
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra2AI",
            success: function(data){
                var a = $("#select5 option:selected").index();
                var b = $("#select6 option:selected").index();
                $("#select3 option:eq("+a+")").attr("selected", "selected");
                $("#select4 option:eq("+b+")").attr("selected", "selected");
                $("#filtro01").show();
                $("#filtro02").hide();
                $("#resultados4").html(data);
                $("#resultados4").show();
            }
        })
    }) 
     
</script>
<h4> Editar Muestra Generada Para la Fuente Seleccionada</h4>
<br>
<c:if test="${aux_selectorAsignarM3 == 1}">
    <ul id="fcbklist">
        <c:forEach items="${muestras.rowsByIndex}" var="item" varStatus="iter">
            <c:set var="auxx" value="1"></c:set>
            <c:forEach items="${muestrasSeleccionadas.rowsByIndex}" var="item2" varStatus="iter2">
                <c:if test="${item[0] == item2[2]}">
                    <c:set var="varaux" value="0"/>
                    <c:forEach items="${selectorAsignarM33.rowsByIndex}" var="item3" varStatus="iter2">
                        <c:if test="${item[1] == item3[0]}">
                            <c:set var="varaux" value="1"/>
                            <c:if test="${item3[1] == 'terminado'}">
                                <li style="background-color: #DFF0D8; color: #468847;">
                                    <c:set var="auxx" value="0"></c:set>
                                    <strong>${item[2]} ${item[3]}</strong><br/> 
                                    <span class="fcbkitem_text">${item[1]}</span>
                                    <input name="${item[0]}" type="hidden" checked="checked" value="0"/>
                                </li>
                            </c:if>
                            <c:if test="${item3[1] == 'guardada'}">
                                <li style="background-color: #D9EDF7; color: #3A87AD;">
                                    <c:set var="auxx" value="0"></c:set>
                                    <strong>${item[2]} ${item[3]}</strong><br/> 
                                    <span class="fcbkitem_text">${item[1]}</span>
                                    <input name="${item[0]}" type="hidden" checked="checked" value="0"/>
                                </li>
                            </c:if>                   
                        </c:if>
                    </c:forEach>
                    <c:if test="${varaux == 0}">
                        <li style="background-color: #F2DEDE; color: #B94A48;">
                            <c:set var="auxx" value="0"></c:set>
                            <strong>${item[2]} ${item[3]}</strong><br/> 
                            <span class="fcbkitem_text">${item[1]}</span>
                            <input name="${item[0]}" type="hidden" checked="checked" value="0"/>
                        </li>
                    </c:if>
                </c:if>
            </c:forEach>
            <c:if test="${auxx == 1}">
                <li>
                    <c:set var="auxx" value="0"></c:set>
                    <strong>${item[2]} ${item[3]}</strong><br/> 
                    <span class="fcbkitem_text">${item[1]}</span>
                    <input name="${item[0]}" type="hidden" value="0"/>
                </li>
            </c:if>
        </c:forEach> 
    </ul>
    <div class="form-actions">
        <button class="btn btn-primary" id="botonActualizarMuestra" type="button">Actualizar Muestra Para Fuente Seleccionada</button>
        <button class="btn btn-secundary" id="botonCancelar" type="button">Cancelar</button>
    </div>
</c:if>
<c:if test="${aux_selectorAsignarM3 == 0}">
    <p class="help-block">Debe seleccionar un programa y un semestre.</p>
    <button class="btn btn-secundary" id="botonCancelar" type="button">Cancelar</button>
</c:if>