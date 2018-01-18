<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>${group.groupName}</title>
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
  	<c:if test="${error.isEmpty() == false}">
		<div class="p-2 text-center" align="center">
      		<div class="alert alert-danger" align="center">
	    		 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    					<span aria-hidden="true">&times;</span>
  			 	</button>
	    			${error}
	    		</div>
    		</div>
	</c:if>
	<c:if test="${message.isEmpty() == false}">
		<div class="p-2 text-center" align="center">
      		<div class="alert alert-success" align="center">
	    		 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    					<span aria-hidden="true">&times;</span>
  			 	</button>
	    			${message}
	    		</div>
    		</div>
	</c:if>
  </div>
  <div class="row">
    <div class="col">
      <div class="border rounded border-primary groups-box bg-light">
     	<div class="p-2 text-center bg-secondary text-light">
     		Group Information
    		</div>
		<div class="division">
			<div>
				<label for="group-name">Group Name</label>
				<br>
				<div id="group-name" class="border rounded bg-white name-box">
					${group.groupName}
				</div>
			</div>
			<div class="break">
				<label for="description">Description</label>
				<br>
				<div id="description" class="border rounded bg-white description-box text-justify">
					${group.description}
				</div>
			</div>
	    		<br>
	    		<c:if test="${isAdmin}">
	     		<div>
	     			<form:form action="groupdetails/edit" method="post">
	     				<input type="submit" class="btn btn-primary btn-block" value="Edit Group"/>
	     			</form:form>
	     			
	     			<form:form action="groupdetails/delete" method="post">
	     				<input type="submit" class="btn btn-danger btn-block" value="Delete Group"/>
	     			</form:form>
	     		</div>
			</c:if>
			<c:if test="${isAdmin == false}">
				<div>
		      		<form:form action="groupdetails/leave" method="post">
		      			<input type="submit" class="btn btn-danger btn-block" value="Leave Group"/>
		      		</form:form>
		    		</div>
			</c:if>
		</div>
      </div>
    </div>
    <div class="col-6">
      <div class="border rounded border-primary groups-box bg-light">
      	<div class="p-2 text-center bg-secondary text-light">
     		Posts
    		</div>
    		<div class="division">
    			<div class="p-2">
	     		<form:form action="groupdetails/post" method="post" commandName="newPostForm">
	     			<div class="form-group">
						<form:input id="post" class="form-control"
							placeholder="What do you want to post ?" path="content" />
						<form:errors path="content" cssClass="error" />
					</div>
					<button type="submit" class="btn btn-success btn-block">
						Add Post
					</button>
		      	</form:form>
	    		</div>
	    		<div class="p-2">
	     		<c:if test="${posts.size() == 0}">
					<p>This group has no posts yet ! Let's change that :) !</p>
				</c:if>
	     		<c:forEach var="post" items="${posts}">
	     			<div class="border rounded border-seconday bg-white post-box" >
	     				<div>
		     				<a id="viewmember" href="userprofile/${post.creator}">
								${post.creatorDisplayName}
							</a>
							<p class="card-text">${post.content}</p>
	     				</div>
					</div>
				</c:forEach>
	    		</div>
    		</div>
      </div>
    </div>
    <div class="col">
      <div class="border rounded border-primary groups-box bg-light">
      	<div class="p-2 text-center bg-secondary text-light">
     		Membership
    		</div>
    		<div class="division">
    			<div>
				<label for="admin-name">Admin</label>
				<br>
				<div id="admin-name" class="border rounded bg-white name-box">
					${admin.userProfile.firstName} ${admin.userProfile.lastName}
				</div>
			</div>
			<div class="break">
				<label for="members">Members</label>
				<div id="members">
					<c:if test="${members.size() == 0}">
						<p>This group has no members</p>
					</c:if>
					<c:if test="${members.size() > 0}">
						<div class="border rounded bg-white name-box">
							<c:forEach var="member" items="${members}">
								<a id="viewmember" href="userprofile/${member.username}">
									${member.userProfile.firstName} ${member.userProfile.lastName}
								</a>
								<c:if test="${isAdmin}">
									<a class="close" aria-label="Close" id="removemember" 
											href="groupdetails/member/${member.username}/remove">
										<span aria-hidden="true">&times;</span>
									</a>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
			<br>
			<c:if test="${isAdmin}">
	      		<div>
	      			<form:form action="groupdetails/member/add" method="post">
	      				<input type="submit" class="btn btn-success btn-block" value="Add Member"/>
	      			</form:form>
	      		</div>
			</c:if>
    		</div>
      </div>
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
