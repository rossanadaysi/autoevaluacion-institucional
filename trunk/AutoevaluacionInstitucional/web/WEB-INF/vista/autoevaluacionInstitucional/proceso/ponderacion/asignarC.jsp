<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    function presionSubmit(va, idc)
    {
             
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=cargarPonde",
            data: $("#formPondeCara").serialize() + "&idc=" + idc,
            dataType: "json",
            success: function(data){
                $.each(data['0']["datos"], function(index, value) {
                    var b = data['0']["datos"][index]['Pond'];
                    var a = data['0']["datos"][index]['Idc'];
                    a="ponderacion2"+a;
                    $("input[name='"+a+"']").val(""+b);
                });
              
            } //fin success
                                            
        }); //fin */
        
        
    }

    $(document).ready(function() {
        if(${auxAsignarC1} == 0){
            $('#myModalC1').modal(); 
            $('#myModalC1').on('hidden', function () {
                $("ul.nav-list li:eq(2) a").trigger("click");                                                                
            })
        }
        else{
            $('a[href^=#PonderacionCaracteristicas]').click(function() {
     
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
        }
        
        $("#formPondeCara").validate({
            
            submitHandler: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/formController?action=asignarPonderacionCaracteristicaAIp",
                    data: $("#formPondeCara").serialize(),
                    success: function(){                                                
                        $('#myModalC').modal(); 
                        $('#myModalC').on('hidden', function () {
                            location = "<%=request.getContextPath()%>/#listarPonderacionCaracteristica";
                        })
                    } //fin success
                }); //fin $.ajax
            } 
        });
    });
