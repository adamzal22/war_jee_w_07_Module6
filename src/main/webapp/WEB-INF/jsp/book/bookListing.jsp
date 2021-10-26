<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<a href="add">Add new book</a>
<hr>
<c:forEach items="${books}" var="b">
    <strong><c:out value="${b.title}: ${b.publisher.name}"/></strong><br>
    <c:out value="${b.description}"/>
    <c:forEach items="${b.authors}" var="a" varStatus="loop">
        <strong><c:out value="${a.firstName} ${a.lastName}"/></strong><c:if test="${loop.index + 1 lt b.authors.size()}">; </c:if>
    </c:forEach>
    <br />
    <a href="edit?idToEdit=${b.id}">
        Edytuj
    </a>
    </br />
    <a href="remove?toRemoveId=${b.id}">
        Usun
    </a>
    <hr>
</c:forEach>