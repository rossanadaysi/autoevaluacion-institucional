<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${aux_IniciarP == 1}">
        0
    </c:when>
    <c:otherwise>
        1
    </c:otherwise>
</c:choose>
