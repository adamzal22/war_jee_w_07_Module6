<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<form:form method="post" modelAttribute="person">

    Login: <form:input path="login"/> <br />
    Password: <form:password path="password"/> <br />
    Email: <form:input type="email" path="email"/> <br />

    <input type="submit" value="Wysyłam"> <br />

</form:form>

<%-- FORMULARZ DO OBSLUGI PRZEZ @RequestParam --%>
<%--
<form method="post">

    Login: <input type="text" name="login"> <br />
    Password: <input type="password" name="password"> <br />
    Email: <input type="email" name="email"> <br />

<c:out value="${student.firstName} ${student.lastName}"/>
