<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Outbox</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>

<br>

<div class="col-lg-8">
      <div class="border rounded border-primary groups-box bg-light">
      	<div class="p-2 text-center bg-secondary text-light">
     		<b>Outbox</b>
    		</div>
    		<div class="row p-2">
	    		<div class="col-lg-2 text-left" >
	    		<b>To:</b>
	    		</div>
	    		<div class="col-lg-10 text-left">
	    		<b>Subject:</b>
	    		</div>
	     		<c:if test="${inbox.size() == 0}">
					<p>No messages!</p>
				</c:if>
	     		<c:forEach var="msg" items="${outbox}">
	     			<div class="col-lg-2" >
								${msg.recipient}
					</div>
	     			<div class="col-lg-10" >
		     				<a id="viewmember" href="message/${msg.id}">
								${msg.subject}
							</a>
					</div>
				</c:forEach>
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