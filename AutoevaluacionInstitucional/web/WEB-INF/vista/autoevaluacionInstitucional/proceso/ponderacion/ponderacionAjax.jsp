<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${ListPondyIdc}" var="PondyIdc" >
    <input name="ponderacion2${PondyIdc[1]}" type="text" class="input-mini uneditable-input" value="${PondyIdc[0]}"/>
</c:forEach>

