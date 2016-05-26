<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Fram</title>
<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="/ProjectFarm/ext/bootstrap/3.2.2/css/bootstrap.min.css">
		<script src="/ProjectFarm/ext/jquery/1.11.2/jquery-1.11.2.js"></script>
		<script src="/ProjectFarm/ext/bootstrap/3.2.2/js/bootstrap.min.js"></script>
		
		
	
</head>
<body>
	  
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/JEECourse/index.jsp">Project Farm</a>
    </div>
	
    <div class="inline pull-right" >
      	<div class="navbar-form form-inline" id="divlogin">
      	 <form method="post" action="/ProjectFarm/LoginServlet">
    		<input type="email" class="form-control" placeholder="email" name="txtusername" id="txtusername" value="sarah@geek.com" required>
    		<input type="password" class="form-control" placeholder="Password" name="txtpassword" id="txtpassword" required>
    		<input type ="submit" class="btn" id="btnsubmit" value="LogIn">
    		<% 
    		String msg = (String)request.getAttribute("error");
    		if (msg != null){
    		%>
    	    
    		<span class="alert alert-warning" role="alert""><%=msg %></span>
    		
    	<% } %>
    	</form>	
    	
       </div>
       
      
		
	</div>	    
  </div>
</nav>
