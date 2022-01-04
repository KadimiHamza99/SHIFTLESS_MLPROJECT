package org.kadimi.JavaProject.controllers;

import java.io.IOException;
import java.util.List;

import org.kadimi.JavaProject.models.Offer;

public interface WebScrapingInterface {

	public static final String technologiesFile = "";
	
	List<String> getLinks();
	List<Offer> extractOffers() throws IOException;
	String cleaningProcess(String r) throws IOException;
}
