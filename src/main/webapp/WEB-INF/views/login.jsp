<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
<title>MeetMap</title>
</head>
<body>
	<c:url value="/j_spring_security_check" var="loginUrl" />
	<div class="container-fluid vertical-center">
		<form:form class="loginform" action="${loginUrl}" name="loginForm"
			method="post">
			<h1 class="header text-center">MeetMap</h1>
			<c:if test="${param.error != null}">
				<div class="alert alert-danger alert-dismissable" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Dismiss">
						<span aria-hidden="true"> &times; </span>
					</button>
					<strong>Login Failed!</strong> Invalid Username or Password
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success alert-dismissable" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Dismiss">
						<span aria-hidden="true"> &times; </span>
					</button>
					You have been logged out.
				</div>
			</c:if>
			<div class="form-group" style="text-align: left;">
				<label for="j_username">Username</label> <input class="form-control"
					type="text" id="j_username" name="j_username" />
			</div>
			<div class="form-group" style="text-align: left;">
				<label for="j_password">Password</label> <input class="form-control"
					type="password" id="j_password" name="j_password" />
			</div>
			<button class="btn btn-primary button" type="submit">Log in</button>
			<a class="btn btn-link button" href="register">Register</a>
		</form:form>
	</div>
		<!-- Bootstrap JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</body>
</html>