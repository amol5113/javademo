<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url var="url" value="choose" />
	<h3 align="center">Choose Image</h3>
	<%-- <form method="post" action="${url}">

		<table style="background-color: cyan; margin: auto;">

			<c:forEach var="img" items="${requestScope.image_list}">
				<tr>
					<td><input type="radio" name="imgName" value="${img}" />${img}</td>
					<!-- <img src=${requestScope.loc}/${img} width="240" height="300"/> -->
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Choose" /></td>${url}
			</tr>
		</table>

	</form> --%>
	<form:form method="post" action="retrieveFile"
		enctype="multipart/form-data">
		
		<input type="text" id="name" name="name"> : Image Name
<p>
			<input type="submit" value="Search">
		</p>
	</form:form>
</body>
</html>
