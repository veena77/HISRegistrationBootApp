package com.ashokit.admin.plan.repo;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.admin.plan.entity.PlanEntity;
@Transactional
		public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {
		@Modifying
			@Query("update PlanEntity p set p.activeSW=:activeSW where p.planId=:id ")
			int updateByIdAndActiveSW(int id,String activeSW);

}
