package com.payday.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payday.auth.service.ApiService;
import com.payday.auth.service.UserService;
import com.payday.common.dto.PostDTO;

@Controller
@RequestMapping("/ui/public")
public class PublicController {
	
	private UserService userService;
	private ApiService apiService;
	
	@Autowired
	public PublicController(UserService userService, ApiService apiService){
		this.userService = userService;
		this.apiService = apiService;
	}

	@GetMapping("/users")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "usersIndex";
	}
	
	@GetMapping("/posts")
	public String getActiveUsers(Model model){
		
		//Get the latest Posts from the basic service using the client credentials Flow
		List<PostDTO> posts = apiService.getPostsClientFlow();
		model.addAttribute("posts", posts);
		return "posts"; 
	}
}
