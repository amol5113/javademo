<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>File Upload</h1>

	<form:form method="post" action="imagePathUpload" 
		enctype="multipart/form-data">
		

		<div class="form-group col-md-6">
			 <spring:bind path="simpleImagee.simpleImage"> 
									 Image<input id="LicenseImg" name="simpleImage"
					accept="image/*"  placeholder="License"
					class="form-control" autocomplete="off" type="file">
				
			 </spring:bind> 
		</div>

		<p>
			<input type="submit" value="Upload">
		</p>
	</form:form>
</body>
</html>