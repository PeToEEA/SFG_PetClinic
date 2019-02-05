package com.petclinic.repositories;

import com.petclinic.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
	Member findByEmail(String email);
}