</script>
<c:if test="${auxAsignarC1 != 0}">
    <c:if test="${auxAsignarC == 0}">
        <br id="PonderacionCaracteristicas">
        <div class="subnav">
            <ul class="nav nav-pills" >
                <li><a><strong>Ponderación de Características</strong></a></li>
                <c:forEach items="${caracteristicas.rowsByIndex}" var="row" varStatus="iter">
                    <c:choose>
                        <c:when test="${((iter.index) % 5 == 0) || (iter.index == 0)}">
                            <c:choose>
                                <c:when test="${(iter.index == 0)}">
                                    <li class="active"><a href="#PonderacionCaracteristicas${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="#PonderacionCaracteristicas${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <div class="hero-unit">
            <div class="row">
                <div class="span10">
                    <form id="formPondeCara" class="form-horizontal" method="post">
                        <fieldset>
                            <legend>Ponderación  de Características</legend>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Caracteristica</th>
                                        <th>Nivel de Importancia</th>
                                        <th>Justificación</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${caracteristicas.rowsByIndex}" var="row" varStatus="iter">
                                        <tr id="PonderacionCaracteristicas${iter.index+1}">   
                                            <td>
                                                <c:out value="${row[0]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row[1]}"/>
                                            </td>
                                            <td>
                                                <select class="span1 {required:true}" id="ponderacion${row[0]}" name="ponderacion${row[0]}">
                                                    <option value=""></option>
                                                    <option value="1">1.0</option>
                                                    <option value="1.5">1.5</option>
                                                    <option value="2">2.0</option>
                                                    <option value="2.5">2.5</option>
                                                    <option value="3">3.0</option>
                                                    <option value="3.5">3.5</option>
                                                    <option value="4">4.0</option>
                                                    <option value="4.5">4.5</option>
                                                    <option value="5">5.0</option>
                                                    <option value="5.5">5.5</option>
                                                    <option value="6">6.0</option>
                                                    <option value="6.5">6.5</option>
                                                    <option value="7">7.0</option>
                                                    <option value="7.5">7.5</option>
                                                    <option value="8">8.0</option>
                                                    <option value="8.5">8.5</option>
                                                    <option value="9">9.0</option>
                                                    <option value="9.5">9.5</option>
                                                    <option value="10">10.0</option>
                                                </select>
                                                <input type="hidden"  value="${row[0]}" name="id${row[0]}">
                                            </td>
                                            <td>
                                                <textarea name="justificacion${row[0]}" rows="4" class="span5 {required:true}"></textarea>
                                            </td>
                                        </tr>
                                        <c:set var="iterador" value="${iter.index + 1}" />
                                    </c:forEach>
                                </tbody>
                            </table>
                            <input type="hidden" name="count" value="${iterador}">
                            <div class="form-actions">
                                <button class="btn btn-primary" type="submit">Asignar Ponderación</button>
                                <button class="btn" type="reset">Cancelar</button>
                            </div>
                        </fieldset>
                    </form>
                </div><!--/span-->        
            </div><!--/row-->    
        </div><!--/hero-unit-->
    </c:if>
    <c:if test="${auxAsignarC == 1}">
        <br id="PonderacionCaracteristicas">

        <div class="subnav" >
            <ul class="nav nav-pills">
                <li><a><strong>Ponderación de Características</strong></a></li>
                <c:forEach items="${caracteristicas.rowsByIndex}" var="row" varStatus="iter">
                    <c:choose>
                        <c:when test="${((iter.index) % 5 == 0) || (iter.index == 0)}">
                            <c:choose>
                                <c:when test="${(iter.index == 0)}">
                                    <li class="active"><a href="#PonderacionCaracteristicas${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="#PonderacionCaracteristicas${iter.index+1}">${iter.index + 1} - ${iter.index + 5}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <div class="hero-unit" >
            <div class="row">
                <div class="span10">
                    <form id="formPondeCara" class="form-horizontal" method="post">
                        <fieldset>
                            <legend>Ponderación de Características</legend>
                            <div id="alert">
                                <div class="alert alert-info">
                                    <a data-dismiss="alert" class="close">×</a>
                                    <strong>Información!</strong>
                                    La ponderación de caracteristicas ya ha sido actualizada.
                                    <a href="<%=request.getContextPath()%>/#listarPonderacionCaracteristica"> Ver Detalle de Ponderación de Características Asignada.</a>
                                </div>
                            </div>  <!--alert-->
                            <br>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Característica</th>
                                        <th>Nivel de Importancia</th>
                                        <th>Ponderación</th>
                                        <th>Justificacion</th>
                                    </tr>
                                </thead><!--thead-->
                                <tbody>
                                    <c:forEach items="${pondeCaracteristicas.rowsByIndex}" var="row2" varStatus="iter">
                                        <tr id="PonderacionCaracteristicas${iter.index+1}">    
                                            <td>   
                                                <c:out value="${row2[4]}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${row2[5]}"/>
                                            </td>
                                            <td>
                                                <select id="select1" onchange="presionSubmit(this.name, ${row2[4]}) " class="span1" name="ponderacion${row2[4]}">
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 1.0}">
                                                            <option selected="selected" value="1.0">1.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="1.0">1.0</option>
                                                        </c:otherwise>
                                                    </c:choose>   
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 1.5}">
                                                            <option selected="selected" value="1.5">1.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="1.5">1.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 2.0}">
                                                            <option selected="selected" value="2.0">2.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="2.0">2.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 2.5}">
                                                            <option selected="selected" value="2.5">2.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="2.5">2.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 3.0}">
                                                            <option selected="selected" value="3.0">3.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="3.0">3.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 3.5}">
                                                            <option selected="selected" value="3.5">3.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="3.5">3.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 4.0}">
                                                            <option selected="selected" value="4.0">4.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="4.0">4.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 4.5}">
                                                            <option selected="selected" value="4.5">4.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="4.5">4.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 5.0}">
                                                            <option selected="selected" value="5.0">5.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="5.0">5.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 5.5}">
                                                            <option selected="selected" value="5.5">5.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="5.5">5.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 6.0}">
                                                            <option selected="selected" value="6.0">6.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="6.0">6.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 6.5}">
                                                            <option selected="selected" value="6.5">6.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="6.5">6.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 7.0}">
                                                            <option selected="selected" value="7.0">7.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="7.0">7.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 7.5}">
                                                            <option selected="selected" value="1">7.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="7.5">7.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 8.0}">
                                                            <option selected="selected" value="8.0">8.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="8.0">8.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 8.5}">
                                                            <option selected="selected" value="8.5">8.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="8.5">8.5</option>
                                                        </c:otherwise>

                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 9.0}">
                                                            <option selected="selected" value="9.0">9.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="9.0">9.0</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 9.5}">
                                                            <option selected="selected" value="9.5">9.5</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="9.5">9.5</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${row2[6] == 10.0}">
                                                            <option selected="selected" value="10.0">10.0</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="10.0">10.0</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </select>
                                                <input type="hidden"  value="${row2[4]}" name="id${row2[4]}">
                                            </td>
                                            <td>
                                                <div id="ponderacion${row2[4]}">
                                                    <input name="ponderacion2${row2[4]}" type="text" class="input-mini uneditable-input" value="${row2[1]}"/>
                                                </div>
                                            </td>
                                            <td>
                                                <textarea name="justificacion${row2[4]}" rows="4" class="span5 {required:true}">${row2[2]}</textarea>
                                            </td>
                                        </tr>
                                        <c:set var="iterador" value="${iter.index + 1}" />
                                    <input type="hidden"  value="${row[0]}" name="id${row[4]}">
                                </c:forEach>
                                </tbody>
                            </table>
                            <input type="hidden" name="count" id="count" value="${iterador}">
                            <div class="form-actions">
                                <button class="btn btn-primary" type="submit">Actualizar Ponderación</button>
                                <button class="btn" type="reset">Cancelar</button>
                            </div>
                        </fieldset>
                    </form>
                </div><!--/span-->        
            </div><!--/row-->    
        </div><!--/hero-unit--> 
    </c:if>
</c:if>

