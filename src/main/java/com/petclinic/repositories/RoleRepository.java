package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
