package org.kadimi.JavaProject.resources;

import java.util.List;

import org.kadimi.JavaProject.controllers.AdministratorController;
import org.kadimi.JavaProject.models.User;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/admin")
public class AdministratorResource {

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
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchData() {
		try {
			//scraping function in adminstratorController class
			return "web scraping succeded";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
}
