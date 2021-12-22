package org.kadimi.JavaProject.controllers;

import java.io.IOException;
import java.util.ArrayList;

public class principale {
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";

	public static void main(String[] args) throws IOException {
		UserController uc = new UserController();
		try {
			ArrayList<ArrayList<String>> Predictions = uc.predict(arff);
			for (ArrayList<String> array : Predictions) {
				for (String str : array) {
					System.out.println(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
