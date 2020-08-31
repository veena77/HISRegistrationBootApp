package com.ashokit.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.constant.ConstantValues;
import com.ashokit.domain.UserDetails;
import com.ashokit.entity.UserEntity;
import com.ashokit.service.UserMgmtService;

@Controller
public class ViewAccountController {

	@Autowired
	private UserMgmtService service;

	@RequestMapping(value = "/viewAccounts")
	public String viewAccountForm(@ModelAttribute("user") UserDetails user,Map<String,Object> map,HttpServletRequest req) throws Exception {

		int pageSize=2;
		int currPno=1;
		String role="CASE WORKER";
		String roleStr=req.getParameter("role");
		String pno=req.getParameter("pno");
		if(!(pno==null)&&!pno.equals(""))
			currPno=Integer.parseInt(pno);
		if(!(roleStr==null)&&!roleStr.equals(role))
			role=roleStr;

		Page<UserEntity> pages=service.getAllUserAccounts(pageSize, currPno-1);
		List<UserDetails> listByRole=service.getAllUsersByRole(role);
		List<UserEntity> listEntity=pages.getContent();
		int totalPages=pages.getTotalPages();

		List<UserDetails> listUser=	listEntity.stream().map(entity->{
			UserDetails userDetails=new UserDetails();
			BeanUtils.copyProperties(entity, userDetails);
			return userDetails;
		}).collect(Collectors.toList());
		map.put("list",listUser);
		map.put("tp", totalPages);
		map.put("cpn",currPno);//removes hyper link for current page
		map.put("role",listByRole);
		return "viewUsersForm";
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("id")String userId,@ModelAttribute("user") UserDetails user,Map<String,String> map) {

		boolean isdeleted=service.deleteUserAccById(userId, ConstantValues.SWITCH_OFF);
		if(isdeleted) {
			map.put("resMsg", user.getFirstName()+" "+user.getLastName()+" is deleted");
			return "redirect:/viewAccounts";
		}
		return null;
	}

	@RequestMapping("/activeUser")
	public String activateUser(@RequestParam("id") String userId,@ModelAttribute("user") UserDetails user,Map<String,String> map) {
		boolean isActivated=service.deleteUserAccById(userId, ConstantValues.SWITCH_ON);
		if(isActivated) {
			map.put("Msg",user.getFirstName()+" "+user.getLastName()+" is Activated");
			return "redirect:/viewAccounts";
		}
		return null;
	}

	@RequestMapping("/editUser")
	public String editUserDetails(HttpServletRequest req,Model model) {
		String id=req.getParameter("id");
		UserDetails user=service.getUserById(id);
		
			model.addAttribute("user",user);
		
		return "";
	}

}
