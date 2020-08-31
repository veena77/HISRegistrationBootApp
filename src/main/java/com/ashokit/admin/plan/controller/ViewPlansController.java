package com.ashokit.admin.plan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.admin.plan.domain.Plan;
import com.ashokit.admin.plan.service.PlanMgmtService;
import com.ashokit.constant.ConstantValues;

@Controller("viewPlans")
public class ViewPlansController {

	@Autowired
	private PlanMgmtService service;
	
	@RequestMapping("/viewAllPlans")
	public String LoadAllPlansForm(@ModelAttribute("plan") Plan plan,Map<String,Object> map) throws Exception {
		List<Plan> listPlan=service.getAllPlans();
		if(listPlan==null) {
			throw new Exception("NO plan is Registered");
		}
		map.put("list", listPlan);
		return "viewPlans";
	}
	
	@RequestMapping("/editplan")
	public String editPlanById(@RequestParam("id")int planId,@ModelAttribute("plan") Plan plan,Map<String,Object> map)throws Exception  {
		plan=service.getPlanById(planId);
		if(plan!=null) {
			map.put("plan", plan);
			return "updatePlanForm";
		}
		return "updatePlanForm";
	}
	
	@RequestMapping("/removeplan")
	public String removePlan(@RequestParam("id") int planId,@ModelAttribute("plan") Plan plan,Map<String,String> map) throws Exception {
		
		boolean isDeleted=service.deletePlanById(planId,ConstantValues.SWITCH_OFF);
		if(isDeleted) {
			map.put("resMsg",plan.getPlanName()+" is Removed");
			return "redirect:/viewAllPlans";
		}
		throw new Exception("Unable delete the Plan Please try again");
	}
	
	@RequestMapping("/activeplan")
	public String activatePlan(@RequestParam("id") int planId,@ModelAttribute("plan") Plan plan,Map<String,String> map) {
		boolean isActivated=service.deletePlanById(planId, ConstantValues.SWITCH_ON);
		if(isActivated)
			map.put("resMsg", plan.getPlanName()+" is Activated");
		return "redirect:/viewAllPlans";
		
	}
}

