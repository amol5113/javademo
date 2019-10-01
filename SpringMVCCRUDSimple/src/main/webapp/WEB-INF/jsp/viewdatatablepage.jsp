<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spr1"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<%@ page session="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8" />
<title>Spring Boot + JPA + Datatables</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
</head>

<body>
Language : <a href="?language=en">English</a>|<a href="?language=hi">Hindi</a>
Current Locale : ${pageContext.response.locale}

	<h1>User Table</h1>
	<table id="emptable" class="table table-hover">

		<!-- Header Table -->
		<thead>
			<tr>
				<th>User Name</th>
				<th>Password</th>
				<th>Email</th>
				<th>User Type</th>
				<th>Gender</th>
				<th>Java</th>
				<th>C</th>
				<th>CPP</th>
				<th>SQL</th>
				<th>Comment</th>
			</tr>
		</thead>
		<!-- BODY -->


		<!-- Footer Table -->
		<tfoot>
			<tr>
				<th>User Name</th>
				<th>Password</th>
				<th>Email</th>
				<th>User Type</th>
				<th>Gender</th>
				<th>Java</th>
				<th>C</th>
				<th>CPP</th>
				<th>SQL</th>
				<th>Comment</th>
			</tr>
		</tfoot>
	</table>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		var data = eval('${list}');
		var table = $('#emptable').DataTable({
			"aaData" : data,
			"aoColumns" : [ {
				"mData" : "userName"
			}, {
				"mData" : "Password"
			}, {
				"mData" : "email"
			}, {
				"mData" : "userType"
			}, {
				"mData" : "gender"
			}, {
				"mData" : "java"
			}, {
				"mData" : "c"
			}, {
				"mData" : "cpp"
			}, {
				"mData" : "sql"
			}, {
				"mData" : "comment"
			} ],
			"paging" : true
		});
	});
</script>
</html>