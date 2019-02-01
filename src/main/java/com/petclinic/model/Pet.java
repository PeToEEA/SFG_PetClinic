package com.petclinic.model;

import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import com.petclinic.pets.PetType;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;
	
	@Nullable
	private LocalDate birthDate;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PetType type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Owner owner;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="pet_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Visit> visits = new HashSet<>();
	
	public Pet() {
		
	}

	public Pet(@NotNull String name, LocalDate birthDate, @NotNull PetType type, Owner owner) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.type = type;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}	
}
