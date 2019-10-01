<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<spring:url value='/css/main.css'/>"
	type="text/css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

</head>
<body>
	<h3 align="center">Testing Images</h3>

	<div class="form-row">
		<div class="form-group col-6">
			<img id="myImg" src="data:image/jpeg;base64,${image}" alt="..."
				width="300" height="300">
		</div>
		<div class="form-group col-6">
			<img id="myImg2" src="data:image/jpeg;base64,${image}" alt="..."
				width="300" height="300">
		</div>
	</div>
	<!-- The Modal -->
	<div id="myModal" class="modal">
		<span class="close" style="color: red; font-size: 3rem;">&times;</span>
		<img class="modal-content" id="img01" height="100%" width="80%">
		<div id="caption"></div>
	</div>
</body>
<script>
// Get the modal
var modal = document.getElementById("myModal");
// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");
var img2 = document.getElementById("myImg2");
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
  //captionText.innerHTML = name;
}
img2.onclick = function(){
	  modal.style.display = "block";
	  modalImg.src = this.src;
	  //captionText.innerHTML = name;
	}
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
}
function my1(){
	alert("Successfully Approved ");
}
</script>
</html>