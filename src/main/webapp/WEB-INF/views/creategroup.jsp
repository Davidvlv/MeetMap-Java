<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Create Group</title>
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
	<form:form class="create-group-form" action="creategroup" method="post" commandName="createGroupForm">
	  <h2>Create a new Group</h2>
	  <c:if test="${param.error != null}">
	    <div class="alert alert-danger">
	    		 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    				<span aria-hidden="true">&times;</span>
  			 </button>
	    		There was an error creating the group.
	    	</div>
    	  </c:if>
    	    
	  <div class="form-group">
	    <label for="groupName text-left">Group Name</label>
	    <form:input path="groupName" class="form-control" id="groupName" placeholder="Enter a group name" />
	    <form:errors path="groupName" cssClass="error" />
	  </div>
	  
	  <div class="form-group">
	  	<label for="description text-left">Description</label>
	    <form:textarea path="description" class="form-control group-description" id="description" placeholder="Enter a description for the group" />
	    <form:errors path="description" cssClass="error" />
	  </div>

	  <div>
	  	<a href="groups" class="btn btn-danger button">Cancel</a>
	  	<button type="submit" class="btn btn-success button">Create Group</button>
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
