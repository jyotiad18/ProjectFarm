<%@page import="java.text.DecimalFormat"%>
<%@page import="model.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Project"%>   
<%@page import="model.Users"%>  
<%@page import="java.util.Set"%>
<%@page import ="java.util.LinkedHashSet" %>


<jsp:include page="header1.jsp"></jsp:include>

   <% 
      Users u = (Users)request.getSession().getAttribute("users") ;
   	  Project p = (Project)request.getSession().getAttribute("projectdetail"); 
   %>
   	<div class="container">
   	
   	    <div class="panel panel-primary">
   	      <div class="panel-heading">Project Detail - <%=p.getAcronym() %></div>
   	        <div class="panel-body">
   	           <fieldset>
	   	         	   	 <legend>Project Evaluation</legend>
			   	             <div class="panel-body">    
		   	             		   Acronym : <%=p.getAcronym() %>
		   	            	 	  <p>Description : <%=p.getDescription() %></p>
		   	            	      <span>Category : <%=p.getCategory().getDescription() %> </span>
		          			      <span style="margin-left: 50px">IncubationDay : <%=p.getFundingDuration() %> </span>
		           			      <span style="margin-left: 50px">Budget : <%= Double.parseDouble(new DecimalFormat("##.####").format(p.getBudget())) %></span>
		   	                 </div>
	   	         </fieldset>
	   	         <fieldset>
	   	              <legend>Documents</legend>
	   	              <div class="row" style="margin-bottom: 10px">
	   	              <div class="col-md-4">
		   	             		 	  <% if (p.getDocuments() != null || p.getDocuments().size() > 0) {%>
						            	<ul class="list-group">
												<%
													for(Document d : p.getDocuments()) {
												%>
								
												<li class="list-group-item">
													<%= d.getDocumentname() %>
												</li>		
												<%
													}
												%>
											</ul>
						            <% } %>
		   	             		 </div>
		   	             		 
		   	             		 <div class="col-md-8">
		   	             		 	 <% if (u.getUserType() == 0) { %>
							            <form enctype="multipart/form-data" class="form form-inline"
						        				 method="post" action="/ProjectFarm/UploadDocumentServlet"> 
								    	<input type="file" name="file" class="btn btn-default form-control" />
								    	<input type="submit" value="Upload" class="btn btn-default form-control" id="btnupload1"/> 
										</form>
									<% } %>	
		   	             		 </div>
		   	             </div>		 
	   	         </fieldset>
	   	         <fieldset>
	   	         <legend></legend>
	   	          
	   	              <% 
	   	                String st = request.getParameter("st")== null ? "n" : request.getParameter("st").toString();
	   	                if (u.getUserType() == 0 || (u.getUserType() == 1 && st.equals("s"))) { 
	   	              %>
				       <div class="row">
					        <div class="col-md-4">
					            RiskLevel : <%=p.getEvaluations().getRiskLevel() %>
					        </div>
					        <div class="col-md-4">
					            Attractiveness : <%=p.getEvaluations().getAttractiveness() %>
					        </div>
					        <div class="col-md-4">
					            # Of Evaluators : <%=(p.getEvaluations().getStatus()==null)?0:p.getEvaluations().getStatus() %>
					                    
					        </div>
				       </div>
				       
				       <div class="row">
				        <div class="col-md-12 form-inline">
				            <form method="post" action="/ProjectFarm/AddDocumentServlet">
				        	    <button class="btn btn-default" <% if(u.getUserType()== 1) {%> disabled="disabled" <%} %> >Save</button>
				        	      <button class="btn btn-default" style="margin-left: 5px"
				        	      <% if(u.getUserType()== 1) {%>  onclick="location.href = '/ProjectFarm/evalutor.jsp';"  <%} else { %>
				        	          onclick="location.href = '/ProjectFarm/MyProjectListServlet';"  <% } %>
				        	      > Discard</button> 
							</form>	 
							      
				        </div>
				      </div>
				       
			       <% } else if (u.getUserType() == 1 && st.equals("n")) { %>
				       <div class="row">
				        <div class="col-md-12 form-inline">
				           <form method="post" action="/ProjectFarm/AddEvalutionServlet">
				                 RiskLevel : <select name="txtrisklevel" class="form-control" style="width: 200px">
					                              <option value="1" selected>1</option>
					                              <option value="2">2</option>
					                              <option value="3">3</option>
					                              <option value="4">4</option>
					                              <option value="5">5</option>
				                 			 </select> 
				                 Attractiveness :<select name="txtattractiveness" class="form-control" style="width: 200px">
						                              <option value="1" selected>1</option>
						                              <option value="2">2</option>
						                              <option value="3">3</option>
						                              <option value="4">4</option>
						                              <option value="5">5</option>
				                 			 </select> 
				                  
				                 <p></p>
				        	    <button class="btn btn-default">Save</button>
				        	    <button class="btn btn-default" style="margin-left: 5px"
				        	    onclick="location.href = '/ProjectFarm/evalutor.jsp';"
				        	    >Discard</button> 
							</form>	
							      
				        </div>
				      </div>
			       <% } %>		 
	   	         </fieldset>
   	         </div>
   	     
   	    </div>
   		  
	       
	       
	       
	</div>
<jsp:include page="footer.jsp"></jsp:include> 

