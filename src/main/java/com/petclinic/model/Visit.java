package com.petclinic.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Nullable
	private LocalDateTime scheduledDateTime;
	
	@Nullable
	private LocalDateTime realDateTime;
	
	@NotNull
	private VisitType visitType = VisitType.OTHER;
	
	@ManyToOne
	private Pet pet;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="doctor_visit", joinColumns = @JoinColumn(name = "visit_id"),
	inverseJoinColumns = @JoinColumn(name="doctor_id"))
	private Set<Doctor> doctors = new HashSet<>();

}
