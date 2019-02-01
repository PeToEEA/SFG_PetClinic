package com.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.petclinic.doctor.Specialization;

@Entity
public class Doctor extends Owner {

	@ManyToMany(cascade = CascadeType.DETACH)
	private Set<Visit> visits = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER, targetClass = Specialization.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="doctor_specialization")
	@Column(name="specialization")
	private Set<Specialization> specializations = new HashSet<>();

	public Doctor() {
		super();
	}

	public Doctor(String firstName, String lastName, LocalDate registrationDate, String passwordHash) {
		super(firstName, lastName, registrationDate, passwordHash);
	}

	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}

	public Set<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(Set<Specialization> specializations) {
		this.specializations = specializations;
	}
	
}
