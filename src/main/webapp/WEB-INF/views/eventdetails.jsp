<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Event Details</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="<c:url value="/resources/css/groupdetails.css" />" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container top-div">
  <div class="row">
  	<div class="col">
      <div class="border rounded border-primary groups-box bg-light">
        <div class="text-center bg-secondary text-light">
     		<h2>${event.eventName}</h2>
    	</div>
    	  	<table class="eventDetailsContainer col-sm-12">
  		<tr>
  			<td class="td-property" align="center">Host: </td>
  			<td class="td-value" align="center">
  				<a href="/elec5619/userprofile/${event.host}" >${event.host}</a>
  			</td>
  		</tr>
		<tr>
 			<td class="td-property" align="center">Description: </td>
			<td class="td-value" align="center">${event.description}</td>
		</tr>
		<tr>
			<td class="td-property" align="center">Start Time: </td>
			<td class="td-value" align="center">${event.startTime }</td>
		</tr>
		<tr>
			<td class="td-property" align="center">End Time: </td>
			<td class="td-value" align="center">${event.endTime}</td>
		</tr>
		<tr>
			<td class="td-property" align="center">isPrivate</td>
			<td class="td-value" align="center">${event.isPrivate}</td>
		</tr>
		<tr>
			<td class="td-property" align="center">latitude</td>
			<td class="td-value" align="center">${event.latitude}</td>
		</tr>
		<tr>
			<td class="td-property" align="center">longitude</td>
			<td class="td-value" align="center">${event.longitude}</td>
		</tr>
		
	</table>
  	  </div>
 	</div>
  </div>
  <div class="row">
  	<div class="col">

	</div>
  </div>
  <div class="row">
  	<div class="col">
	  	<c:if test="${ canEdit }">
	  		
	  		<form action="${id}/delete" method="post">
	  			<a class="btn btn-primary" href="${id}/edit">Edit Event</a>
	  			<button class="btn btn-primary">Delete Event</button>
	  		</form>
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


