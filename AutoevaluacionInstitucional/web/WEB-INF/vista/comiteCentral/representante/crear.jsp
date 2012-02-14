
<%-- 
    Document   : crear
    Created on : 17-nov-2011, 11:42:49
    Author     : Usuario
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Representante</title>

        <link type="text/css" rel="stylesheet" href="jQuery/dragDrop/fcbklistselection.css" />
        <script type="text/javascript" src="jQuery/jquery.js"></script>

        <script type="text/javascript" src="jQuery/dragDrop/fcbklistselection.js"></script>

        <script type="text/javascript" language="JavaScript">
            $(document).ready(function() {
                //id(ul id),width,height(element height),row(elements in row)        
                $.fcbkListSelection("#fcbklist","400","50","2");
                if(document.form1.rol.options [document.form1.rol.selectedIndex].value == 'otros'){
                    elDiv.style.display='block';//damos un atributo display:block que  el div    
                }
            });
            
            function oculta(){
                var elDiv = document.getElementById('div1'); //se define la variable "elDiv" igual a nuestro div
                if(document.form1.rol.options [document.form1.rol.selectedIndex].value == 'otros'){
                    elDiv.style.display='block';//damos un atributo display:block que  el div    
                }
                else{
                    elDiv.style.display='none';//damos un atributo display:block que  el div                         
                }
            }
        </script>
    </head>
    <body>
        <h1>Nuevo Representante</h1>
        <form  id="formulario" name="form1" action="<%=request.getContextPath()%>/Controller?action=pCrearRepresentante" method="post">
            <table>
                <tr>
                    <td>Cedula: </td>
                    <td><input type="text" name="cedula"></input></td>
                </tr>
                <tr>
                    <td>Nombres: </td>
                    <td><input type="text" name="nombres"></input></td>
                </tr>
                <tr>
                    <td>Apellidos: </td>
                    <td><input type="text" name="apellidos"></input></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password"></input></td>
                </tr>
                <tr>
                    <td>Correo Electronico: </td>
                    <td><input type="text" name="mail"></input></td>
                </tr>
                <tr>
                    <td>Programa: </td>
                    <td><select name="programas">
                            <c:forEach items="${programas}" var="item" varStatus="iter">
                                <option value="${item.id}">${item.nombre}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td>Rol: </td>
                    <td> <select  onchange="oculta()" name="rol">
                            <option value="Autoevaluacion Institucional">Autoevaluacion Institucional</option>
                            <option value="Comite Central">Comite Central</option>
                            <option value="Otros">Otros</option>
                        </select></td>
                </tr>
            </table>
            </br>
            <div id="div1">
                <ul id="fcbklist">
                    <c:forEach items="${privilegios}" var="item" varStatus="iter">
                        <li>
                            <strong>${item.nombre}</strong><br/> 
                            <span class="fcbkitem_text">${item.descripcion}</span>
                            <input name="${item.nombre}" type="hidden" value="0"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <input type="submit" value="Crear Representante"></input>
        </form>
    </body>
</html>
