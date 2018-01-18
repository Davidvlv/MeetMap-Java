<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="<c:url value="/resources/css/register.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/bootstrap/jquery/jquery.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
<title>Register</title>
</head>
<body>
	<div class="vertical-center" align="center">
		<form:form class="signupform" action="register" method="post"
			commandName="userForm">
			<h1 class="header text-center">MeetMap</h1>
			<c:if test="${param.error != null}">
				<div class="username-error alert alert-danger alert-dismissable"
					role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Dismiss">
						<span aria-hidden="true"> &times; </span>
					</button>
					Username already taken.
				</div>
			</c:if>

			<div class="form-group">
				<form:input id="username" class="form-control"
					placeholder="Username" path="username" />
				<form:errors path="username" cssClass="error" />
			</div>

			<div class="form-group">
				<form:password id="password" class="form-control"
					placeholder="Password" path="password" />
				<form:errors path="password" cssClass="error" />
			</div>

			<div class="form-group">
				<form:password id="confirmPassword" class="form-control"
					placeholder="Confirm Password" path="confirmPassword" />
				<form:errors path="confirmPassword" cssClass="error" />
			</div>

			<div class="form-group">
				<form:input id="emailAddress" class="form-control"
					placeholder="Email" path="emailAddress" />
				<form:errors path="emailAddress" cssClass="error" />
			</div>

			<div class="button-actions">
				<a href="<c:url value='/login'/>"> <input id="cancelButton"
					class="btn btn-primary button" value="Cancel" />
				</a> <input id="registerButton" class="btn btn-primary button"
					type="submit" value="Register" />
			</div>
			<!-- Create cancel button -->
		</form:form>
	</div>
</body>
</html>