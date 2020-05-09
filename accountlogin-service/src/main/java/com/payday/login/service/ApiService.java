package com.payday.login.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.payday.login.feign.AuthServiceClientFlow;
import com.payday.login.feign.AuthServiceFeign;
import com.payday.common.dto.UserDTO;

@Service
public class ApiService {
	
	private AuthServiceFeign authServiceFeign;
	private AuthServiceClientFlow authServiceClientFlow;

	@Autowired
	public ApiService(AuthServiceFeign authServiceFeign, AuthServiceClientFlow authServiceClientFlow){
		this.authServiceFeign = authServiceFeign;
		this.authServiceClientFlow = authServiceClientFlow;
	}

	@HystrixCommand(fallbackMethod="getUsersFallBack")
	public List<UserDTO> getAllUsers(){
		return authServiceFeign.getAllUsers();
	}
	
	@HystrixCommand(fallbackMethod="getUsersFallBack")
	public List<UserDTO> getActiveUsers(){
		return authServiceClientFlow.getActiveUsers();
	}
	
	public List<UserDTO> getUsersFallBack(){
		UserDTO dummyUser = new UserDTO();
		dummyUser.setFirstName("Fallback User");
		return new ArrayList<UserDTO>(Arrays.asList(dummyUser));
	}
}