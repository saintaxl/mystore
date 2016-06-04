/**
 * 
 */
package com.mycloud.store.mail;

import java.util.Properties;

import javax.activation.FileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author Shawn
 *
 */

@Component
public class EmailAppConfig {

	@Value("#{'${email.host}'}")
	private String emailHost;

	@Value("#{'${email.username}'}")
	private String userName;

	@Value("#{'${email.password}'}")
	private String password;

	@Value("#{'${mail.smtp.auth}'}")
	private String mailAuth;

	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl ms = new JavaMailSenderImpl();
		ms.setHost(emailHost);
		ms.setUsername(userName);
		ms.setPassword(password);
		Properties pp = new Properties();
		pp.setProperty("mail.smtp.auth", mailAuth);
		ms.setJavaMailProperties(pp);
		ms.setDefaultEncoding("UTF-8");
		//FileTypeMap defaultFileTypeMap = FileTypeMap.getDefaultFileTypeMap();
		//defaultFileTypeMap.setDefaultFileTypeMap(null);
		//ms.setDefaultFileTypeMap(defaultFileTypeMap);
		return ms;
	}
}
