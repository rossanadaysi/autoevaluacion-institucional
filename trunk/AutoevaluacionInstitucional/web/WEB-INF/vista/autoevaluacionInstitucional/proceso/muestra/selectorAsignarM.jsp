<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {      
        $("#select3 option:eq(0)").attr("selected", "selected");
        $("#select4 option:eq(0)").attr("selected", "selected");
        $("#select8 option:eq(0)").attr("selected", "selected");
        if(${aux_asignarM == 1}){
            if(${aux_IniciarP == 0}){
                $("#resultadoAlert").show();
                if(${idFuenteMuestra == 1}){
                    $("#filtro3").hide();
                    $("#filtro4").hide();
                    $("#filtro").show();
                    
                }
                else if(${idFuenteMuestra == 2}){
                    $("#filtro").hide();
                    $("#filtro4").hide();
                    $("#filtro3").show();
                }
                else{
                    $("#filtro").hide();
                    $("#filtro3").hide();
                    $("#filtro4").show();
                }
            }else{
                $("#resultadoAlert").show();
                if(${idFuenteMuestra == 1}){
                    $("#filtro3").hide();
                    $("#filtro4").hide();
                    $("#filtro").show();
                    
                }
                else if(${idFuenteMuestra == 2}){
                    $("#filtro").hide();
                    $("#filtro4").hide();
                    $("#filtro3").show();
                }
                else{
                    $("#filtro").hide();
                    $("#filtro3").hide();
                    $("#filtro4").show();
                }
                $("#resultadoAlert").hide();
                $("#formula").hide();
                $("#botonNuevaMuestra").hide();
            }
        }else{
            if(${aux_IniciarP == 1}){
                $("#resultadoAlert").hide();
                $("#formula").hide();
                $("#botonNuevaMuestra").hide();
                $("#infoMuestra").show();
            }else{
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
