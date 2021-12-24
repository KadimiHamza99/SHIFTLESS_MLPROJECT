package org.kadimi.JavaProject.resources;

import java.util.ArrayList;
import java.util.List;

import org.kadimi.JavaProject.controllers.AdministratorController;
import org.kadimi.JavaProject.controllers.UserController;
import org.kadimi.JavaProject.models.Offer;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {
	private static AdministratorController ac = new AdministratorController();
	class Match{
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
	
	private static final String offersPredict = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.csv";
	private static final String offersPredictArff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";
	UserController uc = new UserController();
	
	@POST
	@Path("{techs}")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public List<Match> add(@PathParam("techs") String competences) {
		List<Match> ret = new ArrayList<Match>();
		try {
			uc.addUser(competences);
			List<Offer> offers = new ArrayList<Offer>();
			offers = ac.getOffers();
			for (Offer offer : offers) {
				Match match = new Match(offer.getName(),offer.getTitle(),offer.getLink(),offer.getLocation(),(double) 59);
				ret.add(match);
			}
			return ret;
		}catch(Exception e) {
			e.getStackTrace();
		}	
		return ret;
	}	
	
	
}
