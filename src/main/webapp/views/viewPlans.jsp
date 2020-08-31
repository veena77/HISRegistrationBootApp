<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <title>view page</title>
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
/* $(document).ready(function() {
    $('#planTable').DataTable({
    	"pagingType":"full_numbers";
    });
} );  */
function confirmDelete(){
	return confirm("Do you want to delete plan?");
}
function confirmActivate(){
	return confirm("Do you want to Activate plan?");
}
</script>
</head>
<body>
<h1 style="color:blue" align="center">View Plans</h1>
 <h2 style="color:navy;">${resMsg}</h2> 
<br><br>
	<table border="1" id="planTable" align="center">
	
			<tr>
				<th>SNO</th>
				<th>PLAN NAME</th>
				<th>PLAN DESC</th>
				<th>START DATE</th>
				<th>END DATE</th>
				<th>ACTION</th>
			</tr>
		<c:forEach items="${list}" var="plan" varStatus="index">
		<tr>
		<td>${index.count}</td>
		<td>${plan.planName}</td>
		<td>${plan.planDesc}</td>
		<td>${plan.startDate}</td>
		<td>${plan.endDate}</td>
		 <td style="align:center"><a href="./editUser?id=${plan.planId}">
          <span class="glyphicon glyphicon-pencil"></span>
        </a>&nbsp;|&nbsp;
		
		<c:if test="${plan.activeSW=='Y'}">
				<a href="./removeplan?id=${plan.planId}" onclick="return confirmDelete()"><i  class="glyphicon glyphicon-trash" style="color:red"></i></a>
		</c:if>
			 <c:if test="${plan.activeSW=='N'}">
				<a href="./activeplan?id=${plan.planId}" onclick="return confirmActivate()"><i class="fa fa-exchange" aria-hidden="true" style="color:blue"></i></a>
	</c:if>
	</td>
		</tr> 
		
		</c:forEach>	
		
	</table>
	
</body>
</html>