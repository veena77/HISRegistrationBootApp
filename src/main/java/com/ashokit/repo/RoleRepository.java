package com.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.RoleEntityMaster;

public interface RoleRepository extends JpaRepository<RoleEntityMaster, Serializable> {

	
}
