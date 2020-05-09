package com.payday.login.controller;

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

import com.payday.login.model.Post;
import com.payday.login.service.ApiService;
import com.payday.login.service.PostService;
import com.payday.common.dto.UserDTO;

@Controller
@RequestMapping(value="/ui/posts/")
public class PostController {
	
	private PostService postService;
	private ApiService apiService;
	
	@Autowired
	public PostController(PostService postService, ApiService apiService){
		this.postService = postService;
		this.apiService = apiService;
	}
	
	@GetMapping(value="/")
	public String posts(Model model){
		
		model.addAttribute("posts", postService.findAll());
		return "indexPosts";
	}
	
	
	@GetMapping(value="/add")
	public String addPost(Model model){
		Post post = new Post();
		model.addAttribute("post", post);
		
		return "addPost";
	}
	
	@PostMapping(value="/add")
	public String doAddPost(@Valid Post post, BindingResult result, Model model){
		
		if(result.hasErrors()){
			return "addPost";
		}
		
		postService.save(post);
		return "redirect:/ui/posts/";
	}
	
	
	@GetMapping(value="/edit/{id}")
	public String editPost(Model model, @PathVariable("id")Long id){
		Post post = postService.findByID(id);
		if(post != null){
			model.addAttribute("post", post);
			return "editPost";
		}
		
		return "redirect:/ui/posts/";
	}
	
	@PostMapping(value="/edit/{id}")
	public String doEditPost(@Valid Post post, BindingResult result, Model model, @PathVariable("id")Long id){
		
		if(result.hasErrors()){
			return "editPost";
		}
		
		postService.edit(post);
		return "redirect:/ui/posts/";
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String deletePost(Model model, @PathVariable("id")Long id){
		Post post = postService.findByID(id);
		if(post != null){
			postService.delete(post);
		}
		return "redirect:/ui/posts/";
	}
	
	@GetMapping("/allUsers")
	public String getAllUsers(Model model){
		
		List<UserDTO> users = apiService.getAllUsers();
		model.addAttribute("users", users);
		return "users"; 
		
	}

}
