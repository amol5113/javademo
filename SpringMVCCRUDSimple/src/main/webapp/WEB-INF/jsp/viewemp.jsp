
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<h1>User List</h1>
<table class="table table-hover" border="2" width="70%" cellpadding="1">
	<tr>
		<th>User Name</th>
		<th>Email</th>
		<th>User Type</th>
		<th>Gender</th>
		<th>Java</th>
		<th>C</th>
		<th>CPP</th>
		<th>SQL</th>
	</tr>
	<c:forEach var="emp" items="${list}">
		<tr>
			<td><c:out value="${emp.userName}" /></td>
			<td><c:out value="${emp.email}" /></td>
			<td><c:out value="${emp.userType}" /></td>
			<td><c:out value="${emp.gender}" /></td>
			<td><c:out value="${emp.java}" /></td>
			<td><c:out value="${emp.c}" /></td>
			<td><c:out value="${emp.cpp}" /></td>
			<td><c:out value="${emp.sql}" /></td>
			<%-- <td><c:forEach var="s" items="${emp.interest}">
						<c:if test="${s != ''}">
							<li>${s}</li>
						</c:if>

					</c:forEach></td>
			<td>${emp.salary}</td>
			<td>${emp.designation}</td> --%>
			<td><a href="editemp/${emp.userName}" class="btn btn-success btn-lg">
          <span class="glyphicon glyphicon-edit"></span> Edit 
        </a></td>
			<td><a href="deleteemp/${emp.userName}" class="btn btn-danger btn-lg">
          <span class="glyphicon glyphicon-remove"></span> Remove </a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="empform">Add New Employee</a>