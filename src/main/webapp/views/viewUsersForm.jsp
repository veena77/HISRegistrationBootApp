<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script>

 
/* $(document).ready(function() {
    $('#userTable').DataTable({
    	"pagingType":"full_numbers";
    });   
} ); */ 

$(document).ready(function() {
	 $('#roleId').on('change', function() {
            this.form.submit();
        });
		
});

 function confirmDelete(){
	return confirm("Do you want to delete?");
}
function confirmActivate(){
	return confirm("Do you want to Activate?");
} 
</script>
</head>
<body>
<h1 style="color:blue" align="center">View All Users</h1>
<%-- <h2 style="color:navy;">${resMsg}</h2> --%>
<br><br>
<form action="viewAccounts">
<table>
	<tr>
	<td>Select Role::</td>
	<td><select name="role" id="roleId">
	<option value="">-select-</option>
	<option value="CASE WORKER">CASE WORKER</option>
	<option value="ADMIN">ADMIN</option>
	</select></td>
	</tr>
</table>
</form>

<br><br><br>

	<!-- <table border="1" align="center"> -->
	<table id="userTable" class="display" style="width:100%" border="1">
			<tr>
				<th>SSN</th>
				<th>FIRST NAME</th>
				<th>LAST NAME</th>
				<th>EMAIL ID</th>
				<th>ROLE</th>
				<th>ACTION</th>
			</tr>
		<c:forEach items="${list}" var="user" varStatus="index">
		<tr>
		<td>${index.count}</td>
		<td>${user.firstName}</td>
		<td>${user.lastName}</td>
		<td>${user.emailId}</td>
		<td>${user.roleName}</td>
		 <td style="align:center"><a href="./editUser?id=${user.userId}">
          <span class="glyphicon glyphicon-pencil"></span>
        </a>&nbsp;|&nbsp;
		
		<c:if test="${user.activeSW=='Y'}">
				<a href="./deleteUser?id=${user.userId}" onclick="return confirmDelete()"><i  class="glyphicon glyphicon-trash" style="color:red"></i></a>
		</c:if>
			 <c:if test="${user.activeSW=='N'}">
				<a href="./activeUser?id=${user.userId}" onclick="return confirmActivate()"><i class="fa fa-exchange" aria-hidden="true" style="color:blue"></i></a>
	</c:if>
	</td>
		</tr> 
		
		</c:forEach>	
		
	</table>
	<c:if test="${cpn>1}">
	<a href="viewAccounts?pno=${cpn-1}">Previous</a>
	</c:if>
	<c:forEach begin="1" end="${tp}" var="pno">
	<c:if test="${cpn==pno}">
	${pno}
	</c:if>
	<c:if test="${cpn!=pno}">
	<a href="./viewAccounts?pno=${pno}">${pno}</a>
	</c:if>
	</c:forEach>
	<c:if test="${cpn<tp}">
	<a href="viewAccounts?pno=${cpn+1 }">Next</a>
	</c:if>
</body>
</html>