package com.petclinic.bootstrap;

import java.time.LocalDate;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.petclinic.model.Doctor;
import com.petclinic.model.Member;
import com.petclinic.model.Owner;
import com.petclinic.model.Role;
import com.petclinic.repositories.MemberRepository;
import com.petclinic.repositories.RoleRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private MemberRepository memberRepository;
	private RoleRepository roleRepository;
	
	
	public DevBootStrap(MemberRepository memberRepository, RoleRepository roleRepository) {
		super();
		this.memberRepository = memberRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
		
	}
	
	private void initData() {		
		Role memberRole = new Role("MEMBER", "standard member");
		Role adminRole = new Role("ADMIN", "administrator role");	
		
		Owner owner1 = new Owner("Jon", "Snow", LocalDate.now(), "1234");
		owner1.getRoles().add(memberRole);
		
		Doctor doctor1 = new Doctor("Leonard", "McCoy", LocalDate.now(), "abcd");
		doctor1.getRoles().add(memberRole);
		doctor1.getRoles().add(adminRole);
		
		memberRole.getMembers().add(owner1);
		memberRole.getMembers().add(doctor1);		
		adminRole.getMembers().add(doctor1);
		
		memberRepository.save(owner1);
		memberRepository.save(doctor1);
		
		roleRepository.save(memberRole);
		roleRepository.save(adminRole);
		
	}

}
