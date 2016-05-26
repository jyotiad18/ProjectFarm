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
import model.db.ProjectDB;

@WebServlet("/GetAllProjectDetailServlet")
public class GetAllProjectDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2724779756850548472L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	  try {
		     HttpSession session = req.getSession();
		     
		     @SuppressWarnings("unchecked")
		     List<Project> pList = ProjectDB.getAllProjects() ;
		     session.setAttribute("projectlist", pList);
			 RequestDispatcher disp = req.getRequestDispatcher("/evalutor.jsp");
			 disp.forward(req, res);
		   
		   
	  }catch(Exception ex){
		  
	  }
		
	}
}
