package org.kadimi.JavaProject.controllers;

import java.util.ArrayList;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;

public class MachineLearningController {

	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers.arff";

	public static ArrayList<String> predictRequirements(String InputArff) throws Exception {
		ArrayList<String> returned = new ArrayList<String>();
		
		// convert the csv file of scraping into a arff file
		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
		// =====Requirements Training==========//
		Instances train = source.getDataSet();
		// choose the field that i want to train
		train.setClassIndex(train.numAttributes() - 3);

		// build Naive Bayes Model
		NaiveBayes model = new NaiveBayes();
		model.buildClassifier(train);
//		System.out.println(model.getCapabilities().toString());

		// Upload the test file arff
		DataSource s = new DataSource(InputArff);
		Instances testData = s.getDataSet();
		// choose the field that i want to predict
		testData.setClassIndex(testData.numAttributes() - 3);
		// display the results
		System.out.println("===============Requirements=================");
		for (int i = 0; i < testData.numInstances(); i++) {
			double actualClass = testData.instance(i).classValue();
			String actual = testData.classAttribute().value((int) actualClass);
			Instance newInst = testData.instance(i);
			double predNB = model.classifyInstance(newInst);
			String predString = testData.classAttribute().value((int) predNB);
			System.out.println("Real Value ====>" + actual);
			System.out.println("Predicted Value ====>" + predString);
			returned.add(predString);
		}
		return returned;
	}

	public static ArrayList<String> predictLevel(String InputArff) throws Exception {

		ArrayList<String> returned = new ArrayList<String>();
		// convert the csv file of scraping into a arff file
		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
		// =====Requirements Training==========//
		Instances train = source.getDataSet();
		// choose the field that i want to train
		train.setClassIndex(train.numAttributes() - 4);

		// build Naive Bayes Model
		NaiveBayes model = new NaiveBayes();
		model.buildClassifier(train);
//		System.out.println(model.getCapabilities().toString());

		// Upload the test file arff
		DataSource s = new DataSource(InputArff);
		Instances testData = s.getDataSet();
		// choose the field that i want to predict
		testData.setClassIndex(testData.numAttributes() - 4);
		// display the results
		System.out.println("===============Level=================");
		for (int i = 0; i < testData.numInstances(); i++) {
			double actualClass = testData.instance(i).classValue();
			String actual = testData.classAttribute().value((int) actualClass);
			Instance newInst = testData.instance(i);
			double predNB = model.classifyInstance(newInst);
			String predString = testData.classAttribute().value((int) predNB);
			System.out.println("Real Value ====>" + actual);
			System.out.println("Predicted Value ====>" + predString);
			returned.add(predString);
		}
		return returned;
	}
	
	public static ArrayList<String> predictContracts(String InputArff) throws Exception {

		ArrayList<String> returned = new ArrayList<String>();
		// convert the csv file of scraping into a arff file
		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
		// =====Requirements Training==========//
		Instances train = source.getDataSet();
		// choose the field that i want to train
		train.setClassIndex(train.numAttributes() - 2);

		// build Naive Bayes Model
		NaiveBayes model = new NaiveBayes();
		model.buildClassifier(train);
//		System.out.println(model.getCapabilities().toString());

		// Upload the test file arff
		DataSource s = new DataSource(InputArff);
		Instances testData = s.getDataSet();
		// choose the field that i want to predict
		testData.setClassIndex(testData.numAttributes() - 2);
		// display the results
		System.out.println("===============Studies Level=================");
		for (int i = 0; i < testData.numInstances(); i++) {
			double actualClass = testData.instance(i).classValue();
			String actual = testData.classAttribute().value((int) actualClass);
			Instance newInst = testData.instance(i);
			double predNB = model.classifyInstance(newInst);
			String predString = testData.classAttribute().value((int) predNB);
			System.out.println("Real Value ====>" + actual);
			System.out.println("Predicted Value ====>" + predString);
			returned.add(predString);
		}
		return returned;
	}
}
