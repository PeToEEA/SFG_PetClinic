package com.petclinic.repositories;

import com.petclinic.model.Member;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
	Member findByEmail(String email);
	
	Set<Member> findAll();
	
	Optional<Member> findById(Long id);
}
