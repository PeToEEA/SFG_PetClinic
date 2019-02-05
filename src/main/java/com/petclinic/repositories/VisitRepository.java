package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Visit;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

}
