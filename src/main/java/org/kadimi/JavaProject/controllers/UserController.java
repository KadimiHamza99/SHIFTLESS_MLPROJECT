package org.kadimi.JavaProject.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserController extends UserAbstr{

	public UserController() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("API");
		em = emf.createEntityManager();
	}

}
