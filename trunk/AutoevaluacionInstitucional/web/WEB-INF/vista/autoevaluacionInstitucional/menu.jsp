<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" language="JavaScript">
    

    $(document).ready(function() {
        
       
  
    });
 
           
</script>
<ul class="nav nav-list">  
    <c:choose>
        <c:when test="${aux_index2 == 1}">
            <li class="nav-header">Proceso en Ejecución</li>
            <c:choose>
                <c:when test="${aux2_index2 == 1}">
                    <li><a href="<%=request.getContextPath()%>/#CrearProceso"><i class="icon-white icon-th"></i> Detalle Proceso</a></li>
                    <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#PonderacionFactor"><i class="icon-tag"></i> Ponderacion Factores</a></li>
                    <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#PonderacionCaracteristica"><i class="icon-tags"></i> Ponderacion Caracteristicas</a></li>
                    <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-glass"></i> Asignar Muestra</a></li>
                    <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacion Encuestas</a></li>
                    <li><a href="#IniciarProceso"><i class="icon-play"></i> Iniciar Proceso</a></li> 
                </c:when>
                <c:otherwise>
                    <li><a id="detalle" href="<%=request.getContextPath()%>/#detalleProceso"><i class="icon-white icon-th"></i> Detalle Proceso</a></li>
                    <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#listarPonderacionFactor"><i class="icon-tag"></i> Ponderacion Factores</a></li>
                    <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#listarPonderacionCaracteristica"><i class="icon-tags"></i> Ponderacion Caracteristicas</a></li>
                    <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#AsignacionMuestra"><i class="icon-glass"></i> Asignar Muestra</a></li>
                    <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacion Encuestas</a></li>
                    <li><a href="#CerrarProceso"><i class="icon-trash"></i> Finalizar Proceso</a></li>
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