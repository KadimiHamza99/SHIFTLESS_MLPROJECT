 package org.kadimi.JavaProject.resources;



import java.util.List;

import org.kadimi.JavaProject.controllers.AdministratorController;
import org.kadimi.JavaProject.models.EvaluationResults;
import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/admin")
public class AdministratorResource {
	private static final String arff = "C:\\JAVAEE\\JavaProject\\ressources\\Offers-test.arff";


	AdministratorController ac = new AdministratorController();
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String removeAll() {
		try {
			ac.removeAllUsers();
			return "All users are removed";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DELETE
	@Path("/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public String remove(@PathParam("idUser") int id) {
		try {
			ac.removeUser(id);
			return "User is removed";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(){
		try {
			return ac.getUsers();
		}catch(Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/scraping")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Offer> fetchData() {
		try {
			return ac.javaSoup();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@GET
	@Path("/J48")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationResults J48() {
		try {
			return ac.predictJ48(arff);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/NB")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationResults NB() {
		try {
			return ac.predictNB(arff);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/IBk")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationResults IBk() {
		try {
			return ac.predictIBk(arff);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/AB")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationResults AdaBoostM1() {
		try {
			return ac.predictAdaBoostM1(arff);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/P")
	@Produces(MediaType.APPLICATION_JSON)
	public EvaluationResults PART() {
		try {
			return ac.predictPART(arff);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
