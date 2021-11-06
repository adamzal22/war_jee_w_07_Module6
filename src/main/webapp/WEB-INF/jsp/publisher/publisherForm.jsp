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

<h2>Add new publisher</h2>
<form:form modelAttribute="publisher">
    <form:hidden path="id"/>
    Name: <form:input path="name" /><br>
    <form:errors path="name" cssClass="error"/> <br>

    NIP: <form:input path="nip"/><br>
    <form:errors path="nip" cssClass="error"/> <br>

    REGON: <form:input path="regon"/><br>
    <form:errors path="regon" cssClass="error"/> <br>

    <input type="submit" value="Zapisz">
</form:form>