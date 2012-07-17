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
    <button id="west-closer" class="close">&laquo;</button>
    <c:if test="${aux_index2 == 1}">
        <li class="nav-header">Proceso en configuración</li>
        <li><a href="<%=request.getContextPath()%>/#CrearProceso"><i class="icon-th"></i> Detalle Proceso</a></li>
        <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#PonderacionFactor"><i class="icon-pencil"></i> Ponderacion Factores</a></li>
        <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#PonderacionCaracteristica"><i class="icon-pencil"></i> Ponderacion Caracteristicas</a></li>
        <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-user"></i> Asignar Muestra</a></li>
        <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacion Encuestas</a></li>
        <li class="divider"></li>
        <li><a href="#IniciarProceso"><i class="icon-play"></i> Iniciar Proceso</a></li> 
        <li class="nav-header">Procesos Anteriores</li>
        <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>        
    </c:if>
    <c:if test="${aux_index2 == 2}">
        <li class="nav-header">Proceso en ejecución</li>
        <li class="divider"></li>
        <li class="nav-header">Información del Proceso</li>
        <li><a id="detalle" href="<%=request.getContextPath()%>/#detalleProceso"><i class="icon-th"></i> Detalle Proceso</a></li>
        <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#listarPonderacionFactor"><i class="icon-pencil"></i> Ponderacion Factores</a></li>
        <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#listarPonderacionCaracteristica"><i class="icon-pencil"></i> Ponderacion Caracteristicas</a></li>
        <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Encuestas asignadas</a></li>
        <li class="divider"></li>
        <li class="nav-header">Configuración del Proceso</li>
        <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-user"></i> Muestra Asignada</a></li>
        <li><a  id="infoDocumental"  href="<%=request.getContextPath()%>/#infoDocumental"><i class="icon-list-alt"></i> Información Documental</a></li>
        <li><a  id="infoNumerica"  href="<%=request.getContextPath()%>/#infoNumerica"><i class="icon-list-alt"></i> Información Numérica</a></li>
        <li><a href="#CerrarProceso"><i class="icon-trash"></i> Finalizar Proceso</a></li>
        <li class="divider"></li>
        <li class="nav-header">Estado del proceso</li>
        <li><a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#estadoProceso"><i class="icon-time"></i> Estado del proceso</a></li>                                     
        <li><a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#informe1"><i class="icon-signal"></i> Informe resultado por encuestas</a></li>
        <li><a  id="informeEncuesta2"  href="<%=request.getContextPath()%>/#informeMatriz"><i class="icon-signal"></i> Matriz de calidad por caracteristicas</a></li>
        <li><a  id="informeMatrizFact"  href="<%=request.getContextPath()%>/#informeMatrizFactores"><i class="icon-signal"></i> Matriz de calidad por factores</a></li>
        <li class="divider"></li>
        <li class="nav-header">Procesos Anteriores</li>
        <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>        
    </c:if>
    <c:if test="${aux_index2 == 3}">
        <li class="nav-header">Proceso finzalizado</li>
        <li class="divider"></li>
        <li class="nav-header">Información del proceso</li>
        <li><a id="detalle" href="<%=request.getContextPath()%>/#detalleProceso"><i class="icon-th"></i> Detalle Proceso</a></li>
        <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#listarPonderacionFactor"><i class="icon-pencil"></i> Ponderacion factores</a></li>
        <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#listarPonderacionCaracteristica"><i class="icon-pencil"></i> Ponderacion caracteristicas</a></li>
        <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Encuestas asignadas</a></li>        
        <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-user"></i> Muestra Asignada</a></li>
        <li><a  id="infoDocumental"  href="<%=request.getContextPath()%>/#listarEvaluarDoc"><i class="icon-list-alt"></i> Información Documental</a></li>
        <li><a  id="infoNumerica"  href="<%=request.getContextPath()%>/#listarEvaluarNum"><i class="icon-list-alt"></i> Información Numérica</a></li>
        <li class="divider"></li>
        <li class="nav-header">Resultados del proceso</li>
        <li><a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#estadoProceso"><i class="icon-info-sign"></i> Estadísticas generales</a></li>                                     
        <li><a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#informe1"><i class="icon-signal"></i> Informe resultado por encuestas</a></li>
        <li><a  id="informeEncuesta2"  href="<%=request.getContextPath()%>/#informeMatriz"><i class="icon-signal"></i> Matriz de calidad por caracteristicas</a></li>
        <li><a  id="informeMatrizFact"  href="<%=request.getContextPath()%>/#informeMatrizFactores"><i class="icon-signal"></i> Matriz de calidad por factores</a></li>
        <li class="divider"></li>
        <li class="nav-header">Procesos</li>
        <li><a href="#CrearProceso1"><i class="icon-plus"></i>Proceso Nuevo</a></li>
        <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>
    </c:if>
    <c:if test="${aux_index2 == 0}">
        <li><a href="#CrearProceso1"><i class="icon-plus"></i>Proceso Nuevo</a></li>
    </c:if>
</ul>
