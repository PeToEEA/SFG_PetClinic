package com.petclinic.repositories;

import com.petclinic.model.Member;
import org.springframework.data.repository.*;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
