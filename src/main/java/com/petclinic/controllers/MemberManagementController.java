package com.petclinic.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petclinic.model.Member;
import com.petclinic.services.admin.MemberManagementService;

@Controller
@RequestMapping("/admin/member-management")
public class MemberManagementController {
	
	private MemberManagementService memberManagementService;
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	public MemberManagementController(MemberManagementService memberManagementService) {
		super();
		this.memberManagementService = memberManagementService;
	}

	@RequestMapping(value = {"", "/", "/index"})
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("members", memberManagementService.getAllMembers());
		return "/admin/member-management/members";
	}
	
	@RequestMapping("/detail/{memberId}")
	public String memberDetail(ModelMap modelMap, @PathVariable("memberId") Long memberId) {
		Member member = memberManagementService.findById(memberId);
		if(member == null) {
			logger.warn("No member found for id: " + memberId);
			return "redirect:/admin/member-management/";
		}
		modelMap.addAttribute("member", member);
		return "/admin/member-management/detail";
	}

}
