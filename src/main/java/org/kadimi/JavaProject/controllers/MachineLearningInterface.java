package org.kadimi.JavaProject.controllers;

import org.kadimi.JavaProject.models.EvaluationResults;

public interface MachineLearningInterface {
	
	EvaluationResults predictRequirementsJ48(String i);
	EvaluationResults predictRequirementsNB(String i);
	EvaluationResults predictRequirementsIBk(String i);
	EvaluationResults predictRequirementsAdaBoostM1(String i);
	EvaluationResults predictRequirementsPART(String i);
	
	void afficherTabConsole(String r,String p);
}
