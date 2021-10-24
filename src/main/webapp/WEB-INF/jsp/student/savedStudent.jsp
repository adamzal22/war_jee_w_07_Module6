<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:out value="${student.firstName} ${student.lastName} (${student.gender})"/><br>
<c:out value="${student.country}"/><br>
<c:out value="${student.notes}"/><br>
<c:out value="mailing: ${student.mailingList}"/><br>
<c:out value="programming:"/>
<c:forEach items="${student.programmingSkills}" var="p">
    <c:out value="${p} "/>
</c:forEach><br>
Hobbies:
<c:forEach items="${student.hobbies}" var="h">
    <c:out value="${h} "/>
</c:forEach>