<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Category"%>    
<%@page import="java.util.*"%>


<jsp:include page="header1.jsp"></jsp:include>
    	 <div class="container">
    	 
    	  <div class="panel panel-primary">
    	    <div class="panel-heading">Project Idea</div>
    	    <div class="panel-body">
    	    	<form method="POST" action="/ProjectFarm/AddProjectServlet" >
							 <div class="form-horizontal">
							  <div class="form-group" style="margin-left:20px">
							    <label>Title</label>
							    <input type="text" class="form-control" placeholder="Title" 
							    	id='txtitle' name="titles" required style="width: 500px">
							  </div>
							   <div class="form-group" style="margin-left:20px">
							    <label>Description</label>
							    <textarea class="form-control" rows="3" id="txtdesc" name="desc" style="width:500px"></textarea>
							  </div>
						  </div>
						  <div class="form-inline">
							  <div class="form-group" style="margin-left:20px">
							    <label>Category</label>
							    <select id="selcategory" class="form-control" name="category">
							     <%
					       			@SuppressWarnings("unchecked")
					       			List<Category> cList = (List<Category>)request.getSession().getAttribute("categorys");
					       	  	 	for(Category p : cList){
					       	 	%>
							    <option value=<%=p.getCatid() %>> <%=p.getDescription() %>  </option> 
							    <% } %>
							    </select>
							  </div>
							   <div class="form-group" style="margin-left:20px">
							    <label>#diacubtion</label>
							     <input type="number" class="form-control" placeholder="IncubationDay" id="txtincube" name="incube" required>
							  </div>
							  <div class="form-group" style="margin-left:20px">
							    <label>Budget(Euro)</label>
							    <input type="number" class="form-control" 
							    	   placeholder="Budget" id="txtbudget" name="budget"
							    	   pattern="[0-9]+([\.,][0-9]+)?" step="0.01"
							    	   required>
							  </div>
						  </div>
						  <div class="form-inline" style="margin-top: 10px;margin-left: 20px">
							  <input type="submit" class="btn btn-default" id="btnsave" value="Save" />
							  <button class="btn btn-default" id="btndiscard" onclick="location.href = '/ProjectFarm/MyProjectListServlet';">Discard</button>
						  </div>
						 
					
					</form>
    	    </div>
    	  </div>				
		</div>
     <jsp:include page="footer.jsp"></jsp:include> 
    