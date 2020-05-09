package com.payday.auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payday.auth.model.User;
import com.payday.auth.service.ApiService;
import com.payday.auth.service.UserService;
import com.payday.common.dto.PostDTO;

@Controller
@RequestMapping("/ui/users")
public class UserController {
	
	private UserService userService;
	private ApiService apiService;
	
	@Autowired
	public UserController(UserService userService, ApiService apiService){
		this.userService = userService;
		this.apiService = apiService;
	}
	
	@GetMapping("/add")
	public String addUser(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "addUser";
	}
	
	@PostMapping("/add")
	public String doAddUser(Model model, @Valid User user, BindingResult result){
		if(result.hasErrors()){
			return "addUser";
		}
		
		userService.registerUser(user);
		return "redirect:/ui/users/";
	}
	
	@GetMapping("/")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "usersIndex";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editUser(Model model, @PathVariable("id") Long id){
		User user = userService.findByID(id);
		model.addAttribute("user", user);
		return "editUser";
	}
	
	@PostMapping("/edit/{id}")
	public String doEditUser(Model model, @Valid User user, BindingResult result, @PathVariable("id") Long id){
		if(result.hasErrors()){
			return "editUser";
		}
		
		userService.save(user);
		return "redirect:/ui/users/";
	}
	
	@GetMapping("/posts")
	public String posts(Model model){
		
		//Get the latest Posts from the basic service
		List<PostDTO> posts = apiService.getLatestPosts();
		model.addAttribute("posts", posts);
		
		return "posts";
	}
	

}
