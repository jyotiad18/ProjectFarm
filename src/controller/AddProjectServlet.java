package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.Project;
import model.Users;
import model.db.CategoryDB;
import model.db.ProjectDB;

@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			HttpSession session = req.getSession();
			
			
			
			String titles = req.getParameter("titles");
			String description = req.getParameter("desc");
			String categoryname = req.getParameter("category");
			int incubtion = Integer.parseInt(req.getParameter("incube"));
			float budget = Float.parseFloat(req.getParameter("budget"));
		
			
			Category category = CategoryDB.getCategory(categoryname);
			Users owner = (Users)session.getAttribute("users");
	
			Project project = new Project(titles,description,incubtion, budget, owner, category,0);
			ProjectDB.saveProject(project);
			
			 
			  @SuppressWarnings("unchecked")
			  List<Project> plist = ProjectDB.getProjectsOfOwner(owner);
			  
			  session.setAttribute("projectlist", plist);
			  RequestDispatcher disp  = req.getRequestDispatcher("/MyProject.jsp");
			  disp.forward(req, res);
	
		
		} catch(Exception ex){
			System.out.println(ex);
		}
	}
	
}
