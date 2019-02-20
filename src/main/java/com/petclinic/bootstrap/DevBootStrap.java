package com.petclinic.bootstrap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.petclinic.doctor.Specialization;
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
import com.petclinic.services.MemberService;
import com.petclinic.visit.VisitType;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private MemberRepository memberRepository;
	private RoleRepository roleRepository;
	private PetRepository petRepository;
	private VisitRepository visitRepository;
	private MemberService memberService;
	
	
	public DevBootStrap(MemberRepository memberRepository, RoleRepository roleRepository, 
						PetRepository petRepository, VisitRepository visitRepository,
						MemberService memberService) {
		super();
		this.memberRepository = memberRepository;
		this.roleRepository = roleRepository;
		this.petRepository = petRepository;
		this.visitRepository = visitRepository;
		this.memberService = memberService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
		
	}
	
	private void initData() {
		
		final String ROLE_MEMBER = "ROLE_MEMBER";
		final String ROLE_ADMIN = "ROLE_ADMIN";		
		
		Role memberRole = new Role(ROLE_MEMBER, "standard member");
		Role adminRole = new Role(ROLE_ADMIN, "administrator role");
		
		roleRepository.save(memberRole);
		roleRepository.save(adminRole);
		
		Owner owner1 = new Owner("john.snow@gmail.com", "Jon", "Snow", LocalDate.now(), "1234");
		owner1.getRoles().add(memberRole);
		
		Doctor doctor1 = new Doctor("mccoy@petclinic.com", "Leonard", "McCoy", LocalDate.now(), "abcd");
		doctor1.getSpecializations().add(Specialization.DOG);
		doctor1.getSpecializations().add(Specialization.FELINE);
		doctor1.getRoles().add(memberRole);
		doctor1.getRoles().add(adminRole);
		
		memberRole.getMembers().add(owner1);
		memberRole.getMembers().add(doctor1);		
		adminRole.getMembers().add(doctor1);
		
		memberService.saveMember(owner1, new HashSet<>(Arrays.asList("ROLE_MEMBER")));
		//smemberRepository.save(owner1);
		memberService.saveMember(doctor1, new HashSet<>(Arrays.asList("ROLE_MEMBER", "ROLE_ADMIN")));
		
		
		
		Pet doggo1 = new Pet("Doge", LocalDate.now(), PetType.DOG, owner1);
		petRepository.save(doggo1);
		
		Visit visit1 = new Visit(LocalDateTime.now().plusDays(10), VisitType.PREVENTION, doggo1);
		visit1.getDoctors().add(doctor1);
		visitRepository.save(visit1);
		
		
	}

}
