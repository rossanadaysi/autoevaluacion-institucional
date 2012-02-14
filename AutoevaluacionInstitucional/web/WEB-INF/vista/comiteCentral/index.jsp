<%-- 
    Document   : indexComiteCentral
    Created on : 24-nov-2011, 16:19:47
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comite Central</title>
    </head>
    <body>
        <h1>Bienvenido!</h1>
        Menú
        <ul>
            <li>
                Representantes
                <ul>
                    <li>
                        <a href="<%=request.getContextPath()%>/Controller?action=crearRepresentante">Crear Representante</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/Controller?action=listarRepresentante">Listar Representante</a>
                    </li>
                </ul>
            </li>
        </ul> 
    </body>
</html>
