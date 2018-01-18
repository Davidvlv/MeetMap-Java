<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Message</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="<c:url value="/resources/css/creategroups.css" />" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/views/navbar.jsp"/>

	
<div align="center">
	<form:form class="create-group-form" action="/elec5619/message" method="post" commandName="messageForm">
	  <h2>New Message</h2>
	  <c:if test="${param.error != null}">
	    <div class="alert alert-danger">
	    		 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    				<span aria-hidden="true">&times;</span>
  			 </button>
	    		There was an error creating the group.
	    	</div>
    	  </c:if>
    	    
	  <div class="form-group">
	    <label for="groupName text-left">To:</label>
	    <form:input path="recipient" class="form-control" id="recipient" placeholder="Enter your recipient" />
	    <form:errors path="recipient" cssClass="error" />
	  </div>
	  
	  <div class="form-group">
	    <label for="groupName text-left">From:</label>
	    <form:input path="sender" class="form-control" id="sender" value="${username}" readonly="true" />
	    <form:errors path="sender" cssClass="error" />
	  </div>
	  
	  <div class="form-group">
	    <label for="groupName text-left">Subject:</label>
	    <form:input path="subject" class="form-control" id="subject" placeholder="Enter a subject" />
	    <form:errors path="subject" cssClass="error" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="description text-left">Message:</label>
	    <form:textarea path="messageBody" class="form-control group-description" id="messageBody" placeholder="Enter your message" />
	    <form:errors path="messageBody" cssClass="error" />
	  </div>

	  <div>
	  	<a href="/elec5619/inbox" class="btn btn-danger button">Cancel</a>
	  	<button type="submit" class="btn btn-success button">Send Message</button>
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