<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Your Groups</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/viewgroups.css" />" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/views/navbar.jsp"/>

<div class="border rounded border-primary groups-box bg-light">
	<div class="item">
		<div>
			<a data-toggle="collapse" data-parent="#owned-groups" href="#owned-groups" 
					aria-expanded="true" aria-controls="owned-groups">
      			Groups You Own.
      			<c:if test="${ownedGroups.size() == 0}">
					<span class="badge badge-danger">0 groups</span>
				</c:if>
				<c:if test="${ownedGroups.size() == 1}">
					<span class="badge badge-success">${ownedGroups.size()} group</span>
				</c:if>
				<c:if test="${ownedGroups.size() > 1}">
					<span class="badge badge-success">${ownedGroups.size()} groups</span>
				</c:if>
    			</a>
		</div>
    		<div id="owned-groups" class="collapse" role="tabpanel">
      		<p class="mb-3">
      			<c:if test="${ownedGroups.size() == 0}">
					<p>You own no groups, let's change that !</p>
					<a class="btn btn-primary" href="creategroup">Create Group</a>
				</c:if>
				<c:if test="${ownedGroups.size() > 0}">
					<c:forEach var="group" items="${ownedGroups}">
						<form:form action="groups" method="post" commandName="viewGroupForm">
							<form:input path="groupToView" value="${group.id}" type="hidden"/>
							<input type="submit" value="${group.groupName}" class="btn btn-info"/>
						</form:form>
					</c:forEach>
				</c:if>
      		</p>
    		</div>
  	</div>
</div>

<div class="border rounded border-primary groups-box bg-light">
	<div class="item">
		<div>
			<a data-toggle="collapse" data-parent="#groups" href="#groups" 
					aria-expanded="true" aria-controls="groups">
      			Groups You Belong To.
      			<c:if test="${groups.size() == 0}">
					<span class="badge badge-danger">0 groups</span>
				</c:if>
				<c:if test="${groups.size() == 1}">
					<span class="badge badge-success">${groups.size()} group</span>
				</c:if>
				<c:if test="${groups.size() > 1}">
					<span class="badge badge-success">${groups.size()} groups</span>
				</c:if>
    			</a>
		</div>
    		<div id="groups" class="collapse" role="tabpanel">
      		<p class="mb-3">
      			<c:if test="${groups.size() == 0}">
					<p>You belong to no groups</p>
				</c:if>
				<c:if test="${groups.size() > 0}">
					<c:forEach var="group" items="${groups}">
						<form:form action="groups" method="post" commandName="viewGroupForm">
							<form:input path="groupToView" value="${group.id}" type="hidden"/>
							<input type="submit" value="${group.groupName}" class="btn btn-info"/>
						</form:form>
					</c:forEach>
				</c:if>
      		</p>
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
