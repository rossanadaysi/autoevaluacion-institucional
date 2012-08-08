<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {  
        $("#filtro").show();
        
        $("#title").append(" " + $("select[name='programas'] :selected").text());
   
         
    });
       
    $("#printEnlace").click( function() {
        $('#printMuestra').jqprint();
        return false;
    });
        
    $("#botonEditarMuestra").click(function(){
        var a = $("#select3 option:selected").index();
        var b = $("#select4 option:selected").index();
        $("#select5 option:eq("+a+")").attr("selected", "selected");
        $("#select6 option:eq("+b+")").attr("selected", "selected");
        $("#filtro01").hide();
        $("#filtro02").show();
               
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/formController?action=selectorAsignarMuestra3AI",
            data: $("#formAsigMue").serialize(),
            success: function(){
                $.ajax({
                    type: 'POST',
                    url: "<%=request.getContextPath()%>/ControllerAI?action=selectorAsignarMuestra3AI",
                    
                    success: function(data){
                        $("#resultados4").hide();
                        $("#resultados4").html(data);
                        setTimeout(function(){
                            $(".page_loading").hide();
                            $("#resultados4").show(); 
                        },200);  
                    }
                })
            } //fin success                    
        }); //fin $.ajax
    }) 
</script>
<c:choose>
    <c:when test="${selectorAsignarM2 == null}">
        <p class="help-block">Debe Seleccionar un filtro para listar la muestra asignada.</p>
    </c:when>
    <c:otherwise>
        <c:if test="${selectorAsignarM2.getRowCount() == 0}">
            <p>No existe información en la base de datos para generar la muestra requerida. Pruebe generando usuarios aleatorios.</p>
        </c:if>
        <c:if test="${selectorAsignarM2.getRowCount() != 0}">
            <c:if test="${aux_IniciarP == 1 || aux_IniciarP == 2}">
                <c:if test="${idFuenteMuestra == 1}">
                    <div id="tablax" style="z-index: 1;">
                        <c:if test="${aux_IniciarP == 1}">
                            <button class="btn btn-primary" id="botonEditarMuestra"  type="button">Editar Muestra Asignada</button>
                            <br>
                        </c:if>
                        <a class="span10" style="text-align: right" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
                        <div id="printMuestra">
                            <br>
                            <h4 id="title">Muestra generada para la fuente <c:out value="${nombreFuenteMuestra}"></c:out>.</h4>
                            <br>
                            <div>
                                <span class="label label-success" style="background-color: #F2DEDE;
                                      border-color: #EED3D7;
                                      color: #B94A48;">Pendiente</span>
                                <span class="label label-success" style="background-color: #DFF0D8;
                                      border-color: #D6E9C6;
                                      color: #468847;">Terminado</span>
                                <span class="label label-success" style="background-color: #D9EDF7;
                                      border-color: #BCE8F1;
                                      color: #3A87AD; margin-bottom: 5px">Guardado</span>
                            </div>
                            <br>
                            <table class="table table-striped table-bordered table-condensed">
                                <thead>
                                <th>Código</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>Password</th>
                                <th>Semestre</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter2">
                                        <c:set var="varaux" value="0"/>
                                        <c:forEach items="${selectorAsignarM22.rowsByIndex}" var="item2" varStatus="iter">
                                            <c:if test="${item[0] == item2[0]}">
                                                <c:set var="varaux" value="1"/>
                                                <c:if test="${item2[1] == 'terminado'}">
                                                    <tr>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[0]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[1]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[2]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[3]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[4]}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${item2[1] == 'guardada'}">
                                                    <tr>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[0]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[1]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[2]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[3]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[4]}</td>
                                                    </tr>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${varaux == 0}">
                                            <tr>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[0]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[1]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[2]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[3]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[4]}</td>
                                            </tr>
                                        </c:if>
                                        <c:set var="iterador" value="${iter2.index + 1}" />
                                    </c:forEach>
                                </tbody>
                            </table>
                            <p><strong>Total Estudiantes: ${iterador}</strong></p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${idFuenteMuestra != 1}">
                    <a class="span10" style="text-align: right" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
                    <div id="printMuestra">
                        <br>
                        <h4 id="title">Muestra generada para la fuente <c:out value="${nombreFuenteMuestra}"></c:out>.</h4>
                        <br>
                        <div id="tablax" style="z-index: 1;">
                            <div>
                                <span class="label label-success" style="background-color: #F2DEDE;
                                      border-color: #EED3D7;
                                      color: #B94A48;">Pendiente</span>
                                <span class="label label-success" style="background-color: #DFF0D8;
                                      border-color: #D6E9C6;
                                      color: #468847;">Terminado</span>
                                <span class="label label-success" style="background-color: #D9EDF7;
                                      border-color: #BCE8F1;
                                      color: #3A87AD; margin-bottom: 5px">Guardado</span>
                            </div>
                            <br>
                            <table class="table table-striped table-bordered table-condensed">
                                <thead>
                                <th>Código</th>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>Password</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                                        <c:set var="varaux" value="0"/>

                                        <c:forEach items="${selectorAsignarM22.rowsByIndex}" var="item2" varStatus="iter2">
                                            <c:if test="${item[0] == item2[0]}">
                                                <c:set var="varaux" value="1"/>
                                                <c:if test="${item2[1] == 'terminado'}">
                                                    <tr>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[0]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[1]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[2]}</td>
                                                        <td style="background-color: #DFF0D8; color: #468847;">${item[3]}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${item2[1] == 'guardada'}">
                                                    <tr>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[0]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[1]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[2]}</td>
                                                        <td style="background-color: #D9EDF7; color: #3A87AD;">${item[3]}</td>
                                                    </tr>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${varaux == 0}">
                                            <tr>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[0]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[1]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[2]}</td>
                                                <td style="background-color: #F2DEDE; color: #B94A48;">${item[3]}</td>
                                            </tr>
                                        </c:if>
                                        <c:set var="iterador" value="${iter.index + 1}" />
                                    </c:forEach>
                                </tbody>
                            </table>
                            <p><strong>Total: ${iterador}</strong></p>
                        </div>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${aux_IniciarP == 0}">
                <c:if test="${idFuenteMuestra == 1}">
                    <div id="tablax" style="z-index: 1;">
                        <button class="btn btn-primary" id="botonEditarMuestra"  type="button">Editar Muestra Asignada</button>
                        <br>
                        <table class="table table-striped table-bordered table-condensed">
                            <thead>
                            <th>Código</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Password</th>
                            <th>Semestre</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                                    <tr>
                                        <td>${item[0]}</td>
                                        <td>${item[1]}</td>
                                        <td>${item[2]}</td>
                                        <td>${item[3]}</td>
                                        <td>${item[4]}</td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <p><strong>Total Estudiantes: ${iterador}</strong></p>
                    </div>
                </c:if>
                <c:if test="${idFuenteMuestra != 1}">
                    <div id="tablax" style="z-index: 1;">
                        <table class="table table-striped table-bordered table-condensed">
                            <thead>
                            <th>Código</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Password</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${selectorAsignarM2.rowsByIndex}" var="item" varStatus="iter">
                                    <tr>
                                        <td>${item[0]}</td>
                                        <td>${item[1]}</td>
                                        <td>${item[2]}</td>
                                        <td>${item[3]}</td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <p><strong>Total: ${iterador}</strong></p>
                    </div>
                </c:if>
            </c:if>
        </c:if>
    </c:otherwise>
</c:choose>

