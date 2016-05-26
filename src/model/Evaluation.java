package model;

import java.io.Serializable;

import model.exception.InvalidDataException;

public class Evaluation implements Serializable {

	private static final long serialVersionUID = -2917944580171297941L;

	private Project project;
	private Users evaluator;
	private float attractiveness;
	private float riskLevel;
	private String status;

	
	public Evaluation(){}
	public Evaluation(Users evaluator, float attractiveness,
			float riskLevel , Project project) throws InvalidDataException {
		setEvaluator(evaluator);
		setAttractiveness(attractiveness);
		setRiskLevel(riskLevel);
		setProject(project);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Users getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Users evaluator) throws InvalidDataException {
		if(evaluator == null) {
			throw new InvalidDataException("Evaluator must be specified");
		}
		this.evaluator = evaluator;
	}

	public float getAttractiveness() {
		return attractiveness;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void setAttractiveness(float attractiveness) throws InvalidDataException {
		if(attractiveness < 1 || attractiveness > 5) {
			throw new InvalidDataException("Attractiveness must range between 1 and 5");
		}		
		this.attractiveness = attractiveness;
	}

	public float getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(float riskLevel) throws InvalidDataException {
		if(riskLevel < 1 || riskLevel > 5) {
			throw new InvalidDataException("Risk level must range between 1 and 5");
		}		
		this.riskLevel = riskLevel;
	}

}
