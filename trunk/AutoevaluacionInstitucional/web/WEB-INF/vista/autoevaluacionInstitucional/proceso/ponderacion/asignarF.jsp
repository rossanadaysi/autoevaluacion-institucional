<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${auxAsignarF == 1}">
       <h1>Ponderación de Factores</h1>
    <form method="post" id="formPondeFa">
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
                <c:forEach items="${factores.rowsByIndex}" var="row" varStatus="iter">
                    <tr>    
                        <td>   
                            <c:out value="${row[0]}"/>
                        </td>
                        <td>   
                            <c:out value="${row[1]}"/>
                        </td>
                        <td>
                            <select name="ponderacion${row[0]}">
                                <option value="NULL">--</option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                            <input type="hidden"  value="${row[0]}" name="id${row[0]}"></input><br>
                        </td>
                        <td>
                            <textarea name="justificacion${row[0]}" rows="2" cols="20"></textarea>
                        </td>
                    </tr>
                    <c:set var="iterador" value="${iter.index + 1}" />
                </c:forEach>
            </tbody>
            <input type="hidden" name="count" id="count" value="${iterador}"></input>
        </table>
        <input type="submit" value="Asignar Ponderación"></input>
    </form>
</c:if>
<c:if test="${auxAsignarF == 0}">
    la ponderacion ya fue asignada
</c:if>
