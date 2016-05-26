package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Category;
import model.Users;
import model.db.exception.DatabaseAccessError;

public class CategoryDB {
	
	private static Map<String,Category> categories;
	
	static {
		categories = new LinkedHashMap<>();
		initializeCategoryList();
	}
	
	public static List<Category> getCategories() throws DatabaseAccessError {
		return new LinkedList<Category>(categories.values());
	}
	
	public static Category getCategories(int projectid) throws DatabaseAccessError {
		Category c = null;
		try {
			
			PreparedStatement pstcat = Connectivity.dbConnection().prepareStatement("exec category_get_byproject ?");
			pstcat.setInt(1, projectid);
			ResultSet rsc = Connectivity.ExecuteSelect(pstcat);
			while(rsc.next()){
				c = CategoryDB.getCategory(String.valueOf(rsc.getInt("catid")));
			}
		
		}catch(Exception ex){}
		Connectivity.closeConnection();
		return c ;
		
	}
	
	public static Category getCategory(String name) {
		return categories.get(name);
	}

	private static void initializeCategoryList() {
		
		Category u = null ;
		try {
			PreparedStatement pstcat = Connectivity.dbConnection().prepareStatement("exec category_list"); 
			 ResultSet rs = Connectivity.ExecuteSelect(pstcat);
			 if (rs != null) {
				 while(rs.next()){
					u =  new Category(rs.getString("description") , rs.getInt("catid") );
					categories.put(String.valueOf(rs.getInt("catid")), u);
				 }
			  }
			}
		catch(Exception ex){
			  ex.printStackTrace();
		}
		Connectivity.closeConnection();
	
	}

}
