package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashokit.domain.UserDetails;
import com.ashokit.service.UserMgmtService;

@Controller
public class UserController {
	
	@Autowired
	private UserMgmtService service;

	/*
	 * @InitBinder public void myBinder(ServletRequestDataBinder binder) {
	 * SimpleDateFormat sdf=null; sdf=new SimpleDateFormat("dd-MM-yyyy");
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,false)); }
	 */

	@RequestMapping({"/","/loadForm"})
	public String loadForm(Model model,@ModelAttribute("user") UserDetails userAcc) {
		
		 userAcc =new UserDetails();
		model.addAttribute("userAcc",userAcc);

		List<Object> roleName=service.getAllRoles();
		//Map<Object,Integer> roleMap=service.getAllRoles();
		//		model.addAttribute("role", roleMap);
		model.addAttribute("role",roleName);
		return "userRegisterFrom";
	}
	@PostMapping("/userAccReg")
	public String userAccRegistration(@ModelAttribute("user") UserDetails userAcc,Model model) {
		
		service.saveUserDetails(userAcc);
		
	return "userRegFormSuccess";
	}
	

}
