package org.kadimi.JavaProject.controllers;

import java.io.IOException;

import org.kadimi.JavaProject.models.EvaluationResults;

public class principale {
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";

	public static void main(String[] args) throws IOException {
	
		EvaluationResults j48,nb,ibk,adaboostm1,part = null;
		UserController uc = new UserController();
		try {
			j48=uc.predictJ48(arff);

//			nb=uc.predictNB(arff);

//			ibk=uc.predictIBk(arff);

//			adaboostm1=uc.predictAdaBoostM1(arff);

//			part=uc.predictPART(arff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
