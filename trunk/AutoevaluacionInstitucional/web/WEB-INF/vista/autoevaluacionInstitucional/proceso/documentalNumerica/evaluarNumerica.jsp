<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    
    $(function(){
        if(${aux_index2 == 2}){
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/ControllerAI?action=recargarEstado",
                success: function(data){
                    $("#estado").empty();
                    $("#estado").html(data);
                }
            });
        }
        
        $(".cambiable").each(function() {
            // Save current value of element
            $(this).data('oldVal', $(this).val());

            // Look for changes in the value
            $(this).bind("change", function(event){
                // If value has changed...
                if ($(this).data('oldVal') != $(this).val()) {
                    $(this).parents('tr').children("input[name=^'InfoCambio']").attr("value","1");
                }
            });
        });
        
        
        $("textarea").focus(function(){
            $(this).css("position","absolute");
            $(this).animate({
                width: 350,
                height: 200,
                left: '-=100'
            }, 500);     
        })
        $("textarea").focusout(function() {
            $(this).removeAttr("style");
        })
        
        $("#actualiza").click(function(){
            $(this).button('loading');
            $("#formInfoNum").submit();
            
        });
    
        $("textarea").keyup(function(e){
            
            if (e.keyCode == 27){
                $(this).blur();
            }
        });
        
        
        
        $('a[href^=#InformacionNumerica]').click(function() {
     
            if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'')
                && location.hostname == this.hostname) {

                var $target2 = $(this.hash);
             
                $target2 = $target2.length && $target2 || $('[name=' + this.hash.slice(1) +']');
                if ($target2.length) {
                    var targetOffset = $target2.offset().top;
                    var actual = $('div.ui-layout-center').scrollTop();
                    if(actual!=0){
                        $('div.ui-layout-center').animate({scrollTop: actual + targetOffset - 80}, 500);
                    }else{
                        $('div.ui-layout-center').animate({scrollTop: targetOffset - 118}, 500);
                    }
                    

                    return false;
                }

            }
        });
    });
    
