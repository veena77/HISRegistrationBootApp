<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function validatePwds(){
	var newPwd=$("#newPwd").val();
	var confirmPwd=$("#confirmPwd").val();
	if(newPwd!=confirmPwd){
		$("#errId").text("new Password and confirm password should be same");
		return false;
	}
	return true;
}
</script>
</head>
<body bgcolor="skyblue">
<table>

<h2 style="color: navy;text-align: center;">Unlock Account Here</h2>
<font color="red">${errMsg }</font>
<form:form  action="unlockUserAcc" modelAttribute="unlockAcc" method="POST" > 
<font color="red"><span id="errId"></span></font>
<tr><td>Email Id::</td>
 <%-- <td>${email}</td> --%> 
<td><form:input path="email" readonly="true"  disabled="true"/></td>
</tr>
<tr><td>Enter Temp Password</td>
	<td><form:password path="tempPwd"/></td>
	</tr>
	<tr><td>Enter New Password</td>
	<td><form:password path="newPwd"/></td>
	</tr>
	<tr><td>Confirm Password</td>
			<td><form:password path="confirmPwd"/></td>
			</tr>
<tr><td><input type="submit"  value="UNLOCK" onclick="return validatePwds()"></td></tr>
</form:form>
</table>
</body>
</html>