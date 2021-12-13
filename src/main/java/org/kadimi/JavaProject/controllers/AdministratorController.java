package org.kadimi.JavaProject.controllers;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.kadimi.JavaProject.models.Offer;
import org.kadimi.JavaProject.models.User;

public class AdministratorController extends UserController {
	
	WebScrapingController wsc = new WebScrapingController();
	
	public AdministratorController() {
		super();
	}
	
	public List<User> getUsers() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			Query query=this.em.createQuery("SELECT u FROM User u");
			transaction.commit();
			return query.getResultList();
		}catch(Exception e) {
			transaction.rollback();
			System.out.println("Erreur Fetching Users");
			e.getStackTrace();
			return null;
		}
	}	
	
	public void removeUser(int id) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			User u = this.em.find(User.class, id);
			this.em.remove(u);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			System.out.println("Erreur delete user");
		}
	}
	
	public void removeAllUsers() {
		EntityTransaction transaction = em.getTransaction();
		try {
			List<User> UsersList = this.getUsers();
			for (User user : UsersList) {
				transaction.begin();
				user = em.find(User.class, user.getId());
				em.remove(user);
				transaction.commit();
			}
		}catch(Exception e) {
			transaction.rollback();
			System.out.println("Removing users failed");
			throw e;
		}
	}
	
	public void javaSoup() {
		List<Offer> offers = wsc.extractOffers();
		EntityTransaction transaction = em.getTransaction();
		for (Offer offer : offers) {
			transaction.begin();
			try {
				this.em.persist(offer);
				transaction.commit();
			} catch (Exception e) {
				System.out.println("Error to add user");
				transaction.rollback();
				throw e;
			}
		}
	}
	
}
