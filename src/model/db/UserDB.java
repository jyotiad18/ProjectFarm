package model.db;


import model.Users;
import model.db.exception.DatabaseAccessError;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDB {
	
	

	public static Users checkLogin(String email,String password) throws DatabaseAccessError{
		
		Users u = null ;
		try {
			
			 PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec checklogin ?,?");
			 pst.setString(1, email);
			 pst.setString(2, password);
			 ResultSet rs = Connectivity.ExecuteSelect(pst);
			 if (rs != null) {
				 while(rs.next()){
					 
					u =  new Users(rs.getString("email"),
								   rs.getString("name"),
								   rs.getInt("userid"),
								   rs.getInt("usertype"));
				 }
				 return u ; 
	
			  }
			}
		catch(Exception ex){
			  ex.printStackTrace();
			}
		return u ;
		
	}
	
	
public static Users getUser(int projectid) throws DatabaseAccessError{
		
		Users u = null ;
		try {
			PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec user_get_byproject ?");
			pst.setInt(1, projectid);
			ResultSet rsc = Connectivity.ExecuteSelect(pst);
			while(rsc.next()){
				u = new Users(rsc.getString("email"), rsc.getString("name"), rsc.getInt("userid"), rsc.getInt("usertype"));
			}
		    
			Connectivity.closeConnection();
			return u ;
		
		}catch(Exception ex){
			
			Connectivity.closeConnection();
		}
		
		return u ;
	}




	
	
	
	
	/*
	public static User getUser(String login) throws DatabaseAccessError {
		User u = getOwner(login);
		if(u == null) {
			u = getEvaluator(login);
		}
		return u;
	}
	
	
	public static Owner getOwner(String login) throws DatabaseAccessError{
		User u = users.get(login);
		if(u == null || !(u instanceof Owner))
			return null;
		return (Owner) u;
	}
	
	public static Evaluator getEvaluator(String login) throws DatabaseAccessError {
		User u = users.get(login);
		if(u == null || !(u instanceof Evaluator))
			return null;
		return (Evaluator) u;		
	}
	*/
}
