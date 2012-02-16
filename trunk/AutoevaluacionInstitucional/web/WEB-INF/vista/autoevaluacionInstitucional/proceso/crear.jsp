<%-- 
    Document   : newProceso
    Created on : 23-sep-2011, 9:51:55
    Author     : Usuario
--%>

<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Proceso</title>
    </head>
    <body>
        <h2>Nuevo Proceso de Autoevaluaci&oacute;n</h2>
        <form  id="formCrearProc" method="post">
            <table>
                <tr>
                    <td>Programa: </td>
                    <td>${programa.nombre}</td>
                </tr>
                <tr>
                    <td>Fecha Inicio: </td>
                    <td>Proceso en Configuraci&oacute;n.</td>
                </tr>
                <tr>
                    <td>Descripci&oacute;n: </td>
                    <td><textarea name="descripcion" rows="2" cols="20"></textarea></td>
                </tr>
                <tr>    
                    <td><input type="submit" value="Crear Proceso"></input></td>
                </tr>
            </table>
        </form>
    </body>
</html>
