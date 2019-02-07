package com.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@RequestMapping(value = {"/", "/index"})
	public String registration() {
		System.out.println("register kokot");
		return "/register";
	}

}
