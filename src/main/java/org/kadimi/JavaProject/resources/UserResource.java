package org.kadimi.JavaProject.resources;

import java.util.ArrayList;
import java.util.List;

import org.kadimi.JavaProject.controllers.AdministratorController;
import org.kadimi.JavaProject.controllers.UserController;
import org.kadimi.JavaProject.models.Match;
import org.kadimi.JavaProject.models.Offer;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {
	private static AdministratorController ac = new AdministratorController();
	

	UserController uc = new UserController();
	
	@POST
	@Path("{techs}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Match> add(@PathParam("techs") String competences) {
		List<Match> ret = new ArrayList<Match>();
		try {
			uc.addUser(competences);
			List<Offer> offers = new ArrayList<Offer>();
			offers = ac.getOffers();
			for (Offer offer : offers) {
				String requirements = offer.getReq();
				competences = competences.trim().replaceAll("[.?!]", " ");
				requirements = requirements.trim().replaceAll("[.?!]", " ");
				// Split by space
				double matching = UserController.pecentageOfMatch(competences,requirements);
				Match match = new Match(offer.getName(),offer.getTitle(),offer.getLink(),offer.getLocation(),matching*100);
				if(matching*100 > 30) {
					ret.add(match);
				}
			}
			return ret;
		}catch(Exception e) {
			e.getStackTrace();
		}	
		return ret;
	}	
	
	
}
