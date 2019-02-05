package com.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Owner extends Member {
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="owner_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Pet> pets = new HashSet<>();
	
	public Owner() {
		super();
	}

	public Owner(String email, String firstName, String lastName, LocalDate registrationDate, String passwordHash) {
		super(email, firstName, lastName, registrationDate, passwordHash);
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									
}
