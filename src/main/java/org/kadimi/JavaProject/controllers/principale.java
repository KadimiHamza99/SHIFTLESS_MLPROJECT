package org.kadimi.JavaProject.controllers;

import java.io.IOException;

import org.kadimi.JavaProject.models.EvaluationResults;

public class principale {
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";

	public static void main(String[] args) throws IOException {
	
		EvaluationResults ER,ER1 = null;
		UserController uc = new UserController();
		try {
			ER=uc.predictJ48(arff);
			ER1=uc.predictNB(arff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
