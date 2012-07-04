<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {       
        $("#select3 option:eq(0)").attr("selected", "selected");
        $("#select4 option:eq(0)").attr("selected", "selected");
        $("#select8 option:eq(0)").attr("selected", "selected");
        if(${aux_asignarM == 1}){
            if(${aux_IniciarP == 0}){
                if ($('#enlace').is (':visible') && $('#formula').is (':visible')){
                    $("#filtro").show();
                }else{
                    $("#resultadoAlert").show();
                    $("#filtro").show();
                }
            }else{
                $("#resultadoAlert").hide();
                $("#formula").hide();
                $("#botonNuevaMuestra").hide();
            }
        }else{
            if(${aux_IniciarP == 1}){
                $("#resultadoAlert").hide();
                $("#formula").hide();
                $("#filtro").hide();
                $("#botonNuevaMuestra").hide();
                $("#infoMuestra").show();
            }else{
                $("#filtro").hide();
                $("#formula").show();
                $("option[rel=popover]")
                .popover()
                .click(function(e) {
                    e.preventDefault()      
                })
            }
        }
    });
</script>
<c:if test="${conglomeradoFiltro == 'programa'}">
    <c:choose>
        <c:when test="${idFuenteMuestra == '1'}">
            <p>Filtros</p>
            <div id="filtro01">
                <table>
                    <tr>
                        <td>
                            <select  class="span3" id="select3" name="programas" onchange="presionSubmitFiltro()">
                                <option value="--">Seleccione Programa</option>
                                <c:forEach items="${descripcionFiltro.rowsByIndex}" var="item2" varStatus="iter">
                                    <option value="${item2[0]}">${item2[1]}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select  id="select4" name="semestres" onchange="presionSubmitFiltro()">
                                <option value="--">Seleccione Semestre</option>
                                <option value="1">Primer Semestre</option>
                                <option value="2">Segundo Semestre</option>
                                <option value="3">Tercero Semestre</option>
                                <option value="4">Cuarto Semestre</option>
                                <option value="5">Quinto Semestre</option>
                                <option value="6">Sexto Semestre</option>
                                <option value="7">Septimo Semestre</option>
                                <option value="8">Octavo Semestre</option>
                                <option value="9">Noveno Semestre</option>
                                <option value="10">Decimo Semestre</option>                                      
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="display: none" id="filtro02">
                <table>
                    <tr>
                        <td>
                            <select  class="span3" id="select5" name="programas2" onchange="presionSubmitFiltro2()">
                                <option value="--">Seleccione Programa</option>
                                <c:forEach items="${descripcionFiltro.rowsByIndex}" var="item2" varStatus="iter">
                                    <option value="${item2[0]}">${item2[1]}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select  id="select6" name="semestres2" onchange="presionSubmitFiltro2()">
                                <option value="--">Seleccione Semestre</option>
                                <option value="1">Primer Semestre</option>
                                <option value="2">Segundo Semestre</option>
                                <option value="3">Tercero Semestre</option>
                                <option value="4">Cuarto Semestre</option>
                                <option value="5">Quinto Semestre</option>
                                <option value="6">Sexto Semestre</option>
                                <option value="7">Septimo Semestre</option>
                                <option value="8">Octavo Semestre</option>
                                <option value="9">Noveno Semestre</option>
                                <option value="10">Decimo Semestre</option>                                      
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="width: 500px;"><p class="help-block">Seleccione un programa para ver la muestra asignada al mismo.</p></div>
            <br>
        </c:when>
        <c:otherwise>
            <p>Filtros</p>
            <table>
                <tr>
                    <td>
                        <select  class="span3" id="select7" name="programas" onchange="presionSubmitFiltro()">
                            <option value="--">Seleccione Programa</option>
                            <c:forEach items="${descripcionFiltro.rowsByIndex}" var="item2" varStatus="iter">
                                <option value="${item2[0]}">${item2[1]}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <div style="width: 500px;"><p class="help-block">Seleccione un programa para ver la muestra asignada al mismo.</p></div>
            <br>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${conglomeradoFiltro == 'ninguno'}">
    <p>Filtros</p>
    <table>
        <tr>
            <td>
                <select  class="span3" id="select8" name="programas" onchange="presionSubmitFiltro()">
                    <option value="--">Seleccione Opción</option>
                    <option value="Todos">Todos</option>
                </select>
            </td>
        </tr>
    </table>
    <div style="width: 500px;"><p class="help-block">Seleccione una opción para ver la muestra asignada.</p></div>
    <br>
</c:if>
<c:if test="${conglomeradoFiltro == 'tipo' || conglomeradoFiltro == 'cargo' || conglomeradoFiltro == 'sectorempresarial'}">
    <p>Filtros</p>
    <table>
        <tr>
            <td>
                <select  class="span3" id="select9" name="programas" onchange="presionSubmitFiltro()">
                    <option value="--">Seleccione Opción</option>
                    <c:forEach items="${descripcionFiltro.rowsByIndex}" var="item2" varStatus="iter">
                        <option value="${item2[1]}">${item2[1]}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <div style="width: 500px;"><p class="help-block">Seleccione una opción para ver la muestra asignada al mismo.</p></div>
    <br>
</c:if>
<c:if test="${conglomeradoFiltro == 'nuevoCriterio'}">
    <p>Filtros</p>
    <table>
        <tr>
            <td>
                <select  class="span3" id="select10" name="programas" onchange="presionSubmitFiltro()">
                    <option value="--">Seleccione Opción</option>
                    <c:forEach items="${descripcionFiltro.rowsByIndex}" var="item2" varStatus="iter">
                        <option value="${item2[0]}">${item2[1]}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <div style="width: 500px;"><p class="help-block">Seleccione una opción para ver la muestra asignada al mismo.</p></div>
    <br>
</c:if>

