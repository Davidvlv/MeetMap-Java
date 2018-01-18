<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/userprofile.css" />"
	rel="stylesheet">

<title>Meet Map</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/navbar.jsp" />

	<div class="userProfile container-fluid">
		<div class="profileContainer">
			<div class="row">
				<div class="col-sm-12">
					<img id="profileImage" class="img-thumbnail"
						src="data:image/jpeg;base64,${userImage}" />
				</div>
			</div>

			<div class="row">
				<table class="detailsContainer col-sm-6">
					<tr>
						<td class="td-property" align="left">Username</td>
						<td class="td-value" align="left"><div>${username}</div></td>
					</tr>
					<tr>
						<td class="td-property" align="left" class="profileLabel">Name</td>
						<td class="td-value" align="left"><div id="firstName"></div></td>
					</tr>
					<tr>
						<td class="td-property" align="left" class="profileLabel">Age</td>
						<td class="td-value" align="left"><div id="age"></div></td>
					</tr>
					<tr id="groupCountContainer">
						<td class="td-property">Number of Groups</td>
						<td id="groupCount" class="td-value"></td>
					</tr>
					<tr>
						<td class="td-property" align="left" class="profileLabel">Description
							<br></br>
						</td>
						<td class="td-value" align="left"><div id="description"></div></td>
					</tr>
					<tr>
						<td class="td-property" class="profileLabel">Interests</td>
						<td class="td-value">
							<ul id="interestsList">
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<c:if test="${isOwnerOfProfile}">
						<a id="editLink" class="btn btn-primary button" href="edit">Edit</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(
				function() {
					var pathArray = window.location.pathname.split('/');
					var username = pathArray[3];
					$.ajax({
						url : '/elec5619/rest/groups/count',
						type : 'GET',
				        async: false,
						dataType : 'json',
						success : function(resp) {
							if (resp.count > 0){
							$('#groupCount').html(resp.count);
							}
						}
					});
					$.ajax({
						url : '/elec5619/rest/userprofile/' + username,
						type : 'GET',
				        async: false,
						dataType : 'json',
						success : function(resp) {
							if (resp.firstName !== "" && resp.firstName !== null) {
								$('#firstName').html(
										resp.firstName + " " + resp.lastName);
							}
							$('#age').html(resp.age);
							$('#description').html(resp.description);
							resp.interests.forEach(function(interest) {
								$('#interestsList').append(
										"<li class=\"interest\">"
												+ interest.interest + "</li>");
							});
						}
					});
				});
	</script>
</body>
</html>