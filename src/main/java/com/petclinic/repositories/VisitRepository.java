package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
