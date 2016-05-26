<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Project"%>
<%@page import="model.Users"%>
<%@page import="java.util.List"%>

<jsp:include page="header1.jsp"></jsp:include>
   	<div class="container">
   	 
   	    <div class="panel panel-primary">
	  	  <div class="panel-heading">Project List</div>
	  		<div class="panel-body">
		   		 <table class="table table-bordered">
							       <tr>
							           <th>Acronym</th>
							           <th>Category</th>
							           <th>#of incubation day</th>
							           <th>Budget</th>
							           <th>#ofevals</th>
							           <th>Action</th>
							       </tr>
							       <tbody id="ptbody">
							       <%
							       		@SuppressWarnings("unchecked")
							       		List<Project> projectlist = (List<Project>)request.getSession().getAttribute("projectlist");
							       	   for(Project p : projectlist){
							       	 %>
							       	 	<tr>
							       	 	  <td><b><a href="/ProjectFarm/GetProjectDetailServlet?st=s&acrony=<%=p.getProjectid() %>" ><%= p.getAcronym() %></a></b></td>
								           <td><%= p.getCategory().getDescription() %></td>
								           <td><%= p.getFundingDuration() %></td>
								           <td><%= Double.parseDouble(new DecimalFormat("##.####").format(p.getBudget())) %></td>
								           <td><%=p.getEvaluations().getStatus() %></td>
								           <td><a href="/ProjectFarm/GetProjectDetailServlet?acrony=<%=p.getProjectid() %>" >Evaluator</a></td>
								   
							       	 	</tr>
							       	<% } %>
							       </tbody>
				</table>
			</div>
		</div>		
		
	</div>

<jsp:include page="footer.jsp"></jsp:include> 

  