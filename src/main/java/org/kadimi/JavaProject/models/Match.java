package org.kadimi.JavaProject.models;

public class Match {
	public String Company;
	public String Title;
	public String Link;
	public String Location;
	public Double Matching;
	public Match(String company, String title, String link, String location, Double match) {
		Company = company;
		Title = title;
		Link = link;
		Location = location;
		Matching = match;
	}
}
