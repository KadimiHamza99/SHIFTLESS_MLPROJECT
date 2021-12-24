package org.kadimi.JavaProject.models;

public class EvaluationResults {
	
	private double correct, incorrect, auROC, kappa,mae, rmse, rae, rrse, pecision, recal, fmeasure, errorRate;

	public double getCorrect() {
		return correct;
	}

	public void setCorrect(double correct) {
		this.correct = correct;
	}

	public double getMae() {
		return mae;
	}

	public void setMae(double mae) {
		this.mae = mae;
	}

	public double getIncorrect() {
		return incorrect;
	}

	public void setIncorrect(double incorrect) {
		this.incorrect = incorrect;
	}

	public double getAuROC() {
		return auROC;
	}

	public void setAuROC(double auROC) {
		this.auROC = auROC;
	}

	public double getKappa() {
		return kappa;
	}

	public void setKappa(double kappa) {
		this.kappa = kappa;
	}

	public double getRmse() {
		return rmse;
	}

	public void setRmse(double rmse) {
		this.rmse = rmse;
	}

	public double getRae() {
		return rae;
	}

	public void setRae(double rae) {
		this.rae = rae;
	}

	public double getRrse() {
		return rrse;
	}

	public void setRrse(double rrse) {
		this.rrse = rrse;
	}

	public double getPecision() {
		return pecision;
	}

	public void setPecision(double pecision) {
		this.pecision = pecision;
	}

	public double getRecal() {
		return recal;
	}

	public void setRecal(double recal) {
		this.recal = recal;
	}

	public double getFmeasure() {
		return fmeasure;
	}

	public void setFmeasure(double fmeasure) {
		this.fmeasure = fmeasure;
	}

	public double getErrorRate() {
		return errorRate;
	}

	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}
}
