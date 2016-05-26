package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Document;
import model.Project;
import model.Users;
import model.db.ProjectDB;

@WebServlet("/GetProjectDetailServlet")
public class GetProjectDetailServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1462613850348264837L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
		 String acrnmy = req.getParameter("acrony").toString();
		 
		 HttpSession session = req.getSession();
		 
		 Project p =ProjectDB.getProject(Integer.parseInt(acrnmy));
		 session.setAttribute("projectdetail", p);
		 RequestDispatcher disp = req.getRequestDispatcher("/ProjectDetails.jsp");
		 disp.forward(req, res);
		 
		} catch(Exception ex){
			 System.out.println(ex);
		}
	}
}
