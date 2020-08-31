<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/app.js"></script>
<script src="vendor/jquery/dist/jquery.min.js"></script><!-- Client Side Validations  -->
<script src="vendor/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="js/form-validation.js"></script>
</head>

<body>
<h1 style="text-align:center">Register here</h1>
<table>

<form:form  name="registration" action="userAccReg" method="POST" modelAttribute="user" >
<tr><td>First Name::</td>
<td><form:input path="firstName" id="firstName" name="firstName"/></td></tr>

<tr><td>Last Name::</td>
<td> <form:input path="lastName" id="lastName" name="lastName"/></td></tr>

<tr><td>EmailId::</td>
<td><form:input path="emailId" id="emailId" name="emailId"/></td></tr>


<tr><td>Phone No::</td>
<td><form:input path="phoneNo" id="phoneNo" name="phoneNo"/></td></tr>

<tr>
<td>Gender</td>
<td>
<form:radiobutton path="Gender" value="Male" id="gender"/> Male
<form:radiobutton path="Gender" value="Female" id="gender"/> Female
</td></tr>
<tr>
<td>Role::</td>
<td><form:select path="roleName">
<form:option value="">-Select-</form:option>
<form:options items="${role}"/>
</form:select></td></tr>

<tr>
<td><input type="reset" value="Reset"/><input type="submit" value="Save"/></td>
</tr>

</form:form>
</table>

<br><br><a href="/his/viewAccounts">View All Users</a>
</body>
</html>