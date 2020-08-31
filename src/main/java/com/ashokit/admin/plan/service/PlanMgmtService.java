package com.ashokit.admin.plan.service;

import java.util.List;

import com.ashokit.admin.plan.domain.Plan;

public interface PlanMgmtService {
	
	boolean savePlans(Plan plan);
	
	public List<Plan> getAllPlans();
	
	public Plan getPlanById(int id);
	
	public boolean updatePlan(Plan plan);
	
	public boolean deletePlanById(int id,String activeSW);
	

}
