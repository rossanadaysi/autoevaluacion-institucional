<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function() { 
        marcacion = new Date() 
        Hora = marcacion.getHours() 
        Minutos = marcacion.getMinutes() 
        Segundos = marcacion.getSeconds() 
        if (Hora<=9)
            Hora = "0" + Hora
        if (Minutos<=9)
            Minutos = "0" + Minutos
        if (Segundos<=9)
            Segundos = "0" + Segundos
        var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
        var Mes = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
        var Hoy = new Date();
        var Anio = Hoy.getFullYear();
        var Fecha = "Hoy a las " + Hora + ":" + Minutos + ":" + Segundos;
        $("#horax").html("Actualizado: " + Fecha);
    })
</script>      
<p>Estado del proceso: <c:out value="${porceEstadoProceso}"></c:out></p>
<div class="progress progress-success progress-striped active" style="margin-bottom: 0px;">
    <div class="bar" style="width: ${porceEstadoProceso}"></div>
</div>
<div>
    <%--<div  style="margin-left: 5px; font-size: 10px"><span class="label label-info span1" id="spanActualizado" style="margin-left: 0px;">Actualizado</span></div>
    --%><div id="horax" style="font-size: 10px"></div>
</div>
