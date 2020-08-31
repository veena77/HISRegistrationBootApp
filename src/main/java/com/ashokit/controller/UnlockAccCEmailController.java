package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.domain.UnlockAccount;
import com.ashokit.domain.UserDetails;
import com.ashokit.service.UserMgmtService;
import com.ashokit.util.PwdUtil;

@Controller
public class UnlockAccCEmailController {

	@Autowired
	private UserMgmtService service;

	
		@GetMapping("/unlockAcc")
		public String displayUnlockForm(@RequestParam("email") String email,Model model) {
		model.addAttribute("email", email);
		
		UnlockAccount unlockAcc=new  UnlockAccount();
		unlockAcc.setEmail(email);			
		//two binding 
		model.addAttribute("unlockAcc",unlockAcc);
		
		return "UnlockAccForm";
	}
		/*create sequence CANDIDATE_SEQ
start with 1000000
increment by 1
maxvalue 9999999;*/
	
	@PostMapping("/unlockUserAcc")
	public String unlockUserAcc(@ModelAttribute("unlockAcc") UnlockAccount unlockAcc, Model model) {
			
				UserDetails userAcc=service.getUserAccByPwd(unlockAcc.getTempPwd());
		if(userAcc!=null) {
			//update acc_Status and password
			userAcc.setStatus("Un-Locked");
			userAcc.setPassword(unlockAcc.getNewPwd());
			boolean isUpdate=service.updateUserAccount(userAcc);
			if(isUpdate)
			return "UnlockAccSuccess";
		}
			//dispaly error msg on the same page
		model.addAttribute("errMsg","Please enter Correct Password");
		
		return "UnlockAccForm";
	}
	
}