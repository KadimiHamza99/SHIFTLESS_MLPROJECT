package org.kadimi.JavaProject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="OFFER")
public class Offer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER")
	private Long IdOffer;
	@Column(name="TITLE",length=1000)
	private String title;
	@Column(name="NAME",length=1000)
	private String name;
	@Column(name="CONTRACT",length=50)
	private String contract;
	@Column(name="STUDIES_LEVEL",length=6000)
	private String level;
	@Column(name="REQUIREMENTS",length=6000)
//	@Transient
	private String req;
	@Column(name="LINK",length=300)
	private String link;
	@Column(name="LOCATION")
	private String Location;

	public Offer(Long idOffer, String title, String name, String contract, String level, String req, String link,
			String location) {
		super();
		IdOffer = idOffer;
		this.title = title;
		this.name = name;
		this.contract = contract;
		this.level = level;
		this.req = req;
		this.link = link;
		Location = location;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Offer() {	}
	
	@Override
	public String toString() {
		return "Offer [IdOffer=" + IdOffer + ", title=" + title + ", name=" + name + ", contract=" + contract
				+ ", level=" + level + ", req=" + req + ", link=" + link + ", Location=" + Location + "]";
	}

	public Long getIdOffer() {
		return IdOffer;
	}
	public void setIdOffer(long id) {
		this.IdOffer = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

}
