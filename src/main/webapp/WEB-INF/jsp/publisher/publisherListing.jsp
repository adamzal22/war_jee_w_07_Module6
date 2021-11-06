<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<a href="/">Main menu</a> <br>
<a href="add">Add new publisher</a>
<hr>

<%@include file="../common/search.jsp"%>

<hr>
<c:forEach items="${publishers}" var="pub">
    <strong><c:out value="${pub.name}"/></strong><br>
    <br />
    <a href="edit?idToEdit=${pub.id}">
        Edytuj
    </a>
    </br />
    <a href="remove?toRemoveId=${pub.id}">
        Usun
    </a>
    <hr>
</c:forEach>