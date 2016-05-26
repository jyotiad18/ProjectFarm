package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Category;
import model.Document;
import model.Project;
import model.db.CategoryDB;
import model.db.ProjectDB;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		
			 HttpSession session = req.getSession();
			 @SuppressWarnings("unchecked")
			 List<Category> plist = CategoryDB.getCategories();
			 session.setAttribute("categorys", plist);
			  RequestDispatcher disp  = req.getRequestDispatcher("/AddProjectIdea.jsp");
			  disp.forward(req, res);
			
		}
		catch(Exception ex){}
	}
	
	
	
}
