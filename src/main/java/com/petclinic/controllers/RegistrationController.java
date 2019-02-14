package com.petclinic.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petclinic.commands.RegistrationCmd;
import com.petclinic.services.MemberService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	public RegistrationController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	private MemberService memberService;
	
	private static final Logger logger = LogManager.getLogger(RegistrationController.class);
	
	@RequestMapping(value = {"", "/", "/index"})
	public String registration() {
		return "/register";
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute RegistrationCmd registrationCmd) {
		logger.info("Received registration command: " + registrationCmd.toString());
		memberService.createRegularMember(registrationCmd);
		return "/register";
	}

}
