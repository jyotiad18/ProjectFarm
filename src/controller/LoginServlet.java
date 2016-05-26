package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import model.db.UserDB;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stu
		
		
		try {
			
				HttpSession session = req.getSession(true);
				
			    String username = req.getParameter("txtusername");
				String password = req.getParameter("txtpassword");
				
				Users u = UserDB.checkLogin(username,password);
				RequestDispatcher disp;
				if (u != null){
					session.setAttribute("users", u);
					if(u.getUserType()== 0)
					     disp = req.getRequestDispatcher("/owner.jsp");		
					else 
						disp = req.getRequestDispatcher("/GetAllProjectDetailServlet");
						
					
				} 
				else {
					req.setAttribute("error", "Username & Password Invalid !!!");
					disp = req.getRequestDispatcher("/index.jsp");
				}
				disp.forward(req, res);
			
					
					
		
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
