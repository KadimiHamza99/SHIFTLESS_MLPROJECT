package org.kadimi.JavaProject.resources;

import org.kadimi.JavaProject.controllers.UserController;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

	UserController uc = new UserController();
	
	@POST
	@Path("{techs}")
	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
	public String add(@PathParam("techs") String competences) {
		try {
			uc.addUser(competences);
			//matching function
			return "user added";
		}catch(Exception e) {
			e.getStackTrace();
			return "adding user failed";
		}	
	}	
}
