<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Project"%>
<%@page import="model.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
					           <th>Risk</th>
					           <th>Attractives</th>
					           <th>#of Evalutor</th>
					            
					       </tr>
					       <tbody id="ptbody">
					       <%
					       		@SuppressWarnings("unchecked")
					       		List<Project> projectlist = (List<Project>)request.getSession().getAttribute("projectlist");
					       	   for(Project p : projectlist){
					       	 %>
					       	 	<tr>
					       	 	  <td><a href="/ProjectFarm/GetProjectDetailServlet?acrony=<%=p.getProjectid() %>" ><%= p.getAcronym() %></a></td>
						           <td><%= p.getCategory().getDescription() %></td>
						           <td><%= p.getFundingDuration() %></td>
						           <td><%= Double.parseDouble(new DecimalFormat("##.####").format(p.getBudget())) %></td>
						           <%
						           	  float risklevel = p.getEvaluations().getRiskLevel();
						              float attractiveness = p.getEvaluations().getAttractiveness();
						              if (risklevel == attractiveness){
						           %>
						     		   <td style="background-color: yellow"><%=risklevel %></td>
						     		   <td style="background-color: yellow"><%=attractiveness %></td>
						     	<% } else if (risklevel > attractiveness){ %>
						     		   <td style="background-color: red"><%=risklevel %></td>
						     		   <td style="background-color: red"><%=attractiveness %></td>
						     	<% } else { %>
						     		   <td style="background-color: green"><%=risklevel %></td>
						     		   <td style="background-color: yellow"><%=attractiveness %></td>
						     	<% } %>
						           <td><%=(p.getEvaluations().getStatus()==null)?0:p.getEvaluations().getStatus() %></td>
						        
					       	 	</tr>
					       	 <% 
					       	   }
					         %>
					       </tbody>
		</table>
	  </div>
	</div>
   		 
		
	</div>

<jsp:include page="footer.jsp"></jsp:include> 

  