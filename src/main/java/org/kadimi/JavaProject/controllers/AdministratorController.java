package org.kadimi.JavaProject.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.kadimi.JavaProject.models.EvaluationResults;
import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

public class AdministratorController extends UserController implements AdministratorInterface{
	
	private static final String CSVFile = "C:\\JAVAEE\\JavaProject\\ressources\\OffersCSV.csv";
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers.arff";

	WebScrapingController wsc = new WebScrapingController();
	
	public AdministratorController() {
		super();
	}
	
	public List<User> getUsers() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			Query query=this.em.createQuery("SELECT u FROM User u");
			transaction.commit();
			return query.getResultList();
		}catch(Exception e) {
			transaction.rollback();
			System.out.println("Erreur Fetching Users");
			e.getStackTrace();
			return null;
		}
	}	
	
	public List<Offer> getOffers() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			Query query=this.em.createQuery("SELECT o FROM Offer o");
			transaction.commit();
			return query.getResultList();
		}catch(Exception e) {
			transaction.rollback();
			System.out.println("Erreur Fetching Offers");
			e.getStackTrace();
			return null;
		}
	}
	
	public void removeUser(int id) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			User u = this.em.find(User.class, id);
			this.em.remove(u);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			System.out.println("Erreur delete user");
		}
	}
	
	public void removeAllUsers() {
		EntityTransaction transaction = em.getTransaction();
		try {
			List<User> UsersList = this.getUsers();
			for (User user : UsersList) {
				transaction.begin();
				user = em.find(User.class, user.getId());
				em.remove(user);
				transaction.commit();
			}
		}catch(Exception e) {
			transaction.rollback();
			System.out.println("Removing users failed");
			throw e;
		}
	}
	
	public void removeAllOffers() {
		EntityTransaction transaction = em.getTransaction();
		try {
			List<Offer> offersList = this.getOffers();
			for (Offer offer : offersList) {
				transaction.begin();
				offer = em.find(Offer.class, offer.getIdOffer());
				em.remove(offer);
				transaction.commit();
			}
		}catch(Exception e) {
			transaction.rollback();
			System.out.println("Removing Offers failed");
			throw e;
		}
	}
	
	public List<Offer> javaSoup() throws IOException {
		this.removeAllOffers();
		List<Offer> offers = wsc.extractOffers();
		EntityTransaction transaction = em.getTransaction();
		for (Offer offer : offers) {
			if(offer.getContract() == null) {
				continue;
			}else {
				transaction.begin();
				try {
					this.em.persist(offer);
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Error to add user");
					transaction.rollback();
					throw e;
				}
			}
		}
		List<Offer> O = this.getOffers();
		
		this.writeCSVFile(CSVFile, O);
		try {
			this.CSVToARFF(CSVFile, arff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return O;
	}
	public EvaluationResults predictJ48(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsJ48(arff_test);
		return reqPred;
	}
	public EvaluationResults predictNB(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsNB(arff_test);
		return reqPred;
	}
	public EvaluationResults predictIBk(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsIBk(arff_test);
		return reqPred;
	}
	public EvaluationResults predictAdaBoostM1(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsAdaBoostM1(arff_test);
		return reqPred;
	}
	public EvaluationResults predictPART(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsPART(arff_test);
		return reqPred;
	}
}
