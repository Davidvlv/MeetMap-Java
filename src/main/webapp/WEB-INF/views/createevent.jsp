<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Create Event</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="<c:url value="/resources/css/createevent.css" />" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
  	
</head>
<body>

<jsp:include page="/WEB-INF/views/navbar.jsp"/>

<div align="center">
	<form:form class="create-event-form" method="post" commandName="createEventForm">
	  <h2>Create a new Event</h2>
	  <c:if test="${param.error != null}">
	    <div class="alert alert-danger">
	    		 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    				<span aria-hidden="true">&times;</span>
  			 </button>
    		There was an error creating the event.
    	</div>
      </c:if>
	  <div class="form-group">
	    <label for="eventName text-left">Event Name</label>
	    <form:input path="eventName" class="form-control" id="eventName" placeholder="Enter an event name" />
	    <form:errors path="eventName" cssClass="error" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="description text-left">Description</label>
	    <form:textarea path="description" class="form-control event-description" id="description" placeholder="Enter a description for the group" />
	    <form:errors path="description" cssClass="error" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="startTime text-left">Start Date</label>
	  	<form:input path="startTime" class="form-control" type="datetime-local" id="startTime" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="endTime text-left">End Date</label>
	  	<form:input path="endTime" class="form-control" type="datetime-local" id="endTime"/>
	  </div>
	  
	  
	  <div class="form-group">
	  	<label for="latitude" type="text" >Latitude</label>
	  	<form:input path="latitude" class="form-control" name="latitude" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="longitude">Longitude</label>
	  	<form:input path="longitude" class="form-control" name="longitude" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="isPrivate">Private Event?</label>
	  	<form:checkbox  path="isPrivate" name="isPrivate" />
	  </div>
	  
	  <div>
	  	<a href="elec5619/events/" class="btn btn-danger button">Cancel</a>
	  	<button type="submit" class="btn btn-success button">Create Event</button>
	  </div>		  
	</form:form>
</div>
	
<!-- Bootstrap JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
 
</body>
</html>
