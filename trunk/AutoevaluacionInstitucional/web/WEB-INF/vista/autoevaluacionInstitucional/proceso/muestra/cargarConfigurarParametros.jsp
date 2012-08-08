<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<script type="text/javascript">
    $(function(){
        $("#checkbox1").change(function(){
            if( $(this).is(':checked')){
                $("#inputAux").attr('value', 'manual');
                $("#manual").show();
                $("#help1").hide();
            }else{
                $("#inputAux").attr('value', 'automatic');
                $("#manual").hide();
                $("#help1").show();
            }  
        });
    
        $("#formAsigMue2").validate({
            submitHandler: function(){
                $('#myModalConfigurarMuestra').modal('hide');
            }
        }); 
        
        $("#AceptarConf").click(function(event){
            var aux = false;
            var mayor=true;
            event.stopPropagation();
            $(".conf").each(function(){
                if($(this).val()!="" && !$(this).next().is('label:visible')){
                    aux=true;
                }else{
                    if($(this).hasClass("requerido")){
                        mayor=false;
                    }
                    
                }
            })
            if(aux && mayor){
                $("#CancelarConf").click();
            }
        });
    }); 
</script>
<form  id="formAsigMue2" method="post">
    <fieldset>
        <div class="modal-header">
            <a data-dismiss="modal" class="close">×</a>
            <h3>Configurar parámetros de la fórmula</h3>
        </div>
        <div id="validaConf" class="modal-body">
            <input type="checkbox" id="checkbox1"> Configurar tamaño de población de forma manual.<br>
            <p id="help1" class="help-block">Por defecto el sistema obtiene el tamaño de la población de la base de datos.</p>
            <input type="text" id="inputAux" name="inputAux"  style="display: none"  value="automatic">
            <div id="manual" style="display: none">
                <c:if test="${conglomerado == 'programa'}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Programa</th>
                        <th>Tamaño de población</th>
                        <p class="help-block">Si deja en blanco el tamaño de población para algún programa, éste no será tomado en cuenta para el calculo de la muestra.</p>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProgramasMuestra.rowsByIndex}" var="item" varStatus="iter">
                                <tr>
                            <div class="control-group">
                                <td> <label class="control-label">${item[1]}</label></td>
                                <td> <div class="controls">
                                        <input type="text" class="input-mini {number:true} conf" name="tamanioPobla${item[0]}">
                                    </div>
                                </td>
                            </div>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${conglomerado == 'nuevoCriterio'}">
                    <br>
                    <p>Criterio de Conglomerado: ${listNombreCriterio}</p>
                    
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Elemento</th>
                        <th>Tamaño de población</th>
                        <p class="help-block">Si deja en blanco el tamaño de población para algún elemento, éste no será tomado en cuenta para el calculo de la muestra.</p>
                        </thead>
                        <tbody>
                            <c:forEach items="${listDescripcionCriterio2.rowsByIndex}" var="item" varStatus="iter">
                                <tr>
                            <div class="control-group">
                                <td> <label class="control-label" >${item[1]}</label></td>
                                <td> <div class="controls">
                                        <input type="text" class="input-mini conf requerido {required:true, number:true}" name="tamanioPoblaCriterio${item[0]}">
                                    </div>
                                </td>
                            </div>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    
                </c:if>
                <c:if test="${conglomerado == 'ninguno'}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th></th>
                        <th>Tamaño de población</th>
                        <p class="help-block">Si deja en blanco el tamaño de población para algún programa, éste no será tomado en cuenta para el calculo de la muestra.</p>
                        </thead>
                        <tbody>
                            <tr>
                        <div class="control-group">
                            <td> <label class="control-label" for="input01">Población Total</label></td>
                            <td> <div class="controls">
                                    <input type="text" class="input-mini {required:true, number:true} conf"  name="tamanioPobla">
                                </div>
                            </td>
                        </div>
                        </tr>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${conglomerado == 'cargo' || conglomerado == 'sectorempresarial' || conglomerado == 'tipo'}">
                    <p>No existe información en la base de datos. Deber crear un nuevo criterio de conglomerado para asignar el tamaño de la población de forma manual.</p>
                </c:if>
            </div>
        </div>
        <div class="modal-footer">
            <a class="btn btn-secundary" data-dismiss="modal"  id="CancelarConf" href="#">Cerrar</a>
            <a class="btn btn-primary" data-dismiss="modal" id="AceptarConf" href="#">Aceptar</a>
        </div>
    </fieldset>
</form>
