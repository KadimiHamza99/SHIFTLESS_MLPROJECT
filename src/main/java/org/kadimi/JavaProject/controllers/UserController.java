package org.kadimi.JavaProject.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.kadimi.JavaProject.models.EvaluationResults;
import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

import com.opencsv.CSVWriter;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class UserController {

	protected EntityManager em;

	public UserController() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("API");
		em = emf.createEntityManager();
	}

	public void addUser(String competences) {
//		String tokens[] = competences.split(",");
//		List<String> listeComptences = Arrays.asList(tokens);
//		User user = new User(competences,liste);
//		System.out.println(user.getCompetencesList().toString());
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
	public EvaluationResults predictJ48(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsJ48(arff_test);
//		EvaluationResults levPred = MachineLearningController.predictLevelNB(arff_test);
//		EvaluationResults conPred = MachineLearningController.predictContractsNB(arff_test);
		return reqPred;
	}
	public EvaluationResults predictNB(String arff_test) throws Exception {
		EvaluationResults reqPred = MachineLearningController.predictRequirementsNB(arff_test);
//		EvaluationResults levPred = MachineLearningController.predictLevelNB(arff_test);
//		EvaluationResults conPred = MachineLearningController.predictContractsNB(arff_test);
		return reqPred;
	}
	public void writeCSVFile(String filePath , List<Offer> offers) throws IOException {

		  CSVWriter writer = new CSVWriter(new FileWriter(filePath));
	      //Writing data to a csv file
	      String header[] = {"IdOffer", "title", "name", "location", "level" , "req" ,"contract" , "link"};
	      writer.writeNext(header);
	      //Writing data to the csv file
	      for (Offer o : offers) {
	    	  if(o.getReq()=="") continue;
//	    	  if(o.getContract()=="") o.setContract("na");
//	    	  if(o.getLevel()=="") o.setLevel("na");
//	    	  if(o.getLink()=="") o.setLink("na");
//	    	  if(o.getLocation()=="") o.setLocation("na");
//	    	  if(o.getName()=="") o.setName("na");
//	    	  if(o.getTitle()=="") o.setTitle("na");
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
