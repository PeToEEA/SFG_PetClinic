package com.petclinic.services.admin;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.petclinic.model.Member;
import com.petclinic.repositories.MemberRepository;

@Service
public class MemberManagementService {
	
	private MemberRepository memberRepository;

	public MemberManagementService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}
	
	public Set<Member> getAllMembers() {
		return memberRepository.findAll();
	}
	
	public Member findById(Long id) {
		return memberRepository.findById(id).get();
	}

}
