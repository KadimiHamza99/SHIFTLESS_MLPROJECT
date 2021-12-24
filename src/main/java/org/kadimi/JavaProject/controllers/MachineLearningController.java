package org.kadimi.JavaProject.controllers;
 
import org.kadimi.JavaProject.models.EvaluationResults;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;



public class MachineLearningController {

	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers.arff";

	public static EvaluationResults predictRequirementsJ48(String InputArff) throws Exception {
		
		// convert the csv file of scraping into a arff file
		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
		// =====Requirements Training==========//
		Instances train = source.getDataSet();
		// choose the field that i want to train
		train.setClassIndex(train.numAttributes() - 3);
		Evaluation eval = new Evaluation(train);
		// build Naive Bayes Model
		J48 model = new J48();
//		NaiveBayes model = new NaiveBayes();
		model.buildClassifier(train);
//		System.out.println(model.getCapabilities().toString());
		// Upload the test file arff
		DataSource s = new DataSource(InputArff);
		Instances testData = s.getDataSet();
		// choose the field that i want to predict
		testData.setClassIndex(testData.numAttributes() - 3);
		
		eval.evaluateModel(model, testData);
		System.out.println(eval.toSummaryString("Eval Results : \n",false));
		EvaluationResults ER = new EvaluationResults();
		
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
		}
		ER.setCorrect(eval.pctCorrect());
		ER.setIncorrect(eval.pctIncorrect());
		ER.setAuROC(eval.areaUnderROC(1));
		ER.setKappa(eval.kappa());
		ER.setMae(eval.meanAbsoluteError());
		ER.setRmse(eval.rootMeanSquaredError());
		ER.setRae(eval.relativeAbsoluteError());
		ER.setRrse(eval.rootRelativeSquaredError());
		ER.setPecision(eval.precision(1));
		ER.setRecal(eval.recall(1));
		ER.setFmeasure(eval.fMeasure(1));
		ER.setErrorRate(eval.errorRate());
		return ER;
	}
public static EvaluationResults predictRequirementsNB(String InputArff) throws Exception {
		
		// convert the csv file of scraping into a arff file
		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
		// =====Requirements Training==========//
		Instances train = source.getDataSet();
		// choose the field that i want to train
		train.setClassIndex(train.numAttributes() - 3);
		Evaluation eval = new Evaluation(train);
		// build Naive Bayes Model
//		J48 model = new J48();
		NaiveBayes model = new NaiveBayes();
		model.buildClassifier(train);
//		System.out.println(model.getCapabilities().toString());
		// Upload the test file arff
		DataSource s = new DataSource(InputArff);
		Instances testData = s.getDataSet();
		// choose the field that i want to predict
		testData.setClassIndex(testData.numAttributes() - 3);
		
		eval.evaluateModel(model, testData);
		System.out.println(eval.toSummaryString("Eval Results : \n",false));
		EvaluationResults ER = new EvaluationResults();
		
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
		}
		ER.setCorrect(eval.pctCorrect());
		ER.setIncorrect(eval.pctIncorrect());
		ER.setAuROC(eval.areaUnderROC(1));
		ER.setKappa(eval.kappa());
		ER.setMae(eval.meanAbsoluteError());
		ER.setRmse(eval.rootMeanSquaredError());
		ER.setRae(eval.relativeAbsoluteError());
		ER.setRrse(eval.rootRelativeSquaredError());
		ER.setPecision(eval.precision(1));
		ER.setRecal(eval.recall(1));
		ER.setFmeasure(eval.fMeasure(1));
		ER.setErrorRate(eval.errorRate());
		return ER;
	}

//	public static EvaluationResults predictLevelNB(String InputArff) throws Exception {
//
//		// convert the csv file of scraping into a arff file
//		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
//		// =====Requirements Training==========//
//		Instances train = source.getDataSet();
//		// choose the field that i want to train
//		train.setClassIndex(train.numAttributes() - 4);
//
//		// build Naive Bayes Model
//		NaiveBayes model = new NaiveBayes();
//		model.buildClassifier(train);
////		System.out.println(model.getCapabilities().toString());
//
//		// Upload the test file arff
//		DataSource s = new DataSource(InputArff);
//		Instances testData = s.getDataSet();
//		// choose the field that i want to predict
//		testData.setClassIndex(testData.numAttributes() - 4);
//		// display the results
//		System.out.println("===============Level=================");
//		for (int i = 0; i < testData.numInstances(); i++) {
//			double actualClass = testData.instance(i).classValue();
//			String actual = testData.classAttribute().value((int) actualClass);
//			Instance newInst = testData.instance(i);
//			double predNB = model.classifyInstance(newInst);
//			String predString = testData.classAttribute().value((int) predNB);
//			System.out.println("Real Value ====>" + actual);
//			System.out.println("Predicted Value ====>" + predString);
//		}
//	}
//	
//	public static EvaluationResults predictContractsNB(String InputArff) throws Exception {
//
//		// convert the csv file of scraping into a arff file
//		ConverterUtils.DataSource source = new ConverterUtils.DataSource(arff);
//		// =====Requirements Training==========//
//		Instances train = source.getDataSet();
//		// choose the field that i want to train
//		train.setClassIndex(train.numAttributes() - 2);
//
//		// build Naive Bayes Model
//		NaiveBayes model = new NaiveBayes();
//		model.buildClassifier(train);
////		System.out.println(model.getCapabilities().toString());
//
//		// Upload the test file arff
//		DataSource s = new DataSource(InputArff);
//		Instances testData = s.getDataSet();
//		// choose the field that i want to predict
//		testData.setClassIndex(testData.numAttributes() - 2);
//		// display the results
//		System.out.println("===============Studies Level=================");
//		for (int i = 0; i < testData.numInstances(); i++) {
//			double actualClass = testData.instance(i).classValue();
//			String actual = testData.classAttribute().value((int) actualClass);
//			Instance newInst = testData.instance(i);
//			double predNB = model.classifyInstance(newInst);
//			String predString = testData.classAttribute().value((int) predNB);
//			System.out.println("Real Value ====>" + actual);
//			System.out.println("Predicted Value ====>" + predString);
//		}
//		
//	}
}
