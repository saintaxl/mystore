/**
 * 
 */
package com.mycloud.store.controller.portal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycloud.entity.Customer;
import com.mycloud.entity.Role;
import com.mycloud.entity.User;
import com.mycloud.entity.UserValidate;
import com.mycloud.enums.State;
import com.mycloud.exception.BusinessException;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.exception.ErrorCode;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.service.CustomerService;
import com.mycloud.store.service.EmailService;
import com.mycloud.store.service.UserService;

/**
 * @author Shawn
 *
 */

@Controller
public class RegisterController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;

	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/register.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerUser(@RequestParam(value = "customername", required = true) String customername,
	        @RequestParam(value = "logonname", required = true) String username, @RequestParam(value = "email", required = true) String email,
	        @RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "newpassword", required = true) String password,Model model) {
		
		User user = new User();
		user.setEmail(email);
		user.setMobile(mobile);
		
		String hashedPassword = passwordEncoder.encode(password);
		
		user.setUsername(username);
		Customer customer = customerService.addCustomer(customername);
		user.setCustomer(customer);
		user.setPassword(hashedPassword);
		user.setState(State.INACTIVE.getState());
		String validataCode = UUID.randomUUID().toString();
		Date after30 = DateUtils.addMinutes(new Date(), 30);
		UserValidate validate = new UserValidate();
		validate.setRegisterDate(after30);
		validate.setValidataCode(validataCode);
		validate.setUser(user);
		user.setValidate(validate);
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			response.setHeader("errorPage", "logon");
			response.setHeader("fromPage", "register");
			throw e;
		}
		
		
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMddHHmm");
		String formatdate = dateformat.format(user.getValidate().getRegisterDate());
		
        String key = user.getEmail()+"$"+formatdate+"$"+validataCode;
        String digitalSignature = DigestUtils.md5Hex(key);

        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String validatelink =  basePath+"validateMail.htm?sid="+digitalSignature+"&email="+user.getEmail();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("validatelink", validatelink);
		
		emailService.sendWithTemplate("云立方注册用户邮箱验证", email, "/email/email_verify.vm", map);

		//return "redirect:homepage.htm";
		model.addAttribute("mail", user.getEmail());
		return "/email/sendRegisterMail";
	}
	
	@RequestMapping(value = "/validateMail.htm", method = { RequestMethod.GET })
	public String validateMail(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "sid", required = true) String sid,Model model){
        if(StringUtils.isEmpty(sid) || StringUtils.isEmpty(email)){
        	throw new BusinessException(ErrorCode.EMAIL_OR_SID_INCOMPLETE);
        }
        User user = userService.findUserByEmail(email);
        if(user == null){
        	throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND,email);
        }
        Date registerDate = user.getValidate().getRegisterDate();
        if(registerDate.getTime() <= System.currentTimeMillis()){
        	throw new BusinessException(ErrorCode.VALIDATE_MAIL_TIMEOUT);
        }
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMddHHmm");
		String formatdate = dateformat.format(registerDate.getTime());
        
        String key = user.getEmail()+"$"+formatdate+"$"+user.getValidate().getValidataCode();
        String digitalSignature = DigestUtils.md5Hex(key);
        if(!digitalSignature.equals(sid)) {
        	throw new BusinessException(ErrorCode.VALIDATE_MAIL_TIMEOUT);
        }
        
        user.setState(State.ACTIVE.getState());
        userService.update(user);
        
        model.addAttribute("mail", user.getEmail());
		return "/email/validateMail";
	}
	
	@RequestMapping(value = "/resendMail.htm", method = { RequestMethod.GET })
	public String resendMail(@RequestParam(value = "email", required = true) String email, Model model){
        if(StringUtils.isEmpty(email)){
        	throw new BusinessException(ErrorCode.EMAIL_IS_EMPTY);
        }
        User user = userService.findUserByEmail(email);
        if(user == null){
        	throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND,email);
        }
        Date after30 = DateUtils.addMinutes(new Date(), 30);
        UserValidate validate = user.getValidate();
        validate.setRegisterDate(after30);
        userService.update(user);
        
        
        String validataCode = validate.getValidataCode();
        
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMddHHmm");
		String formatdate = dateformat.format(user.getValidate().getRegisterDate());
		
        String key = user.getEmail()+"$"+formatdate+"$"+validataCode;
        String digitalSignature = DigestUtils.md5Hex(key);

        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String validatelink =  basePath+"validateMail.htm?sid="+digitalSignature+"&email="+user.getEmail();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("validatelink", validatelink);
		
		emailService.sendWithTemplate("云立方-注册用户邮箱验证", email, "/email/email_verify.vm", map);

		model.addAttribute("mail", user.getEmail());
		return "/email/sendRegisterMail";
     
	}
	
	@RequestMapping(value = "/forgotPassword.htm", method = { RequestMethod.POST })
	public String forgotPassword(@RequestParam(value = "forgotemail", required = true) String email, Model model){
		if(StringUtils.isEmpty(email)){
        	response.setHeader("errorPage", "logon");
			response.setHeader("fromPage", "forgot");
        	throw new BusinessException(ErrorCode.EMAIL_IS_EMPTY);
        }
        User user = userService.findUserByEmail(email);
        if(user == null){
        	response.setHeader("errorPage", "logon");
			response.setHeader("fromPage", "forgot");
        	throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND,email);
        }
        UserValidate validate = user.getValidate();
        String validataCode = validate.getValidataCode();
		
        String key = user.getEmail()+"$"+validataCode;
        String digitalSignature = DigestUtils.md5Hex(key);

        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String resetlink =  basePath+"resetPassword.htm?sid="+digitalSignature+"&email="+user.getEmail();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("resetlink", resetlink);
		
		emailService.sendWithTemplate("云立方-用户登录密码重置邮件", email, "/email/forgot_password.vm", map);

		model.addAttribute("mail", user.getEmail());
		return "/email/sendForgotMail";
	}
	
	@RequestMapping(value = "/resetPassword.htm", method = { RequestMethod.GET })
	public String resetPassword(@RequestParam(value = "email", required = true) String email,@RequestParam(value = "sid", required = true) String sid, Model model){
		if(StringUtils.isEmpty(email)){
        	throw new BusinessException(ErrorCode.EMAIL_IS_EMPTY);
        }
        User user = userService.findUserByEmail(email);
        if(user == null){
        	throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND,email);
        }
        String key = user.getEmail()+"$"+user.getValidate().getValidataCode();
        String digitalSignature = DigestUtils.md5Hex(key);
        if(!digitalSignature.equals(sid)) {
        	throw new BusinessException(ErrorCode.VALIDATE_MAIL_TIMEOUT);
        }

		model.addAttribute("email", user.getEmail());
		return "/email/resetPassword";
	}
	
	
	@RequestMapping(value = "/resetLogonPassword.htm", method = { RequestMethod.POST })
	public String resetLogonPassword(@RequestParam(value = "email", required = true) String email,@RequestParam(value = "newpassword", required = true) String password, Model model){
		if(StringUtils.isEmpty(email)){
        	throw new BusinessException(ErrorCode.EMAIL_IS_EMPTY);
        }
        User user = userService.findUserByEmail(email);
        if(user == null){
        	throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND,email);
        }
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        userService.update(user);
        
		return "/email/successResetPassword";
	}
	
	
	
	
	public void autoLogon(String email){
		
		User user = userService.findUserByEmail(email);
		String hashedPassword = user.getPassword();
		
		
		UserDetails customUserDetails = new CustomUserDetails(user);

		// UsernamePasswordAuthenticationToken authentication = new
		// UsernamePasswordAuthenticationToken(email, hashedPassword);
		// authentication.setAuthenticated(true);

		Set<SimpleGrantedAuthority> authoritys = new HashSet<SimpleGrantedAuthority>();
		for (Role role : user.getRoles()) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
			authoritys.add(authority);
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails, hashedPassword, authoritys);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
