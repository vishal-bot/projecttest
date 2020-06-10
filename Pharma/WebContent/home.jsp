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

table {
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid: yellow;
	text-align: left;
	padding: 8px;
}

tr:nth-child(odd) {
	background-color: yellow;
}

.left {
	float: left;
	width: 20%;
	text-align: center;
}

.left a {
	background-color: yellow;
	padding: 8px;
	margin-top: 7px;
	display: block;
	width: 100%;
	color: black;
}

.main {
	float: left;
	width: 60%;
	padding: 0 20px;
}

.right {
	float: left;
	width: 20%;
	text-align: center;
}

.right a {
	background-color: yellow;
	padding: 8px;
	margin-top: 7px;
	display: block;
	width: 100%%;
	color: black;
}

@media only screen and (max-width:620px) {
	.main, .left, .right {
		width: 100%;
	}
}
</style>
</head>
<title>Pharma</title>
<body style="font-family: Verdana; color: black;">
	<header>
		<div
			style="background-color: yellow; padding: 15px; text-align: center;">
			<h1>PHARMA MANAGEMENT APPLICATION</h1>
		</div>
	</header>
	<section>
		<div style="overflow: auto">
			<div class="left">
				<a href="<%=request.getContextPath()%>/new">Add meds</a> <a
					href="<%=request.getContextPath()%>/list">Meds</a>
			</div>

			<div class="main">
				<h2 style="text-align: center;">MEDICINE</h2>
				<div>
					<table>

						<tr>
							<th>id</th>
							<th>name</th>
							<th>company</th>
							<th>Actions</th>
						</tr>

						<c:forEach var="med" items="${listMed}">
							<tr>
								<td><c:out value="${med.id}" /></td>
								<td><c:out value="${med.name}" /></td>
								<td><c:out value="${med.company}" /></td>

								<td><a href="edit?id=<c:out value='${med.id}' />">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="delete?id=<c:out value='${med.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="right">
				<a href="<%=request.getContextPath()%>/login">Login</a> <a
					href="<%=request.getContextPath()%>/logout">Logout</a>
			</div>
		</div>
	</section>


	<footer>
		<div
			style="background-color: yellow; text-align: center; padding: 10px; margin-top: 7px;">
			PHARMA.COM</div>

	</footer>
</body>
</html>