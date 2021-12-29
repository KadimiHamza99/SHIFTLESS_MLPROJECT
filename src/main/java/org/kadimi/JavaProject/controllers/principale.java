package org.kadimi.JavaProject.controllers;

import java.io.IOException;

import org.kadimi.JavaProject.models.EvaluationResults;

public class principale {
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";

	public static void main(String[] args) throws IOException {
	
		EvaluationResults j48,nb,ibk,adaboostm1,part = null;
		UserController uc = new UserController();
		try {
			System.out.println("J48 test");
			j48=uc.predictJ48(arff);
			System.out.println("Naive Bayes test");
			nb=uc.predictNB(arff);
			System.out.println("IBk test");
			ibk=uc.predictIBk(arff);
			System.out.println("AdaBoostM1 test");
			adaboostm1=uc.predictAdaBoostM1(arff);
			System.out.println("logitboost test");
			part=uc.predictPART(arff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
