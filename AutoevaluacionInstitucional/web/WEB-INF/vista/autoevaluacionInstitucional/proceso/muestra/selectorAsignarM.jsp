<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {      
        $("#select3 option:eq(0)").attr("selected", "selected");
        $("#select4 option:eq(0)").attr("selected", "selected");
        if(${aux_asignarM == 1}){
            $("#resultadoAlert").show();
            if(${idFuenteMuestra == 1}){
                $("#filtro3").hide();
                $("#filtro").show();
            }
            if(${idFuenteMuestra == 2}){
                $("#filtro").hide();
                $("#filtro3").show();
            }
        }else{
            $("#formula").show();
            $("option[rel=popover]")
            .popover()
            .click(function(e) {
                e.preventDefault()      
            })
        }
    });
</script>