</script>
<br>
<c:if test="${auxInfoNumerica == 0}"><!--Si no se ha asignado nada-->
    <div class="subnav" data-top="80">
        <ul class="nav nav-pills">
            <c:forEach items="1,2,3,4,5,6,7,8,9,10" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${(iter.index == 0)}">
                        <li class="active"><a href="#InformacionNumerica${iter.index+1}">Factor ${iter.index + 1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#InformacionNumerica${iter.index+1}">Factor ${iter.index + 1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach> 
        </ul>
    </div>
    <div class="hero-unit">
        <div class="row">
            <div id="conte" class="span12">
                <form id="formInfoNum" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluar información numérica</legend>
                        <table class="table table-striped">
                            <thead>
                            <th>Cod.</th>
                            <th>Documento asociado</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acción a implementar u observación</th>
                            </thead>
                            <tbody>
                                <c:set var="fActual" value="0"></c:set>
                                <c:forEach items="${indicadoresNumerica.rowsByIndex}" var="row" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${fActual!=row[2]}">
                                            <tr id="InformacionNumerica${row[2]}">    
                                                <c:set var="fActual" value="${row[2]}"></c:set>
                                            </c:when>    
                                            <c:otherwise>
                                            <tr>    
                                            </c:otherwise>
                                        </c:choose>
                                        <td style="text-align: justify;">    
                                            <c:out value="${row[1]}"/>
                                        </td>
                                        <td>
                                            <textarea name="nombreDocumento${row[0]}" rows="4" class="span2" placeholder="Documento asociado"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="responsableDocumento${row[0]}" rows="4" class="span2" placeholder="Responsable"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="medioDocumento${row[0]}" rows="4" class="span1" placeholder="Medio"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="lugarDocumento${row[0]}" rows="4" class="span2" placeholder="Lugar"></textarea>
                                        </td>
                                        <td>
                                            <select class="span1"  name="evaluacionDoc${row[0]}">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                            </select>
                                            <input type="hidden"  value="${row[0]}" name="idIndicadorDoc${row[0]}">
                                        </td>
                                        <td>
                                            <textarea name="accionDocumento${row[0]}" rows="4" class="span2" placeholder="Acci&oacute;n a implementar u observaci&oacute;n"></textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Evaluar Información Numérica</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
<c:if test="${auxInfoNumerica == 1}">
    <div class="subnav" data-top="80">
        <ul class="nav nav-pills">
            <c:forEach items="1,2,3,4,5,6,7,8,9,10" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${(iter.index == 0)}">
                        <li class="active"><a href="#InformacionNumerica${iter.index+1}">Factor ${iter.index + 1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#InformacionNumerica${iter.index+1}">Factor ${iter.index + 1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach> 
        </ul>
    </div>
    <div class="hero-unit" >
        <div class="row">
            <div id="conte" class="span12">
                <form id="formInfoNum" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluar información numérica</legend>
                        <div class="alert alert-info">
                            <a data-dismiss="alert" class="close">×</a>
                            <strong>Información!</strong>
                            La evaluación de información numérica ya ha sido asignada. <a href="<%=request.getContextPath()%>/#listarEvaluarNum">Detalle de información numérica evaluada.</a>
                        </div>
                        <br>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Código del indicador</th>
                                    <th>Documento asociado</th>
                                    <th>Responsable</th>
                                    <th>Medio</th>
                                    <th>Lugar</th>
                                    <th>Estado</th>
                                    <th>Acción a implementar u observación</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="fActual" value="0"></c:set>
                                <c:forEach items="${evaluarcionNumerica.rowsByIndex}" var="row2" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${fActual!=row2[10]}">
                                            <tr id="InformacionNumerica${row2[10]}">
                                        <input type="hidden" name="InfoCambio${row2[0]}" value="0">
                                        <c:set var="fActual" value="${row2[10]}"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>  
                                        <input type="hidden" name="InfoCambio${row2[0]}" value="0">
                                    </c:otherwise>
                                </c:choose>
                                <td>   
                                    <c:out value="${row2[9]} ${row2[1]}"/>
                                </td>
                                <td>
                                    <textarea name="nombreDocumento${row2[0]}" rows="4" class="span2 cambiable" placeholder="Documento asociado">${row2[2]}</textarea>
                                </td>
                                <td>
                                    <textarea name="responsableDocumento${row2[0]}" rows="4" class="span2 cambiable" placeholder="Responsable">${row2[3]}</textarea>
                                </td>
                                <td>
                                    <textarea name="medioDocumento${row2[0]}" rows="4" class="span1 cambiable" placeholder="Medio">${row2[4]}</textarea>
                                </td>
                                <td>
                                    <textarea name="lugarDocumento${row2[0]}" rows="4" class="span2 cambiable" placeholder="Lugar">${row2[5]}</textarea>
                                </td>
                                <td>
                                    <select  class="span1 cambiable" name="evaluacionDoc${row2[0]}">
                                        <c:choose>
                                            <c:when test="${row2[6] == 1}">
                                                <option selected="selected" value="1">1</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="1">1</option>
                                            </c:otherwise>
                                        </c:choose>                                        
                                        <c:choose>
                                            <c:when test="${row2[6] == 2}">
                                                <option selected="selected" value="2">2</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="2">2</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${row2[6] == 3}">
                                                <option selected="selected" value="3">3</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="3">3</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${row2[6] == 4}">
                                                <option selected="selected" value="4">4</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="4">4</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${row2[6] == 5}">
                                                <option selected="selected" value="5">5</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="5">5</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <input type="hidden"  value="${row2[0]}" name="idIndicadorDoc${row2[0]}">
                                    <input type="hidden"  value="${row2[8]}" name="idnumericaDoc${row2[0]}">
                                </td>
                                <td>
                                    <textarea name="accionDocumento${row2[0]}" rows="4" class="span2 cambiable" placeholder="Acci&oacute;n a implementar u observaci&oacute;n">${row2[7]}</textarea>
                                </td>
                                </tr>
                                <c:set var="iterador" value="${iter.index + 1}"/>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" id="actualiza" data-original-title="Actualizar Evaluaci&oacute;n" type="button" data-loading-text="Actualizando..." autocomplete="off">Actualizar Evaluación</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
