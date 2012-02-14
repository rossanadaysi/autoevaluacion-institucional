<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar Encuesta</title>
        <link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
     

        <script type="text/javascript" src="jQuery/dragDrop/fcbklistselection.js"></script>

        <script type="text/javascript" language="JavaScript">
            $(document).ready(function() {
                //id(ul id),width,height(element height),row(elements in row)        
                $.fcbkListSelection("#fcbklist","400","50","2");
                
            });
            
            
        </script>
    </head>
    <body>
        <h1>Asignar Encuesta</h1>
        <c:if test="${aux == 1}">

            <form  id="formulario" name="form1" action="<%=request.getContextPath()%>/ControllerAI?action=pAsignarEncuestaAI" method="post">
                <select name="fuente">
                    <c:forEach items="${fuentes.rowsByIndex}" var="item2" varStatus="iter">
                        <option value="${item2[0]}">${item2[1]}</option>
                    </c:forEach>
                </select>
                <br>
                <br>
                <div id="div1">
                    <ul id="fcbklist">
                        <c:forEach items="${encuestas.rowsByIndex}" var="item" varStatus="iter">
                            <li>
                                <strong>${item[1]}</strong><br/> 
                                <span class="fcbkitem_text">${item[2]}</span>
                                <input name="${item[1]}" type="hidden" value="0"/>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <br><br>
                <input type="submit" value="Asignar Encuesta Para Fuente Seleccionada"></input>
            </form>  

        </c:if>
        <c:if test="${aux == 0}">
            <table border="1" cellspacing="2">
                <%-- for every row in the Result ...--%>
                <thead>
                    <tr>
                        <td>Fuente</td>
                        <td>Encuesta</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${asignacionEncuestas.rowsByIndex}" var="row" varStatus="iter">
                        <tr>    
                            <td>   
                                <c:out value="${row[1]}"/>
                            </td>
                            <td>   
                                <c:out value="${row[2]}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="<%=request.getContextPath()%>/ControllerAI?action=detalleProcesoAI">Volver al detalle del proyecto</a>
        </c:if>
    </body>
</html>
