<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autoevaluación Institucional</title>
    </head>
    <body>
        <h1>Programa ${programa.nombre}</h1>
        Menú
        <ul>
            <c:choose>
                <c:when test="${aux == 1}">
                    <li>
                        <a href="<%=request.getContextPath()%>/ControllerAI?action=detalleProcesoAI">Ver Proceso de Autoevaluación en Ejecución</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="<%=request.getContextPath()%>/ControllerAI?action=crearProcesoAI">Crear Proceso de Autoevaluación</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul> 
    </body>
</html>
