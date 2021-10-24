<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add new book</h2>
<form:form modelAttribute="book" action="/book/form/save">
    Title: <form:input path="title"/><br>
    Description: <form:textarea path="description" cols="50" rows="15"/><br>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/><br>
    <input type="submit" value="ADD BOOK">
</form:form>