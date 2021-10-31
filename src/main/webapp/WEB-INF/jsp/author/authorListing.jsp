<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<a href="add">Add new author</a>
<hr>
<c:forEach items="${authors}" var="auth">
    <strong><c:out value="${auth.fullName}"/></strong><br>
    <br />
    <a href="edit?idToEdit=${auth.id}">
        Edytuj
    </a>
    </br />
    <a href="remove?toRemoveId=${auth.id}">
        Usun
    </a>
    <hr>
</c:forEach>