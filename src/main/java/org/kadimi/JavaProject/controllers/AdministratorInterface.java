package org.kadimi.JavaProject.controllers;

import java.io.IOException;
import java.util.List;

import org.kadimi.JavaProject.models.EvaluationResults;
import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

public interface AdministratorInterface {
	
		//user Methods
		void addUser(String competences);
		void writeCSVFile(String filePath , List<Offer> offers) throws IOException ;
		void CSVToARFF(String inputCSV, String OutputARFF) throws Exception ;
		
		//admin methods
		List<User> getUsers();
		List<Offer> getOffers();
		void removeUser(int id);
		void removeAllUsers();
		void removeAllOffers();
		List<Offer> javaSoup() throws IOException;
		EvaluationResults predictJ48(String arff_test) throws Exception;
		EvaluationResults predictNB(String arff_test) throws Exception;
		EvaluationResults predictIBk(String arff_test) throws Exception;
		EvaluationResults predictAdaBoostM1(String arff_test) throws Exception;
		EvaluationResults predictPART(String arff_test) throws Exception;
	
}
