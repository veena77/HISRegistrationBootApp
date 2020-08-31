package com.ashokit.service;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.ashokit.constant.ConstantValues;
import com.ashokit.domain.UserDetails;
import com.ashokit.entity.RoleEntityMaster;
import com.ashokit.entity.UserEntity;
import com.ashokit.exception.NotFoundException;
import com.ashokit.repo.RoleRepository;
import com.ashokit.repo.UserRepository;
import com.ashokit.util.EmailUtil;
import com.ashokit.util.PwdUtil;


@Service("userService")
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public boolean saveUserDetails(UserDetails user) {
		user.setStatus(ConstantValues.LOCKED_STR);
		user.setActiveSW(ConstantValues.SWITCH_ON);
		user.setPassword(PwdUtil.generateTempPassword(ConstantValues.LENGTH_OF_PWD));
		UserEntity entity=new UserEntity();	
		BeanUtils.copyProperties(user, entity);
		UserEntity savedEntity=repo.save(entity);
		if(savedEntity.getUserId()!=null) {				
			return emailUtil.sendUserAccUnlockEmail(user); 
		}
			else
			throw new ResourceAccessException("Registration is failed Please Try Again");
	}
	
	
	@Override
	public String fetchUserByEmailId(String emailId) {
		UserEntity entity=repo.findByEmailId(emailId); 
		if(entity!=null)
			return "VALID";
		else
			throw new NotFoundException("The Given Email Id is not Resgistered");
	}

	/*public Map<Object,Integer> getAllRoles(){
	Map<Object,Integer> map=new LinkedHashMap<>();
			List<RoleEntityMaster> rolesList=roleRepo.findAll();
	rolesList.forEach(roles->{
		map.put(roles.getRoleName(),roles.getRoleId());
	});
	
	return map;
		
	}*/
	

	@Override
	public List<Object> getAllRoles() {

		List<Object> list=new ArrayList<Object>();
		List<RoleEntityMaster> listRoles=roleRepo.findAll();
		listRoles.forEach(roles->{
			list.add(roles.getRoleName());
		});
		return list;
	}
	
	
	public UserDetails getUserAccByPwd(String tempPwd) {
		UserEntity entity=repo.findByPassword(tempPwd);
		UserDetails user=null;
	
		if(entity!=null) {
			user=new UserDetails();
			BeanUtils.copyProperties(entity, user);
		}
			return user;
	}
	
	@Override
	public boolean updateUserAccount(UserDetails useracc) {

		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(useracc, entity);
		UserEntity savedEntity=repo.save(entity);
			return savedEntity!=null;
	}
	
	/*
	 * public List<UserDetails> getAllUserAccounts(){ List<UserDetails>
	 * usersList=new ArrayList<UserDetails>(); List<UserEntity> entityList=null;
	 * entityList= repo.findAll(); entityList.forEach(entity->{ UserDetails user=new
	 * UserDetails(); BeanUtils.copyProperties(entity , user); usersList.add(user);
	 * }); return usersList; }
	 */
	
	
	public UserDetails getUserById(String id) {
		UserDetails user=null;
		UserEntity userEntity=null;
		Optional<UserEntity> entity=repo.findById(id);
		if(entity.isPresent()) {
			userEntity=entity.get();
			user=new UserDetails();
			BeanUtils.copyProperties(userEntity, user);
			return user;
		}
		return user;
	}
	
	@Override
	public boolean deleteUserAccById(String id,String activeSW) {
		//UserDetails user=new UserDetails();
		
		int count= repo.deleteByIdAndActiveSW(id,activeSW);
		if(count==0)
			return false;
		else
		return true;
	}


	@Override
	public Page <UserEntity> getAllUserAccounts(int pageSize, int pageNo) {
		PageRequest page=PageRequest.of(pageNo, pageSize);
		Page<UserEntity> findAll= repo.findAll(page);
		return findAll;
	}


	@Override
	public List<UserDetails> getAllUsersByRole(String role) {
		List<UserDetails> listUser=new ArrayList<UserDetails>();
		List<UserEntity> listEntity=repo.findByRoleName(role);
		listEntity.forEach(entity->{
			UserDetails user=new UserDetails();
			BeanUtils.copyProperties(entity, user);
			listUser.add(user);
		});
		return listUser;
	}


}
