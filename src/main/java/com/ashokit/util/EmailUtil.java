package com.ashokit.util;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ashokit.domain.UserDetails;

@Component

public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;

	
	
	public  boolean sendUserAccUnlockEmail(UserDetails user) {
		boolean isSent=false;
		try {
			
			MimeMessage msg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(msg);
			helper.setTo(user.getEmailId());
			helper.setSubject("Unlock your account");
			helper.setText(getUnlockAccEmailBody(user),true);
		mailSender.send(msg);
			isSent=true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSent;
	}
	
	public String getUnlockAccEmailBody(UserDetails user) throws Exception {
		StringBuffer buffer=new StringBuffer("");
		FileReader reader=new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		BufferedReader br=new BufferedReader(reader);
		String line=br.readLine();
		while(line!=null) {
		buffer.append(line);
		line=br.readLine();
		}
		br.close();
		
		//format mailBody to String
		String mailBody=buffer.toString();
		mailBody=mailBody.replace("{FNAME}", user.getFirstName());
		mailBody=mailBody.replace("{LNAME}", user.getLastName());
		mailBody=mailBody.replace("{TEMP-PWD}",user.getPassword());
		mailBody=mailBody.replace("{EMAIL}", user.getEmailId());
		return mailBody;
	}
}
