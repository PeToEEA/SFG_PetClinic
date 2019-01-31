package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
