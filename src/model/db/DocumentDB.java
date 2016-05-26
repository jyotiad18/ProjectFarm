package model.db;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.Category;
import model.Document;
import model.Project;
import model.Users;
import model.db.exception.DatabaseAccessError;

public class DocumentDB {
	
	public static int saveDocument(int projectid , String documentpath) throws DatabaseAccessError {
		 int result = 0 ;
		 try {
			  PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec document_save ?,?");
			  pst.setInt(1, projectid);
			 // Path p = Paths.get(documentpath);
			  pst.setString(2, documentpath);// p.getFileName().toString());
			  
			  result = Connectivity.dataExecute(pst);
		 } catch(Exception ex) {}
		 
		 Connectivity.closeConnection();
		 return result;
	}
	
	public static List<Document> getDocument(int projectid) throws DatabaseAccessError {

		List<Document> dList = new LinkedList<Document>() ;
		try {
			PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec document_get_byproject ?");
			pst.setInt(1, projectid);
			ResultSet rscdoc = Connectivity.ExecuteSelect(pst);
	
			while(rscdoc.next()){
				Document d = new Document(rscdoc.getString("documentPath"),rscdoc.getInt("docid"));
				dList.add(d);
			}
			Connectivity.closeConnection();
		}catch(Exception ex){
			return null;
		}
		
		return dList ;
		
	}
	

}
