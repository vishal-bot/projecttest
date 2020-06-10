<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport"
	content="width=device-width, initial-scale=1.0">
<style>
* {
	box-sizing: border-box;
}

.main {
	width: 60%;
	padding: 0 20px;
}

@media only screen and (max-width:620px) {
	.main {
		width: 100%;
	}
}
</style>
</head>
<title>Pharma</title>
<body style="font-family: Verdana; color: black;">
	<div
		style="background-color: yellow; padding: 15px; text-align: center;">
		<h1>PHARMA MANAGEMENT APPLICATION</h1>
	</div>
	<center>
<h2>Click here to login in system</h2>
<a href="<%=request.getContextPath()%>/login"><button>login</button></a> 

		<div
			style="background-color: yellow; text-align: center; padding: 10px; margin-top: 7px;">
			PHARMA.COM</div>
</body>
</html>