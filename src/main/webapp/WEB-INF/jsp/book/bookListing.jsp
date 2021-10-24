<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:forEach items="${books}" var="b">
    <strong><c:out value="${b.title}: ${b.publisher.name}"/></strong><br>
    <c:out value="${b.description}"/>
    <hr>
</c:forEach>

<a href="/book/form/show">Add new book</a>