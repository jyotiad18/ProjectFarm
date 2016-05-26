package model.db;

import java.security.acl.Permission;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;


import model.Project;
import model.Users;
import model.Category;
import model.Document;
import model.Evaluation;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {

	public static Map<String, Project> projects;

	static {
		projects = new LinkedHashMap<String, Project>();
	}
	
	public static void intializedproject(Project p)
	{
		projects.put(String.valueOf(p.getProjectid()), p);
	}

	public static void saveProject(Project project) throws DatabaseAccessError {
		//projects.put(project.getAcronym(), project);
		try {
		PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec project_save ?,?,?,?,?,?");
		pst.setString(1, project.getAcronym());
		pst.setString(2, project.getDescription());
		pst.setInt(3,project.getFundingDuration());
		pst.setDouble(4, project.getBudget());
		pst.setInt(5, project.getOwner().getUserid());
		pst.setInt(6, project.getCategory().getCatid());
		
		Connectivity.dataExecute(pst);
		}catch(Exception ex){
			
		}
		
	}

	public static Project getProject(int projectid) throws DatabaseAccessError {
		Project p = null ; 
		p = projects.get(String.valueOf(projectid));
		return p;		
	}

	public static List<Project> getProjectsOfOwner(Users owner) throws DatabaseAccessError {

		List<Project> projectsOfOwner = new LinkedList<Project>();
		projects.clear();
		try {
	    PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec project_get_byuser ?");
		pst.setInt(1, owner.getUserid());
		ResultSet rs = Connectivity.ExecuteSelect(pst);
		Connectivity.closeConnection();
		while(rs.next()){
			
			Category c = CategoryDB.getCategories(rs.getInt("projectid"));
			Project p = new Project(rs.getString("acronym"), rs.getString("description"),
					rs.getInt("fundingduration"), rs.getDouble("budget"), owner, c ,rs.getInt("projectid"));
			
			p.setDocuments(DocumentDB.getDocument(p.getProjectid()));
			
			Evaluation e = EvaluationDB.getEvalution(rs.getInt("projectid"));
			p.setEvaluations(e);
			
			projectsOfOwner.add(p);
			intializedproject(p);
			
			Connectivity.closeConnection();
			}
		}
		catch (Exception ex) {
			
		}
		return projectsOfOwner;

	}
	
	public static List<Project> getAllProjects() throws DatabaseAccessError {
		
		List<Project> projectsOfOwner = new LinkedList<Project>();
		projects.clear();
		try {
	    PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec project_list");
		ResultSet rs = Connectivity.ExecuteSelect(pst);
		Connectivity.closeConnection();
		while(rs.next()){
			
			Category c = CategoryDB.getCategories(rs.getInt("projectid"));
			Users owner = UserDB.getUser(rs.getInt("projectid"));
			Project p = new Project(rs.getString("acronym"), rs.getString("description"),
					rs.getInt("fundingduration"), rs.getDouble("budget"), owner, c ,rs.getInt("projectid"));
			
			p.setDocuments(DocumentDB.getDocument(p.getProjectid()));
			
			Evaluation e = EvaluationDB.getEvalution(rs.getInt("projectid"));
			p.setEvaluations(e);
			
			projectsOfOwner.add(p);
			intializedproject(p);
			Connectivity.closeConnection();
			}
		}
		catch (Exception ex) {
			
		}
		return projectsOfOwner;
		
	}
	
	
	
	
}
