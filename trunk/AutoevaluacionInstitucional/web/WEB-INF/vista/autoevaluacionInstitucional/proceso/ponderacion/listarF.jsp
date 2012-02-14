<%-- 
    Document   : listarF
    Created on : 09-feb-2012, 17:03:30
    Author     : Arturo González
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ponderación Factores Asignada</title>
    </head>
    <body>
        <h1>Ponderación Factores Asignada</h1>
        <table border="1" cellspacing="2">
            <%-- for every row in the Result ...--%>
            <thead>
                <tr>
                    <td></td>
                    <td>Factor</td>
                    <td>Ponderación</td>
                    <td>Justificaciòn</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ponderacionfactor.rowsByIndex}" var="row" varStatus="iter">
                    <tr>    
                        <td>   
                            <c:out value="${row[0]}"/>
                        </td>
                        <td>   
                            <c:out value="${row[1]}"/>
                        </td>
                        <td>
                            <c:out value="${row[2]}"/>
                        </td>
                        <td>
                            <c:out value="${row[3]}"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
