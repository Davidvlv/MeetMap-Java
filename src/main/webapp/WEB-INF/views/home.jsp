<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">

<!-- Google Maps CSS -->
<style>
#map {
	height: -o-calc(100% - 56px); /* opera */
	height: -webkit-calc(100% - 56px); /* google, safari */
	height: -moz-calc(100% - 56px); /* firefox */
	width: 100%;
}
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/navbar.jsp" />

	<!-- Event Details Modal -->
	<div class="modal fade" id="eventModal" tabindex="-1" role="dialog"
		aria-labelledby="eventModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="eventModalLabel">{{EVENT NAME
						HERE}}</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="eventModalBody">{{EVENT DESCRIPTION HERE}}</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Join Meet</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Create Event Modal -->
	<div class="modal fade" id="createModal" tabindex="-1" role="dialog"
		aria-labelledby="createModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="createModalLabel">Create Event</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form class="create-event-form" method="post"
						commandName="createEventForm">
						<h2>Create a new Event</h2>
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								There was an error creating the event.
							</div>
						</c:if>
						<div class="form-group">
							<label for="eventName text-left">Event Name</label>
							<form:input path="eventName" class="form-control" id="eventName"
								placeholder="Enter an event name" />
							<form:errors path="eventName" cssClass="error" />
						</div>

						<div class="form-group">
							<label for="description text-left">Description</label>
							<form:textarea path="description"
								class="form-control event-description" id="description"
								placeholder="Enter a description for the group" />
							<form:errors path="description" cssClass="error" />
						</div>

						<div class="form-group">
							<label for="startTime text-left">Start Date</label>
							<form:input path="startTime" class="form-control"
								type="datetime-local" id="startTime" />
						</div>

						<div class="form-group">
							<label for="endTime text-left">End Date</label>
							<form:input path="endTime" class="form-control"
								type="datetime-local" id="endTime" />
						</div>


						<div class="form-group">
							<form:input path="latitude" class="form-control" name="latitude"
								id="latitude" hidden="hidden" />
						</div>

						<div class="form-group">
							<form:input path="longitude" class="form-control"
								name="longitude" id="longitude" hidden="hidden" />
						</div>

						<div class="form-group">
							<label for="isPrivate">Private Event?</label>
							<form:checkbox path="isPrivate" name="isPrivate" />
						</div>

						<div>
							<input type="submit" id="submit-form" hidden />
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<label for="submit-form" class="btn btn-primary"
						style="margin-top: 9px;">Create Meet</label>
				</div>
			</div>
		</div>
	</div>

	<!-- Google Map -->
	<div id="map"></div>
	<script>
		var map;
		var meets = [];
		var markers = [];

		var latitude;
		var longitude;

		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : -33.864677,
					lng : 151.211160
				},
				zoom : 15
			});
			infoWindow = new google.maps.InfoWindow();

			google.maps.event.addListener(map, 'click', function(event) {
				latitude = event.latLng.lat()
				longitude = event.latLng.lng()

				show_create_event_dialog(latitude, longitude);
			});
			
			// test meet
			var meet = {
				lattitude : -33.864677,
				longitude : 151.211160,
				eventName : "test",
				description : "description",
				host : "hoster",
				isPrivate : "false",
				startTime: 1508882400000,
				endTime : 1508929200000
			};
			place_marker(meet);

			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/rest/events/",
				success : function(data) {
					events = JSON.parse(data);
					
					for (i = 0; i < events.length; i++) {
						place_marker(events[i]);
					}
				}
			});
		}

		function show_create_event_dialog(latitude, longitude) {
			// add longitude and latitude to the form
			$('#latitude').val(latitude);
			$('#longitude').val(longitude);

			// show the create modal
			$('#createModal').modal('show');
		}

		function place_marker(meet) {
			
			meets.push(meet);

			latitude = meet.latitude;
			longitude = meet.longitude;
			
			var latLng = new google.maps.LatLng(latitude, longitude);
			var marker = new google.maps.Marker({
				position : latLng,
				map : map,
			});
			markers.push(marker);

			google.maps.event
					.addListener(
							marker,
							'click',
							function() {
								this_index = markers.indexOf(marker);
								this_meet = meets[this_index];
								selected_meet = this_meet;
								$("#eventModalLabel").html(meet.eventName);
								$("#eventModalBody").html("Hosted by: " + meet.host + "<br /><br />" + meet.description + "<br /><br />Start Time: " + meet.startTime + "<br />End Time: " + meet.endTime);
								html = '<div class="marker-details"><h2 class="text-center">'
										+ meet.eventName
										+ '</h2>'
										+ '<p>'
										+ meet.description
										+ '</p>'
										+ '<button type="button" class="btn btn-default text-center" data-toggle="modal" data-target="#eventModal">'
										+ 'View Details'
										+ '</button>'
										+ '</div>'
								infoWindow.close();
								infoWindow.setContent(html);
								infoWindow.open(map, marker);
							});
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD7oKuoseyWVqMIZ1hzTft9r9g-7iyhZt4&callback=initMap">
		
	</script>



	<!-- Bootstrap JavaScript -->
	<!-- jQuery first (not slim because I need ajax), then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>
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
