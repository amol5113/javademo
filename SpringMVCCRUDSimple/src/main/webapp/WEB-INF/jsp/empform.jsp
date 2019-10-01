 <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spr1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css"
	integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
	integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
	crossorigin="anonymous"></script>
</head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: red;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<body>

Language : <a href="?language=en">English</a>|<a href="?language=hi">हिंदी</a>


Current Locale : ${pageContext.response.locale}

	<spr:form action="save" modelAttribute="emp" enctype="multipart/form-data"
		class="form-group col-md-6" method="post">
		
		<div class="form-group m-40">
			<div class="input-group m-4">
				<br> <span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span>
				<spr1:message code="message.UserName" var="userNamee" />
				<spr:input path="userName" placeholder="${userNamee}"
					class="form-control" />
			</div>
			<spr:errors path="userName" cssClass="errorblock" class="form-control"/>
			
			
			<div class="input-group m-4">
				<br> <span class="input-group-addon"><i
					class="glyphicon glyphicon-lock"></i></span>
				<spr1:message code="message.Password" var="Password" />
				<spr:password path="Password" placeholder="${Password }"
					class="form-control" />
			</div>
			<spr:errors path="Password" cssClass="errorblock" class="form-control"/>
			<div class="input-group m-4">
				<br> <span class="input-group-addon"><i
					class="glyphicon glyphicon-envelope"></i></span>
				<spr1:message code="message.email" var="email" />	
				<spr:input type="text" path="email" placeholder="${email}"
					class="form-control" />
			</div>
			<spr:errors path="email" cssClass="errorblock" class="form-control"/>
			<br>
			<div class="input-group m-4">
				<spr:select path="gender" class="form-control">
					<spr1:message code="message.selectgender" var="selectgender" />
					<spr:option value="-" label="--${selectgender}--" />
					<spr1:message code="message.male" var="male" />
					<spr:option value="${male }" label="${male }" />
					<spr1:message code="message.female" var="female" />
					<spr:option value="${female }" label="${female }" />
				</spr:select>
			</div>
			<spr:errors path="gender" cssClass="errorblock" class="form-control"/>
			<br>
			<spr1:bind path="gender">
									<input type="date" class="form-control" id="dateofbirth" max="2000-12-31"
									name="dob" autocomplete="off"
										>
									</spr1:bind>
			<div class="input-group m-4">
				<spr1:message code="message.usertype" var="usertype" />
				${usertype}
				<spr1:message code="message.employee" var="employee" />
				<spr:radiobutton path="userType" value="${employee}" />
				${employee }
				<spr1:message code="message.student" var="student" />
				
				<spr:radiobutton path="userType" value="${student}" />
				${student}
			</div>
			<spr:errors path="userType" cssClass="errorblock" class="form-control"/>
			<div class="input-group m-4">
			<spr1:message code="message.selectlang" var="selectlang" />
				${selectlang}
				<spr1:message code="message.java" var="java" />
				<spr:checkbox path="java" value="${java}" />
				${java}
				<spr1:message code="message.c" var="c" />
				<spr:checkbox path="c" value="${c}" />
				${c}
				<spr1:message code="message.cpp" var="cpp" />
				<spr:checkbox path="cpp" value="${cpp}" />
				${cpp}
				<spr1:message code="message.sql" var="sql" />
				<spr:checkbox path="sql" value="${sql}" />
				${sql}
			</div>
			<div class="input-group m-4">
			<spr1:message code="message.comment" var="comment" />
				<spr:textarea path="comment" rows="3" cols="20"
					placeholder="${comment }" class="form-control" />
			</div>
			<spr:errors path="comment" cssClass="errorblock" class="form-control"/>
			
			<!--  <input type = "file" name = "file" size = "50" />
 -->
			<br>
			<div class="input-group m-4">
				<spr1:message code="message.register" var="register" />
				<input type="submit" value="${register}" class="btn btn-success mr-2" />
				<spr1:message code="message.cancle" var="cancle" />
				<input type="reset" value="${cancle }" class="btn btn-danger" />
			</div>
			<br>
		</div>
	</spr:form>


</body>
</html>