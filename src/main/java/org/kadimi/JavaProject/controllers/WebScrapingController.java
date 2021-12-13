package org.kadimi.JavaProject.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kadimi.JavaProject.models.Offer;

public class WebScrapingController {

	private static final String Company = ".col-md-12 blc p";
	private static final String Location = "#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(3) > li:nth-child(2)";

	protected List<String> getLinks() {
		final String JOB_CARD_CLASS = "section";
		final String JOB_LINK_SELECTOR = ".section a";
//		final String JOB_LEVEL_SELECTOR = "div.section div.holder";
		List<String> links = new ArrayList<String>();

		Document document = null;
		for (int i = 1; i < 2; i++) {
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

	protected List<Offer> extractOffers() {
		final String contract = "tagContrat";
		final String Requirement = "#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(6)";
		List<String> jobs = this.getLinks();
		List<Offer> offers = new ArrayList<Offer>();

		for (String job : jobs) {
			Document doc1 = null;
			try {
				doc1 = Jsoup.connect("https://www.rekrute.com" + job).get();
			} catch (IOException e) {
				System.out.println("error failed to connect");
				throw new RuntimeException(e);
			}
			Offer offer1 = new Offer();

			Elements titleElements = doc1.select(
					"#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12");
			if (!titleElements.isEmpty()) {
				offer1.setTitle(titleElements.get(0).text());
			}

			Elements levelElements = doc1.select(
					"#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(3) > li:nth-child(3)");
			if (!levelElements.isEmpty()) {
				offer1.setLevel(levelElements.get(0).text());
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
			Elements requirementElements = doc1.select(Requirement);
			if (!requirementElements.isEmpty()) {
				offer1.setReq(requirementElements.get(0).text());
			}
			Elements locationElements = doc1.select(Location);
			if (!locationElements.isEmpty()) {
				String n = locationElements.get(0).text();
				n = n.replace("poste(s) sur ", "");
				n = n.replaceAll("[0-9]", "");

				offer1.setLocation(n);
			}
			offers.add(offer1);

		}
		for (Offer offer : offers) {
			System.out.println(String.format("%s\n", offer.getLevel()));

		}
		System.out.println(offers.size());
		return offers;
		// requirements,name,title,city,level
	}
}
