package com.ashokit.admin.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashokit.admin.plan.domain.Plan;
import com.ashokit.admin.plan.service.PlanMgmtService;

@Controller
public class PlansController {

	@Autowired
	private PlanMgmtService planService;
	
	@RequestMapping("/plans")
	public String loadPlanForm(Model model,@ModelAttribute("plan") Plan plan) {
		
		 plan=new Plan();
		model.addAttribute("plan",plan);
		return "planRegistrationForm";
	}
	
	
	@PostMapping("/regPlan")
	public String planRegistration(Model model,@ModelAttribute("plan") Plan plan) {
		planService.savePlans(plan);
		model.addAttribute("resMsg","Saved Successfully");
		return "planRegistrationForm" ;
	}
}

