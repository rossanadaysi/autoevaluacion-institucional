<%-- 
    Document   : listar
    Created on : 24-nov-2011, 14:18:45
    Author     : Usuario
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Representantes</title>
    </head>
    <body>
        <h1>Representantes</h1>
        <c:choose>
            <c:when test="${listrepresentantes.size() != 0}">
                <table border="1" cellspacing="2">
                    <%-- for every row in the Result ...--%>
                    <thead>
                        <tr>
                            <td>Cedula</td>
                            <td>Nombres</td>
                            <td>Apellidos</td>
                            <td>Contraseña</td>
                            <td>Correo Electronico</td>
                            <td>Programa</td>
                            <td>Rol</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listrepresentantes}" var="row" varStatus="iter">
                            <tr>    
                                <td>   
                                    <c:out value="${row.personaId.id}"/>
                                </td>
                                <td>
                                    <c:out value="${row.personaId.nombre}"/>
                                </td>
                                <td>
                                    <c:out value="${row.personaId.apellido}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.personaId.password}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.personaId.mail}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.programaId.nombre}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.rol}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                No Existen Representates Registrados en el Sistema.
            </c:otherwise>
        </c:choose>
        <a href="<%=request.getContextPath()%>/Controller?action=indexCC">Volver al menu Comite Central</a>
    </body>
</html>
