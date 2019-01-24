package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
