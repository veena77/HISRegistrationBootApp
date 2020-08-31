package com.ashokit.admin.plan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.admin.plan.domain.Plan;
import com.ashokit.admin.plan.entity.PlanEntity;
import com.ashokit.admin.plan.repo.PlanRepository;
import com.ashokit.exception.ResourceCreationException;

@Service("planMgmtService")
public class PlanMgmtServiceImpl implements PlanMgmtService{

	@Autowired
	private PlanRepository planRepo;

	@Override
	public boolean savePlans(Plan plan) {
	plan.setActiveSW("Y");
	PlanEntity entity=new PlanEntity();
	BeanUtils.copyProperties(plan, entity);
		PlanEntity savedEntity=planRepo.save(entity);
		if(savedEntity==null) {
			throw new ResourceCreationException("Plan Creation Is failed");
		}
		return !(savedEntity==null);
	}
	
	@Override
	public List<Plan> getAllPlans(){
	
		List<Plan> listPlan=new ArrayList<>();
		
		List<PlanEntity> listEntity=planRepo.findAll();
		
		listEntity.forEach(entity->{
			Plan plan=new Plan();
			BeanUtils.copyProperties(entity, plan);
			listPlan.add(plan);
		});
		
		return listPlan;
	}
	
	@Override
	public Plan getPlanById(int id) {
		
		Plan plan=null;
		Optional<PlanEntity> optEntity=planRepo.findById(id);
		if(optEntity.isPresent()) {
			PlanEntity entity=new PlanEntity();
			BeanUtils.copyProperties(entity, plan);
			return plan;
		}
		
		return plan;
		
	}
	
	public boolean updatePlan(Plan plan) {
		PlanEntity entity=new PlanEntity();
		BeanUtils.copyProperties(plan, entity);
		PlanEntity updatedEntity=planRepo.save(entity);
		return (updatedEntity!=null);
		
	}
	
	public boolean deletePlanById(int id,String activeSW) {
		
		int count=planRepo.updateByIdAndActiveSW(id, activeSW);
		if(count==0)
			return false;
		return true;
		
	}


	
}
