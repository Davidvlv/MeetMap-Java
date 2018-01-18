<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Your Events</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/viewgroups.css" />" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/views/navbar.jsp"/>

<div class="container">
<h2>Public Events</h2>


<div class="row">
	<div class="col-md-3">
		<a class="btn btn-primary" href="/events/add">Create Event</a>
	
		<h4>Filter by:</h4>
		<a href="events"> Public Events</a> <br />
		<a href="events/hosting"> Hosting Events</a> <br />
		<!-- <a href="/events/upcoming"> Upcoming Events</a> <br /> -->
		
		
	</div>
	
	<div class="col-md-9">
		<c:if test="${events.size() == 0}">
			<div class="border rounded border-primary groups-box bg-light">
				<p>It seems that there aren't any groups in your area. You should correct that!</p>
				<a class="btn btn-primary" href="/add">Create Event</a>
			</div>
		</c:if>
		<c:if test="${events.size() > 0}">
			<c:forEach var="event" items="${events}">
				<div class="border rounded border-primary groups-box bg-light">
					<h4><a href="/elec5619/events/${event.id}"> ${event.eventName} </a></h4><br />
					<a href="/elec5619/userprofile/${event.host}">Hosted by ${event.host}</a>
				</div>
			</c:forEach>
		</c:if>
				
	</div>
</div>
</div>

	
<!-- Bootstrap JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  
</body>
</html>
