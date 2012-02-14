<%-- 
    Document   : confirmation
    Created on : 24-nov-2011, 14:52:26
    Author     : Usuario
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmación</title>
    </head>
    <body>
        <h1>${mensaje}</h1>
        <a href="<%=request.getContextPath()%>/ControllerAI?action=${action}">Continuar</a>
    </body>
</html>
