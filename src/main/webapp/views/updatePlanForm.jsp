<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">Update Plan</h1>
<form:form>
<table>
<tr>
<td>PlanId::</td>
<td><form:input path="planId"/>
</td>
</tr>

<tr>
<td>PlanName::</td>
<td><form:input path="planName"/>
</td>
</tr>

<tr>
<td>Plan Description::
</td>
<td><form:input path="planDesc"/>
</td>
</tr>

<tr>
<td>Plan Start Date::
</td>
<td><form:input path="startDate"/>
</td>
</tr>

<tr>
<td>Plan End Date::
</td>
<td><form:input path="endDate"/>
</td>
</tr>
</table>
</form:form>

</body>
</html>