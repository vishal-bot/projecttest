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
		<div class="main">
			<fieldset>

				<form action="<%=request.getContextPath()%>/loginA" method="post">


					<table>
						<tr>
							<td>UserName</td>
							<td><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" /></td>
						</tr>

					</table>
					<input type="submit" value="login" />

				</form>

			</fieldset>

		</div>
	</center>

	<div
		style="background-color: yellow; text-align: center; padding: 10px; margin-top: 7px;">
		PHARMA.COM</div>


</body>
</html>