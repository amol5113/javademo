
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Employees List</h1>
<table border="2" width="70%" cellpadding="2">
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
			<td><a href="editemp/${emp.userName}">Edit</a></td>
			<td><a href="deleteemp/${emp.email}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="empform">Add New Employee</a>