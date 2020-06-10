<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Grocery Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> Grocery Management App </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<h3 class="text-center">Login</h3>
			<hr>
			<div class="card-body">

				<form action="<%=request.getContextPath()%>/loginA" method="post">
					<fieldset class="form-group">
						<label>UserName</label> <input type="text" name="username"
							class="form-control" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Password</label> <input type="text" name="password"
							class="form-control" required="required">
					</fieldset>
					<p style="color: red;">wrong credantials</p>
					<button class="btn btn-success" type="submit" value="login">Submit</button>

				</form>
			</div>
		</div>
	</div>

</body>
</html>