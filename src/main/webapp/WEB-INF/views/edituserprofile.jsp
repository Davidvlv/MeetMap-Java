<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>

<script src="<c:url value="/resources/js/edituserprofile.js" />"></script>
<link href="<c:url value="/resources/css/edituserprofile.css" />"
	type="text/css" rel="stylesheet">
<title>Meet Map</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/navbar.jsp" />

	<div class="container-fluid">
		<form:form class="editprofileform" action="edit" method="post"
			commandName="userForm" enctype="multipart/form-data">

			<div class="sub-entry left-form">

				<div class="form-group image-group">
					<img id="userImage" class="profileImage img-thumbnail"
						src="data:image/jpeg;base64,${userImage}" /> <input
						id="imageUpload" class=".form-control-file" type="file"
						name="file" value="data:image/jpeg;base64,${userImage}" />
				</div>

				<div class="form-group">
					<input class="form-control" maxlength="15" value="${user.username}"
						readonly />
				</div>

				<div class="form-group">
					<input class="form-control" maxlength="20"
						value="${user.emailAddress}" readonly />
				</div>

				<div class="form-group">
					<form:input class="form-control" maxlength="15" path="firstName"
						placeholder="First Name" value="${user.userProfile.firstName}" />
				</div>

				<div class="form-group">
					<form:input class="form-control" maxlength="15" path="lastName"
						placeholder="Last Name" value="${user.userProfile.lastName}" />
				</div>

				<div class="form-group">
					<form:input class="form-control" type="number" min="0" max="100"
						maxlength="2" path="age" placeholder="Age"
						value="${user.userProfile.age}" />
				</div>

				<div class="form-group">
					<form:textarea rows="4" cols="50" maxlength="150"
						class="form-control" path="Description" placeholder="Description"
						value="${user.userProfile.description}" />
				</div>
			</div>

			<div class="sub-entry right-form">

				<div class="form-group interest-group">
					<input id="interestInput" class="form-control"
						placeholder="Add Interest" />
					<button id="addInterest" class="btn btn-primary button" type=button>Add</button>
					<p class="form-text text-muted" style="margin-top: 10px;">Add
						interests here, to see related events on the map. Note, Your
						Interests cannot contain special characters.</p>
				</div>

				<ul id="interestsList">
					<c:forEach var="interest" varStatus="status"
						items="${user.userProfile.interests}">
						<li class="interest" id="entry-${interest.interest}">
							<div class="interest-value">
								${interest.interest}
								<button type="button"
									class="glyphicon glyphicon-remove remove-button"
									onclick="removeInterest('${interest.interest}')"></button>
							</div>
						</li>
						<input id="input-${interest.interest}"
							name="interests[${status.index}].interest" type="hidden"
							value="${interest.interest}" />
					</c:forEach>
				</ul>
			</div>

			<div class="submit-group btn-group">
				<a href="<c:url value='/userprofile/${user.username}'/>">
					<button id="cancelButton" class="btn btn-primary button btn-lg">Cancel</button>
				</a>
				<button class="btn btn-primary button btn-lg" type="submit">Save</button>

			</div>
		</form:form>
	</div>

	<!-- Bootstrap JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>

</body>
</html>