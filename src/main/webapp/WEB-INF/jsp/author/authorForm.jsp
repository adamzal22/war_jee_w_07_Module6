<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <style>
        .error {
            color: red;
            border: 2px solid;
        }
    </style>
</head>

<h2>Add new author</h2>
<form:form modelAttribute="author">
    <form:hidden path="id"/>
    First name: <form:input path="firstName"/><br>
    <form:errors path="firstName" cssClass="error"/> <br>

    Last name: <form:input path="lastName"/><br>
    <form:errors path="lastName" cssClass="error"/> <br>

    PESEL: <form:input path="pesel"/> <br>
    <form:errors path="pesel" cssClass="error"/> <br>

    Email: <form:input path="email"/> <br>
    <form:errors path="email" cssClass="error"/> <br>

    <input type="submit" value="Zapisz">
</form:form>