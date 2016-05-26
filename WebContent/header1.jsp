<%@page import="model.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Farm(ESIGELEC)</title>
<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="/ProjectFarm/ext/bootstrap/3.2.2/css/bootstrap.min.css">
		<script src="/ProjectFarm/ext/jquery/1.11.2/jquery-1.11.2.js"></script>
		<script src="/ProjectFarm/ext/bootstrap/3.2.2/js/bootstrap.min.js"></script>
		
   <% response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0); %>
	
</head>
<body>
 			 <%
 			    
 			    Object o = request.getSession().getAttribute("users");
 			    Users u = new Users();
 			    	 u = (Users)o;
 			    if ( u == null )
 			    	response.sendRedirect("/ProjectFarm/index.jsp");
			  %>
			  	
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/JEECourse/index.jsp">Project Farm</a>
    </div>
	
    <div class="inline pull-right" >
       <div class="dropdown" id="divlogout" style="margin-top: 5px">
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			  <span class="glyphicon glyphicon-user" aria-hidden="true"></span> <%=u.getName()%>
		    </button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			     <% if (u.getUserType() == 0 ) { %>
			     <li><a href="/ProjectFarm/CategoryServlet" >Add Idea</a></li>
				 <li><a href="/ProjectFarm/MyProjectListServlet" >My Project</a></li>
				 <% } else {  %>
				 <li><a href="/ProjectFarm/evalutor.jsp" >ProjectList</a></li>
				  <% }%>
				 <li><a href="/ProjectFarm/LogoutServlet">Logout</a></li>
			 </ul>
		</div>
		
	</div>	    
  </div>
</nav>
