package com.petclinic.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.petclinic.visit.VisitType;

@Entity
public class Visit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Nullable
	private LocalDateTime scheduledDateTime;
	
	@Nullable
	private LocalDateTime realDateTime;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private VisitType visitType = VisitType.OTHER;
	
	@ManyToOne
	private Pet pet;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="doctor_visit", joinColumns = @JoinColumn(name = "visit_id"),
	inverseJoinColumns = @JoinColumn(name="doctor_id"))
	private Set<Doctor> doctors = new HashSet<>();

	public Visit() {
		
	}
	
	public Visit(LocalDateTime scheduledDateTime, @NotNull VisitType visitType, Pet pet) {
		super();
		this.scheduledDateTime = scheduledDateTime;
		this.visitType = visitType;
		this.pet = pet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	public LocalDateTime getRealDateTime() {
		return realDateTime;
	}

	public void setRealDateTime(LocalDateTime realDateTime) {
		this.realDateTime = realDateTime;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

}
