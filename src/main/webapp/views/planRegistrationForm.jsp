<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Plan Registration</h1>
	<table>

<form:form action="regPlan" method="POST" modelAttribute="plan">
			<tr>
				<td>Plan Name::</td>
				<td><form:input path="planName" /></td>
			</tr>
		<tr>
			<td>Plan Description::</td>
			<td><form:input path="planDesc" /></td>
		</tr>
		<tr>
			<td>Plan Start Date::</td>
			<td><form:input type="date" path="startDate" /></td>
		</tr>
		<tr>
			<td>Plan End Date::</td>
			<td><form:input type="date" path="endDate" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Register" /></td>
			<td><input type="reset" value="Cancel" /></td>
		</tr>
</form:form>
	</table>
	<br><a href="./viewAllPlans">ViewPlans</a>
	<h2>${resMsg}</h2>
</body>
</html>