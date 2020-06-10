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
				<a href="https://www.javaguides.net" class="navbar-brand"> Grocery
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Grocerys</a></li>
			</ul>
			<ul class="navbar-nav float-right">
				<li><a href="<%=request.getContextPath()%>/login"
					class="nav-link">LogOut</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${grocery != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${grocery == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${grocery != null}">
                                    Edit Grocery
                                </c:if>
						<c:if test="${grocery == null}">
                                    Add New Grocery
                                </c:if>
					</h2>
				</caption>

				<c:if test="${grocery != null}">
					<input type="hidden" name="id" value="<c:out value='${grocery.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Grocery Name</label> <input type="text"
						value="<c:out value='${grocery.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Grocery Type</label> <input type="text"
						value="<c:out value='${grocery.type}' />" class="form-control"
						name="type">
				</fieldset>
				<fieldset class="form-group">
					<label>Grocery Company</label> <input type="text"
						value="<c:out value='${grocery.company}' />" class="form-control"
						name="company">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>