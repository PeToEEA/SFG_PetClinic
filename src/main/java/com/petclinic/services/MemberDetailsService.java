package com.petclinic.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;

import com.petclinic.controllers.HomeController;
import com.petclinic.model.Member;
import com.petclinic.model.Role;
import com.petclinic.repositories.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : member.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		User user = new User(member.getEmail(), member.getPassword(), 
				true, true, true, true, grantedAuthorities);
		logger.info("user: " + user.getUsername() + ", password: " + user.getPassword() + ", authorities: " + getAuthoritiesAsString(grantedAuthorities));
		
		
		return user;
	}
	
	private String getAuthoritiesAsString(Set<GrantedAuthority> grantedAuthorities) {
		Set<String> authorities = new HashSet<>();
		for(GrantedAuthority grantedAuthority: grantedAuthorities) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return String.join(", ", authorities);
	}

}
