<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" language="JavaScript">
    

    $(document).ready(function() {
        $("ul.nav-list li a").click(function(event){
            console.log("clickl");
            $(".nav li").removeClass("active");
            $(this).parent().siblings().removeClass("active");
            $(this).parent().siblings().children("a").children("i").removeClass("icon-white");
            $(this).parent().addClass("active");
            $(this).children("i").addClass("icon-white");
            location = $(this).attr("href");
        })
    });
 
           
</script>
<ul class="nav nav-list">  
    <c:choose>
        <c:when test="${aux_index2 == 1}">

            <c:choose>
                <c:when test="${aux2_index2 == 1}">
                    <li class="nav-header">Proceso en configuración</li>
                    <li><a href="<%=request.getContextPath()%>/#CrearProceso"><i class="icon-th"></i> Detalle Proceso</a></li>
                    <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#PonderacionFactor"><i class="icon-tag"></i> Ponderacion Factores</a></li>
                    <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#PonderacionCaracteristica"><i class="icon-tags"></i> Ponderacion Caracteristicas</a></li>
                    <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-glass"></i> Asignar Muestra</a></li>
                    <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacion Encuestas</a></li>
                    <li class="divider"></li>
                    <li><a href="#IniciarProceso"><i class="icon-play"></i> Iniciar Proceso</a></li> 
                </c:when>
                <c:otherwise>
                    <li class="nav-header">Proceso en ejecución</li>
                    <li class="divider"></li>
                    <li class="nav-header">Información del Proceso</li>
                    <li><a id="detalle" href="<%=request.getContextPath()%>/#detalleProceso"><i class="icon-th"></i> Detalle Proceso</a></li>
                    <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#listarPonderacionFactor"><i class="icon-tag"></i> Ponderacion Factores</a></li>
                    <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#listarPonderacionCaracteristica"><i class="icon-tags"></i> Ponderacion Caracteristicas</a></li>
                    <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacion Encuestas</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Configuración del Proceso</li>
                    <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-glass"></i> Asignar Muestra</a></li>
                    <li><a  id="infoDocumental"  href="<%=request.getContextPath()%>/#infoDocumental"><i class="icon-glass"></i> Información Documental</a></li>
                    <li><a  id="infoNumerica"  href="<%=request.getContextPath()%>/#infoNumerica"><i class="icon-glass"></i> Información Numérica</a></li>
                    <li><a href="#CerrarProceso"><i class="icon-trash"></i> Finalizar Proceso</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Estado del Proceso</li>
                    <li><a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#informe1"><i class="icon-signal"></i> Informe Encuestas</a></li>
                    <li><a  id="informeEncuesta2"  href="<%=request.getContextPath()%>/#informe2"><i class="icon-signal"></i> Informe Encuestas 2</a></li>
     
                    </c:otherwise>
                </c:choose>
            <li class="nav-header">Procesos Anteriores</li>
            <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>        
        </c:when>
        <c:otherwise>
            <li class="nav-header">Procesos</li>
            <li><a href="#CrearProceso1"><i class="icon-plus"></i>Proceso Nuevo</a></li>
            <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>
        </c:otherwise>
    </c:choose>
</ul>