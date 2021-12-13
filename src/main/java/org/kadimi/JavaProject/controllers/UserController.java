package org.kadimi.JavaProject.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.kadimi.JavaProject.models.User;

public class UserController {

	protected EntityManager em;

	public UserController() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("API");
		em = emf.createEntityManager();
	}

	public void addUser(String competences) {
		String tokens[] = competences.split(",");
//		List<String> listeComptences = Arrays.asList(tokens);
//		User user = new User(competences,liste);
//		System.out.println(user.getCompetencesList().toString());
		User user = new User(competences);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			this.em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error to add user");
			transaction.rollback();
			throw e;
		}
	}
	
	//matching function

}
