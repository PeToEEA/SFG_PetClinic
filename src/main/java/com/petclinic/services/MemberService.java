package com.petclinic.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.petclinic.model.Member;
import com.petclinic.model.Role;
import com.petclinic.repositories.MemberRepository;
import com.petclinic.repositories.RoleRepository;

@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public MemberService(MemberRepository memberRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.memberRepository = memberRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public Member findMemberByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	
	public void saveMember(Member member, Set<String> roleNames) {
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setActive(true);
		member.setRoles(retrieveRoles(roleNames));
		memberRepository.save(member);
	}
	
	private Set<Role> retrieveRoles(Set<String> roleNames) {
		Set<Role> roles = new HashSet<>();
		for(String roleName : roleNames) {
			Role role = roleRepository.findByRoleName(roleName);
			if(role != null) {
				roles.add(role);
			}
		}
		return roles;
	}

}
