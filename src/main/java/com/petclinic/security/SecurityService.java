package com.petclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.petclinic.services.MemberService;

@Service
public class SecurityService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MemberService memberService;
	
	public String findLoggedInMemberName() {
		Object userDetailsObject = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if(userDetailsObject instanceof UserDetails) {
			return ((UserDetails)userDetailsObject).getUsername();
		}
		return null;
	}
	
}
