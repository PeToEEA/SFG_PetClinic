package com.petclinic.bootstrap;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.petclinic.model.Doctor;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.Role;
import com.petclinic.model.Visit;
import com.petclinic.pets.PetType;
import com.petclinic.repositories.MemberRepository;
import com.petclinic.repositories.PetRepository;
import com.petclinic.repositories.RoleRepository;
import com.petclinic.repositories.VisitRepository;
import com.petclinic.visit.VisitType;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private MemberRepository memberRepository;
	private RoleRepository roleRepository;
	private PetRepository petRepository;
	private VisitRepository visitRepository;
	
	
	public DevBootStrap(MemberRepository memberRepository, RoleRepository roleRepository, 
						PetRepository petRepository, VisitRepository visitRepository) {
		super();
		this.memberRepository = memberRepository;
		this.roleRepository = roleRepository;
		this.petRepository = petRepository;
		this.visitRepository = visitRepository;
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
		
		Pet doggo1 = new Pet("Doge", LocalDate.now(), PetType.DOG, owner1);
		petRepository.save(doggo1);
		
		Visit visit1 = new Visit(LocalDateTime.now().plusDays(10), VisitType.PREVENTION, doggo1);
		visit1.getDoctors().add(doctor1);
		visitRepository.save(visit1);
		
		
	}

}
