package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class Connectivity {
	

	static Connection dbConnection()
    {
   	 	try {
   	 		
   	 		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   	 		 String ConnectionUrl = "jdbc:sqlserver://localhost;database=ProjectFarm;user=sa;password=sa123";
   	 		 Connection conn = DriverManager.getConnection(ConnectionUrl);
   	 		 return conn;
   	 		
   	 	}
   	 	catch(Exception ex){
   	 		ex.printStackTrace();
   	 	}
   	 	
   	 	return null;
    }
	
	static void closeConnection(){
		try {
		dbConnection().close();
		} 
		catch(Exception ex) {}
	}
	
	public static int dataExecute(PreparedStatement pst)
    {
   	 try {
   		 return pst.executeUpdate();
   	 }
   	 catch(Exception ex){
   		 ex.printStackTrace();
   	 }
   	 return 0 ;
   	 
    }
	
	public static ResultSet ExecuteSelect(PreparedStatement pst)
    {
   	 try {
   		 
   		 ResultSet rs =  pst.executeQuery();
   		 return rs;
   	 }
   	 catch(Exception ex){
   		 ex.printStackTrace();
   	 }
   	 return null;
   	 
    }
	


}
