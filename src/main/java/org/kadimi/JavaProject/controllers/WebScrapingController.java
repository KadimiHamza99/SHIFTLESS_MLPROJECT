package org.kadimi.JavaProject.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kadimi.JavaProject.models.Offer;

public class WebScrapingController {

	private static final String Company = "#fortopscroll > div.container.anno > div:nth-child(2) > div > div.foruloffemp.col-md-12.blc > h4 > strong";
	private static final String contract = "tagContrat";
	private static final String Requirement = "#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(5)";
	private static final String JOB_CARD_CLASS = "section";
	private static final String JOB_LINK_SELECTOR = ".section a";
	private static final String TITLE = "h1";

	private static final String technologiesFile = "C:\\JAVAEE\\JavaProject\\ressources\\technologies.txt";
	
	protected List<String> getLinks() {

//		final String JOB_LEVEL_SELECTOR = "div.section div.holder";
		List<String> links = new ArrayList<String>();

		Document document = null;
		for (int i = 1; i < 11; i++) {
			try {
				document = Jsoup.connect("https://www.rekrute.com/offres.html?s=3&p=" + i + "&o=1&sectorId%5B0%5D=24")
						.userAgent("Mozilla").data("name", "jsoup").get();
			} catch (IOException ioe) {
				System.out.println("Unable to connect to the URL");
				ioe.printStackTrace();
			}
			Elements jobElements = document.getElementsByClass(JOB_CARD_CLASS);

			for (Element jobElement : jobElements) {
				Elements linkElements = jobElement.select(JOB_LINK_SELECTOR);

				if (!linkElements.isEmpty()) {
	 				links.add(linkElements.get(0).attr("href"));
				}
			}
		}
		return links;
	}

	protected List<Offer> extractOffers() throws IOException {

		List<String> links = this.getLinks();
		List<Offer> offers = new ArrayList<Offer>();

		for (String link : links) {
			Document doc1 = null;
			try {
				doc1 = Jsoup.connect("https://www.rekrute.com" + link).get();
			} catch (IOException e) {
				System.out.println("error failed to connect");
				throw new RuntimeException(e);
			}
			Offer offer1 = new Offer();

			Elements levelElements = doc1.select(
					"#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(3) > li:nth-child(3)");
			if (!levelElements.isEmpty()) {
				offer1.setLevel(levelElements.get(0).text().toLowerCase());
			}
			Elements NameElements = doc1.select(Company);
			if (!NameElements.isEmpty()) {
				String n = NameElements.get(0).text();
				n = n.replace("Les dernières offres d’emploi de « ", "");
				n = n.replace(" »", "");
				offer1.setName(n);
			}
			Elements ContractElements = doc1.getElementsByClass(contract);
			if (!ContractElements.isEmpty()) {
				offer1.setContract(ContractElements.get(0).text());
			}
			Elements titleElements = doc1.select(TITLE);
			if (!titleElements.isEmpty() && offer1.getContract() != null) {
				String[] parts = titleElements.get(0).text().split("-");
				offer1.setTitle(parts[0].trim());
				offer1.setLocation(parts[1].trim());
			}
			Elements requirementElements = doc1.select(Requirement);
			if (!requirementElements.isEmpty()) {
				for (Element l : requirementElements) {
					if (l.text().contains("Poste :")) {
						offer1.setReq(cleaningProcess(doc1.select(
								"#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(6)").get(0)
								.text()));
					} else {
						offer1.setReq(cleaningProcess(doc1.select(Requirement).get(0).text()));
					}
				}
			}
			offer1.setLink(link);

			offers.add(offer1);

		}
		for (Offer offer : offers) {
			System.out.println(String.format("%s\n", offer.getReq()));

		}
		System.out.println(offers.size());
		
		return offers;
	}

	private String cleaningProcess(String req) throws IOException {
		// get technologies from the technologies file txt and put it in a techs array
		BufferedReader br = new BufferedReader(new FileReader(technologiesFile));
		ArrayList<String> techs = new ArrayList<String>();
		String line = br.readLine();
		while (line != null) {
			techs.add(line.trim());
			line = br.readLine();
		}
		br.close();
		// save technologies on a arrayList
		ArrayList<String> returnList = new ArrayList<String>();
		ArrayList<String> rl = new ArrayList<String>();
		req = req.toLowerCase().trim().replaceAll("\\s+", " ").replaceAll("\\p{Punct}", "");
		for (String t : techs) {
			if (req.contains(" " + t + " ") && t != null)
				rl.add(t);
		}
		// remove duplicates
		for (String string : rl) {
			if (!returnList.contains(string))
				returnList.add(string);
		}
		// return converted techs List to string
		return String.join(" , ", returnList);
	}

}
