package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long>{

}
