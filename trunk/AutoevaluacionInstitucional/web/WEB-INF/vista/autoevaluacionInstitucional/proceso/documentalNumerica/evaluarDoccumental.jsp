<%-- 
    Document   : evaluarDoccumental
    Created on : 03-may-2012, 0:13:07
    Author     : Arturo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Códdigo</th>
                    <th>Indicador</th>
                    <th>Ponderacion</th>
                    <th>Justificacion</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${factores.rowsByIndex}" var="row" varStatus="iter">
                <tr id="PonderacionFactores${iter.index+1}">    
                    <td>   
                <c:out value="${row[0]}"/>
                </td>
                <td>   
                <c:out value="${row[1]}"/>
                </td>
                <td>
                    <input name="ponderacion${row[0]}" class="span1 {required:true,number:true}" type="text">
                    <div class='alert alert-error fade' style="display: none">
                        <a data-dismiss='alert' class='close'>×</a>  
                        <strong>Error!</strong>
                    </div>
                    <input type="hidden"  value="${row[0]}" name="id${row[0]}">
                </td>
                <td>
                    <textarea name="justificacion${row[0]}" rows="4" class="span5 {required:true}"></textarea>
                </td>
                </tr>
                <c:set var="iterador" value="${iter.index + 1}" />
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
