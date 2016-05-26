package controller;



import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Document;
import model.Project;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.DocumentDB;
import sun.security.pkcs11.Secmod.DbMode;


@WebServlet("/AddDocumentServlet")
//@MultipartConfig
public class AddDocumentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		{
		try {
			
			HttpSession session = req.getSession();
			
			Project p = (Project)session.getAttribute("projectdetail");
		    
			for(Document d : p.getDocuments())
			{
				if (d.getDocid() <= 0 )
				{
					DocumentDB.saveDocument(p.getProjectid(), d.getDocumentPath());
				}
			}
			
			res.sendRedirect("/ProjectFarm/MyProject.jsp");
	
		}
		catch(Exception ex) {
			
				System.out.println(ex.toString());
			
		}
	 }
	}
	
}
