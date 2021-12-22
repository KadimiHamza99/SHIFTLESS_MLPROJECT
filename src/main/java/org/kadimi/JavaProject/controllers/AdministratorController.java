package org.kadimi.JavaProject.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

import com.opencsv.CSVWriter;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class AdministratorController extends UserController {
	
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
	public void writeCSVFile(String filePath , List<Offer> offers) throws IOException {

		  CSVWriter writer = new CSVWriter(new FileWriter(filePath));
	      //Writing data to a csv file
	      String header[] = {"IdOffer", "title", "name", "location", "level" , "req" ,"contract" , "link"};
	      writer.writeNext(header);
	      //Writing data to the csv file
	      for (Offer o : offers) {
	    	  String line[] = {Long.toString(o.getIdOffer()),o.getTitle(),o.getName(),o.getLocation(),o.getLevel(),o.getReq(),o.getContract(),o.getLink() };
	    	  writer.writeNext(line);
	      }
	      //Flushing data from writer to file
	      writer.flush();
	      System.out.println("Data entered");
		
	}
	public void CSVToARFF(String inputCSV, String OutputARFF) throws Exception {
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(inputCSV));
		Instances data = loader.getDataSet();

		String[] opts = new String[] { "-R", "1" };
		Remove remove = new Remove();
		remove.setOptions(opts);
		remove.setInputFormat(data);

		Instances newData = Filter.useFilter(data, remove);

		// save ARFF
		ArffSaver saver = new ArffSaver();
		saver.setInstances(newData);
		saver.setFile(new File(OutputARFF));
		saver.writeBatch();
	}
	
}
