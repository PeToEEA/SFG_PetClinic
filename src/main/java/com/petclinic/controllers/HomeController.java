package com.petclinic.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/", "/index"})
	public String index() {
		logger.info("hit to main page");
		return "/index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

}
