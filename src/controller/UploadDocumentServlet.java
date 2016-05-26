package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Document;
import model.Project;
import model.db.ProjectDB;

@WebServlet("/UploadDocumentServlet")
@MultipartConfig
public class UploadDocumentServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5397595975514196076L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Part filePart = req.getPart("file");
		HttpSession session = req.getSession();
		
		int lindex = filePart.getSubmittedFileName().lastIndexOf("\\") ;
		int leng = filePart.getSubmittedFileName().length();
		
		String filename = filePart.getSubmittedFileName().substring( lindex + 1 , leng);
	
		InputStream in = filePart.getInputStream();
		
		 String appPath = req.getServletContext().getRealPath("");
	     String savePath = appPath + File.separator + "uploadfiles" ;
	     savePath += "\\" + filename;
	        
	        
		FileOutputStream outp = new FileOutputStream(savePath);  //"C:\\document\\"+filename);
		
		byte[] buffer = new byte[req.getContentLength()];
		int len;
		
		while ((len = in.read(buffer)) != -1) {
			outp.write(buffer, 0, len);
		}
		
		in.close();
		outp.close();
		
			try {
				Project p = (Project)session.getAttribute("projectdetail");//ProjectDB.projects.get((String)session.getAttribute("acronmy"));
		    	List<Document> dList = new ArrayList<Document>();
		        dList = p.getDocuments();
		    	Document d = new Document(savePath , 0);//(filename,0);
		        dList.add(d);
		    	p.setDocuments(dList);
		    	session.setAttribute("projectdetail", p);
		    	RequestDispatcher disp = req.getRequestDispatcher("/ProjectDetails.jsp");
		    	disp.forward(req, res);
			} catch(Exception ex){ 
				System.out.println(ex);
			}
	}
}
