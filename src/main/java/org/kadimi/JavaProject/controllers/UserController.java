package org.kadimi.JavaProject.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public static double pecentageOfMatch(String[] as0, String[] as1) {
		int n = as0.length;
		Integer temp = null;

		// String frequency of as0
		HashMap<String, Integer> hm0 = new HashMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			temp = hm0.get(as0[i]);
			if (temp == null) {
				hm0.put(as0[i], new Integer(1));
			} else {
				hm0.put(as0[i], new Integer(temp.intValue() + 1));
			}
		}

		// String frequency of as1
		n = as1.length;
		HashMap<String, Integer> hm1 = new HashMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			temp = hm1.get(as1[i]);
			if (temp == null) {
				hm1.put(as1[i], new Integer(1));
			} else {
				hm1.put(as1[i], new Integer(temp.intValue() + 1));
			}
		}

		// Frequency difference between hm0 and hm1 to diff
		HashMap<String, Integer> diff = new HashMap<String, Integer>();
		String key;
		Integer value, value1, rval;
		Iterator it = hm0.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it.next();
			key = pairs.getKey();
			value = pairs.getValue();
			value1 = hm1.get(key);
			it.remove();
			hm1.remove(key);
			if (value1 != null)
				rval = new Integer(Math.abs(value1.intValue() - value.intValue()));
			else
				rval = value;
			diff.put(key, rval);
		}

		// Sum all remaining String frequencies in hm1
		int val = 0;
		it = hm1.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it.next();
			val += pairs.getValue().intValue();
		}

		// Sum all frequencies in diff
		it = diff.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it.next();
			val += pairs.getValue().intValue();
		}

		// Calculate word match percentage
		double per = (double) ((((float) val * 100)) / ((float) (as0.length + as1.length)));
		per = 100 - per;
		return per;
	}

}
