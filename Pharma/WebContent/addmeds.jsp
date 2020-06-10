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
}

tr {
	border: 1px solid: yellow;
	text-align: right;
	padding: 8px;
}

.b {
	text-align: center;
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
	float: right;
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
	<div
		style="background-color: yellow; padding: 15px; text-align: center;">
		<h1>PHARMA MANAGEMENT APPLICATION</h1>
	</div>


	<div style="overflow: auto">
		<div class="left">
			<a href="<%=request.getContextPath()%>/new">Add meds</a> <a
				href="<%=request.getContextPath()%>/list">Meds</a>
		</div>

		<div class="main">
			
			<div>
				<center>
					<c:if test="${med != null}">
						<form action="update" method="post">
					</c:if>
					<c:if test="${med == null}">
						<form action="insert" method="post">
					</c:if>
					<caption>
						<h2>
							<c:if test="${med != null}">
                                    Edit Med
                                </c:if>
							<c:if test="${med == null}">
                                    Add New Med
                                </c:if>
						</h2>
					</caption>
					<c:if test="${med != null}">
						<input type="hidden" name="id" value="<c:out value='${med.id}' />" />
					</c:if>

					<fieldset>
							<table>
								<tr>
									<td><label for="name">MED Name:</label></td>
									<td><input type="text" name="name"
										value="<c:out value='${med.name}' />"></td>
								</tr>
								<tr>
									<td><label for="company">MED Company:</label></td>
									<td><input type="text" name="company"
										value="<c:out value='${med.company}' />"></td>
								</tr>

								<tr>
									<td></td>
									<td class="b"><input type="submit" value="Save"></td>
								</tr>
							</table>
					</fieldset>
					</form>
				</center>
			</div>

		</div>


		<div class="right">
			<a href="<%=request.getContextPath()%>/login">Login</a> 
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</div>
	</div>




	<div
		style="background-color: yellow; text-align: center; padding: 10px; margin-top: 7px;">
		PHARMA.COM</div>


</body>
</html>