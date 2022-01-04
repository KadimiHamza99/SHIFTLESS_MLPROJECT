package org.kadimi.JavaProject.controllers;

import java.io.IOException;

public class principale {
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";

	public static void main(String[] args) throws IOException {
	
		AdministratorController uc = new AdministratorController();
		try {
			uc.predictJ48(arff);

//			uc.predictNB(arff);

//			uc.predictIBk(arff);

//			uc.predictAdaBoostM1(arff);

//			uc.predictPART(arff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
