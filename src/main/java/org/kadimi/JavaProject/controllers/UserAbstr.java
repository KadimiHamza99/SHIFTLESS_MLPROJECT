package org.kadimi.JavaProject.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

import com.opencsv.CSVWriter;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public abstract class UserAbstr {
	protected EntityManager em;
	
	public void addUser(String competences) {
		User user = new User(competences);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			this.em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error to add user");
			transaction.rollback();
			throw e;
		}
	}
	
	public void writeCSVFile(String filePath , List<Offer> offers) throws IOException {

		  CSVWriter writer = new CSVWriter(new FileWriter(filePath));
	      //Writing data to a csv file
	      String header[] = {"IdOffer", "title", "name", "location", "level" , "req" ,"contract" , "link"};
	      writer.writeNext(header);
	      //Writing data to the csv file
	      for (Offer o : offers) {
	    	  if(o.getReq()=="") continue;
	    	  String line[] = {Long.toString(o.getIdOffer()),o.getTitle(),o.getName(),o.getLocation(),o.getLevel(),o.getReq(),o.getContract(),o.getLink() };
	    	  writer.writeNext(line);
	      }
	      //Flushing data from writer to file
	      writer.flush();
	      System.out.println("Write Data into data base");
		
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
	
	
	//Matching Method
	public static double pecentageOfMatch(String s1, String s2) {
		int somme = 0;
		List<String> techs = Arrays.asList(s1.split(" , "));
		List<String> reqs = Arrays.asList(s2.split(" , "));
		System.out.println(techs.toString());
		System.out.println(reqs.toString());
		for (String tech : techs) {
			if(reqs.contains(tech)) {
				somme+=1;
				System.out.println(tech+" "+somme);
			}
		}
		return (double) somme/reqs.size();	
	}
}
