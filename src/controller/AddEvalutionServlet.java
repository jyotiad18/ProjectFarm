package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evaluation;
import model.Users;
import model.Project;
import model.db.EvaluationDB;
import model.db.ProjectDB;
import model.db.UserDB;

@WebServlet("/AddEvalutionServlet")
public class AddEvalutionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			HttpSession session = req.getSession();
			Users u = (Users)session.getAttribute("users");
			int attractiveness = Integer.parseInt((String)req.getParameter("txtattractiveness"));
			int risklevel = Integer.parseInt((String)req.getParameter("txtrisklevel"));
			Project p  =(Project)session.getAttribute("projectdetail");
	
			Evaluation evaluation = new Evaluation(u, attractiveness, risklevel, p);
			
			EvaluationDB.saveEvalution(evaluation);
			
			
			
			session.setAttribute("projectlist", ProjectDB.getAllProjects());
			RequestDispatcher disp = req.getRequestDispatcher("/evalutor.jsp");
			disp.forward(req, res);
			
			//res.sendRedirect("/evalutor.jsp");
			
			
			
			
		}catch(Exception ex){
			
		}
	}

}
