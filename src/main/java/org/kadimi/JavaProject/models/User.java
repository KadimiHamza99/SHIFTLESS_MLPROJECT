package org.kadimi.JavaProject.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//Attributs	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER")
	private int Id;
	
//	@Transient
//	private List<String> CompetencesList;
	
	@Column(name="COMPETENCES")
	private String Competences;

	//Constructors
	public User() { }
	
//	public User(String competences,List<String> liste) {
//		Competences = competences;
//		CompetencesList = liste;
//	}
	public User(String competences) {
		Competences = competences;
	}

	
	//Getters and Setters
	public int getId() {
		return Id;
	}
	
	public String getCompetences() {
		return Competences;
	}

	public void setCompetences(String competences) {
		Competences = competences;
	}
	
//	public List<String> getCompetencesList() {
//		return CompetencesList;
//	}
//
//	public void setCompetencesList(List<String> competencesList) {
//		CompetencesList = competencesList;
//	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", Competences=" + Competences + "]";
	}
	
	
}
