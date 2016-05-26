package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Evaluation;
import model.Project;
import model.Users;
import model.db.exception.DatabaseAccessError;

public class EvaluationDB {

	public static Evaluation getEvalution(int projectid) throws DatabaseAccessError {
		Evaluation e =  new Evaluation();
		try {
			
			PreparedStatement pstcat = Connectivity.dbConnection().prepareStatement("exec evaluation_get_byproject ?");
			pstcat.setInt(1, projectid);
			ResultSet rsc = Connectivity.ExecuteSelect(pstcat);
			while(rsc.next()){
				
				e.setAttractiveness(rsc.getFloat("attractivness"));
				e.setRiskLevel(rsc.getFloat("risklevel"));
				e.setStatus(rsc.getString("numevalutor"));
			}
		
		}catch(Exception ex){}
		Connectivity.closeConnection();
		return e ;
		
	}
	
	public static void saveEvalution(Evaluation evaluation) throws DatabaseAccessError {
		try {
		PreparedStatement pst = Connectivity.dbConnection().prepareStatement("exec evaluation_save ?,?,?,?");
		pst.setInt(1, evaluation.getProject().getProjectid());
		pst.setInt(2, evaluation.getEvaluator().getUserid());
		pst.setFloat(3,evaluation.getAttractiveness());
		pst.setFloat(4, evaluation.getRiskLevel());
		
		Connectivity.dataExecute(pst);
		Connectivity.closeConnection();
		}catch(Exception ex){
			System.out.println(ex);
		}
		
	}
}
