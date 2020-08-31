package com.ashokit.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.ashokit.domain.UserDetails;
import com.ashokit.entity.UserEntity;

public interface UserMgmtService {

	boolean saveUserDetails(UserDetails user);
	String fetchUserByEmailId(String emailId);
	public UserDetails getUserAccByPwd(String tempPwd);
	boolean updateUserAccount(UserDetails useracc);
	//public Map<Object,Integer> getAllRoles();
	public List<Object> getAllRoles();
	public Page<UserEntity> getAllUserAccounts(int pageSize,int pageNo);
	public UserDetails getUserById(String id) ;
	public boolean deleteUserAccById(String id,String activeSW);
	List<UserDetails> getAllUsersByRole(String role);
}