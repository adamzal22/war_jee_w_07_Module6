<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<form:form method="post" modelAttribute="student" action="/student/save">

    First Name: <form:input path="firstName"/><br>
    First Name: <form:input path="lastName"/><br>
    Gender: <form:radiobutton path="gender" value="F"/>F
    <form:radiobutton path="gender" value="M"/>M<br>
    Country: <form:select path="country" items="${countries}"/><br>
    Notes: <form:textarea path="notes" cols="150" rows="25"/><br>
    Mailing: <form:checkbox path="mailingList" /><br>
    Programming: <form:select multiple="true" path="programmingSkills" items="${programmingSkills}"/><br>
    Hobby: <form:checkboxes path="hobbies" items="${hobbies}"/>
    <br>

    <input type="submit" value="Wysyłam"> <br />

</form:form>

