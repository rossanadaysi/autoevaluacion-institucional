<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    
    $(function(){
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
        
        
        
        
        $('a[href^=#InformacionDocumental]').click(function() {
     
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
<c:if test="${auxInfoDocumental == 0}"><!--Si no se ha asignado nada-->
    <div class="subnav" data-top="80">
        <ul class="nav nav-pills">
            <c:forEach items="1,2,3,4,5,6,7,8,9,10" var="row" varStatus="iter">
                <c:choose>
                    <c:when test="${(iter.index == 0)}">
                        <li class="active"><a href="#InformacionDocumental${iter.index+1}">Factor ${iter.index + 1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#InformacionDocumental${iter.index+1}">Factor ${iter.index + 1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach> 
        </ul>
    </div>
    <div class="hero-unit">
        <div class="row">
            <div id="conte" class="span12">
                <form id="formInfoDoc" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluar informaci&oacute;n documental</legend>
                        <table class="table table-striped">
                            <thead>
                            <th>Indicador</th>
                            <th>Documento asociado</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acción a implementar u observación</th>
                            </thead>
                            <tbody>

                                <c:set var="fActual" value="0"></c:set>
                                <c:forEach items="${indicadoresDocumental.rowsByIndex}" var="row" varStatus="iter">

                                    <c:choose>
                                        <c:when test="${fActual!=row[2]}">
                                            <tr id="InformacionDocumental${row[2]}">    
                                                <c:set var="fActual" value="${row[2]}"></c:set>
                                            </c:when>    
                                            <c:otherwise>
                                            <tr>    
                                            </c:otherwise>
                                        </c:choose>
                                        <td style="text-align: justify;">   
                                            <c:out value="${row[1]} ${row[3]}"/>
                                        </td>
                                        <td>
                                            <textarea name="nombreDocumento${row[0]}" class="span2" placeholder="Documento asociado"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="responsableDocumento${row[0]}" class="span2" placeholder="Responsable"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="medioDocumento${row[0]}" class="span1" placeholder="Medio"></textarea>
                                        </td>
                                        <td>
                                            <textarea name="lugarDocumento${row[0]}" class="span2" placeholder="Lugar"></textarea>
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
                            <button class="btn btn-primary" type="submit">Evaluar Información Documental</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
<c:if test="${auxInfoDocumental == 1}">
    <div class="hero-unit" >
        <div class="row">
            <div id="conte" class="span12">
                <form id="formInfoDoc" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluar información documental</legend>
                        <div class="alert alert-info">
                            <a data-dismiss="alert" class="close">×</a>
                            <strong>Información!</strong>
                            La evaluación de información documental ya ha sido asignada. <a href="<%=request.getContextPath()%>/#listarEvaluarDoc">Detalle de información documental Evaluada.</a>
                        </div>
                        <br>
                        <table class="table table-striped">
                            <thead>
                            <th>Indicador</th>
                            <th>Documento asociado</th>
                            <th>Responsable</th>
                            <th>Medio</th>
                            <th>Lugar</th>
                            <th>Estado</th>
                            <th>Acci&oacute;n a implementar u observaci&oacute;n</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${evaluarcionDocumental.rowsByIndex}" var="row2" varStatus="iter">
                                    <tr id="InformacionDocumental${iter.index+1}">
                                        <td>   
                                            <c:out value="${row2[0]} ${row2[1]}"/>
                                        </td>
                                        <td>
                                            <textarea name="nombreDocumento${row2[0]}" rows="4" class="span2">${row2[2]}</textarea>
                                        </td>
                                        <td>
                                            <textarea name="responsableDocumento${row2[0]}" rows="4" class="span2">${row2[3]}</textarea>
                                        </td>
                                        <td>
                                            <textarea name="medioDocumento${row2[0]}" rows="4" class="span1">${row2[4]}</textarea>
                                        </td>
                                        <td>
                                            <textarea name="lugarDocumento${row2[0]}" rows="4" class="span2">${row2[5]}</textarea>
                                        </td>
                                        <td>
                                            <select  class="span1" name="evaluacionDoc${row2[0]}">
                                                <c:choose>
                                                    <c:when test="${row2[6] == 1.0}">
                                                        <option selected="selected" value="1.0">1.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="1.0">1.0</option>
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
                                                    <c:when test="${row2[6] == 3.0}">
                                                        <option selected="selected" value="3.0">3.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="3.0">3.0</option>
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
                                                    <c:when test="${row2[6] == 5.0}">
                                                        <option selected="selected" value="5.0">5.0</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="5.0">5.0</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <input type="hidden"  value="${row2[0]}" name="idIndicadorDoc${row2[0]}">
                                        </td>
                                        <td>
                                            <textarea name="accionDocumento${row2[0]}" rows="4" class="span2">${row2[7]}</textarea>
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}"/>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Actualizar Evaluación</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </div><!--/span-->        
        </div><!--/row-->    
    </div><!--/hero-unit--> 
</c:if>
