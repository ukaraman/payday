package com.payday.auth.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payday.auth.model.User;
import com.payday.auth.service.UserService;
import com.payday.common.config.UserPrincipal;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	private UserService userService;
	
	@Autowired
	public ApiController(UserService userService){
		this.userService = userService;
	}
		
//	First Name, Last Name, Phone Number,
//	Email Address, Password, Gender, Date of Birth
	
	@GetMapping("/user")
	public @ResponseBody Map<String, Object> user(Principal principal){
		Map<String, Object> userInfo = new HashMap<String, Object>();
		UserPrincipal userPrincipal = userService.getCurrentUser();
		
		userInfo.put("firstName", userPrincipal == null? "" : userPrincipal.getUsername());
		userInfo.put("lastName", userPrincipal == null? "" : userPrincipal.getUsername());
		userInfo.put("email", userPrincipal == null? "" : userPrincipal.getEmail());
		userInfo.put("isEnabled", userPrincipal == null? "" : userPrincipal.isEnabled());
		userInfo.put("authorities", userPrincipal == null? "" : userPrincipal.getAuthorities());
		userInfo.put("user", principal.getName());
		
		return userInfo;
	}
	
	@GetMapping("/activeUsers")
	public @ResponseBody List<User> getPublicUsers(){
		return userService.getActiveUsers();
	}
	
	
	@GetMapping("/allUsers")
	public @ResponseBody List<User> getAllUsers(){
		return userService.findAll();
	}

}
