<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function(){
        $('#collapseOne').on('hidden', function () {
            $("a[href='#collapseOne']  i").removeClass("icon-arrow-up");
            $("a[href='#collapseOne']  i").addClass("icon-arrow-down");
            
            
            
        });
        $('#collapseOne').on('shown', function () {
            $("a[href='#collapseOne']  i").removeClass("icon-arrow-down");
            $("a[href='#collapseOne']  i").addClass("icon-arrow-up");
            
        }); 
                  
       
        if(${muestraCalculada != null} || ${muestraIndividual != null} || ${muestraCalculada3 != null}){
      
            $("#checkbox2").removeAttr("checked");

            $("#checkbox2").removeAttr("disabled");

           
            if(${conglomerado == "nuevoCriterio"}){
                $("#checkbox2").attr("checked", true);
                $("#checkbox2").attr("disabled", true);
            }else{
                $("#checkbox2").attr("checked", false);
                $("#checkbox2").attr("disabled", false);
            }
            
            $("#botonGenerarMuestra").show();
            $("#botonCalcularMuestra2").show();
            $("#botonCalcularMuestra").hide();    
          
        }
        if(${muestraCalculada == null && muestraCalculada3 == null && muestraIndividual == null}){
            $("#botonGenerarMuestra").hide();
            $("#botonCalcularMuestra2").hide();
            $("#botonCalcularMuestra").show();    
        }
               
    });
</script>

<div class="accordion-group">
    <div class="accordion-heading">
        <a href="#collapseOne" data-parent="#resultados3" data-toggle="collapse" class="accordion-toggle"><i class="icon-arrow-down"></i>
            Muestra Calculada Para la Fuente Seleccionada 
        </a> 
    </div>
    <div class="accordion-body collapse in" id="collapseOne" style="background-color: #FFFFFF;">
        <div class="accordion-inner">
            <c:if test="${muestraCalculada != null}">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Facultad</th>
                    <th>Programa</th>
                    <th>Descripción</th>
                    <th>Población</th>
                    <th>Muestra</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${muestraCalculada.rowsByIndex}" var="item2" varStatus="iter">
                            <tr>
                                <td>${item2[0]}</td>
                                <td>${item2[1]}</td>
                                <td>${item2[2]}</td>
                                <td style="text-align: right">${item2[3]}</td>
                                <c:choose>
                                    <c:when test="${item2[4]==0}">
                                        <td style="text-align: right">-</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="text-align: right">${item2[4]}</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <c:set var="iterador" value="${iterador + item2[4]}" />
                            <c:set var="iterador2" value="${iterador2 + item2[3]}" />
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td><strong>Total</strong></td>
                            <td></td>
                            <td></td>
                            <td style="text-align: right"><strong>${iterador2}</strong></td>
                            <td style="text-align: right"><strong>${iterador}</strong></td>
                        </tr>
                    </tfoot>
                </table>   
            </c:if>
            <c:if test="${muestraCalculada == null}">
                <c:if test="${muestraCalculada3 != null}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Muestra Calculada</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${muestraCalculada3}" var="item2" varStatus="iter">
                                <tr>
                                    <td>${item2.programa}</td>
                                    <c:choose>
                                        <c:when test="${item2.tamanio == '0'}">
                                            <td style="text-align: right">-</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="text-align: right">${item2.tamanio}</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <c:set var="iterador" value="${iterador + item2.tamanio}" />
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td><strong>Total Muestra</strong></td>
                                <td style="text-align: right"><strong>${iterador}</strong></td>
                            </tr>
                        </tfoot>
                    </table>
                </c:if> 
                <c:if test="${muestraIndividual != null}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Muestra Calculada</th>
                        </thead>
                        <tbody>
                            <tr>
                                <c:if test="${muestraIndividual != null}">
                                    <td>${muestraIndividual}</td>
                                </c:if>
                                <c:if test="${muestraIndividual == null}">
                                    <td>No existen datos en la base de datos. Configure el tama&nacute;o de la muestra manualmente.</td>
                                </c:if>
                            </tr>
                        </tbody>
                    </table>
                </c:if> 
            </c:if>
            <c:if test="${muestraCalculada == null && muestraCalculada3 == null && muestraIndividual == null}">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Muestra Calculada</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>No existen datos en la base de datos. Configure tama&nacute;o de la muestra manualmente.</td>
                        </tr>
                    </tbody>
                </table>   
            </c:if>
        </div>
    </div>
</div>
