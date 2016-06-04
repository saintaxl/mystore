/**
 * 
 */
package com.mycloud.store.service;

import java.util.Date;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.mycloud.exception.BusinessException;
import com.mycloud.store.exception.ErrorCode;

/**
 * @author Shawn
 *
 */

@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	 
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Value("#{'${email.from}'}")
	private String from;
	
	public void sendWithTemplate(String subject,String toEmail,String templateName, Map model) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        String text = null;  
        try {  
        	messageHelper.setFrom(from);
        	messageHelper.setTo(toEmail);    
        	messageHelper.setSubject(subject);
        	messageHelper.setSentDate(new Date());
        	text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8",model);  
        	messageHelper.setText(text,true);
        	emailSender.send(mimeMessage);;  
        } catch (Exception e) {
        	throw new BusinessException(ErrorCode.SEND_REGISTER_EMAIL_ERROR);
        }  
    }  
    

}
