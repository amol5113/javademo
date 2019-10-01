<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="table-bordered" border="2" width="95%" cellpadding="2">
		<tr>
			<th><spring:message code="viewDBFiles.id" text="ID" /></th>
			<th><spring:message code="viewDBFiles.filename" text="File Name" /></th>
			<th><spring:message code="viewDBFiles.filecontenttype"
					text="File Type" /></th>
			<th><spring:message code="viewDBFiles.download" text="Download" /></th>
		</tr>
		<c:forEach var="file" items="${list}">
			<tr>
				<td>${file.id}</td>
				<td>${file.filename}</td>
				<td>${file.fileContentType}</td>
				<td><a download href="downloadFile/${file.id}"> <spring:message
							code="viewDBFiles.clickhere" text="Click Here" />
				</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>