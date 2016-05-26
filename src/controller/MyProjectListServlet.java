package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Project;
import model.Users;
import model.db.ProjectDB;



@WebServlet("/MyProjectListServlet")
public class MyProjectListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		 HttpSession session = req.getSession();
		
		 Users owner = (Users)session.getAttribute("users");
		
		  @SuppressWarnings("unchecked")
		  List<Project> plist = ProjectDB.getProjectsOfOwner(owner);
		  session.setAttribute("projectlist", plist);
		  RequestDispatcher disp  = req.getRequestDispatcher("/MyProject.jsp");
		  disp.forward(req, res);
		 
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}


	
}
