package com.payday.auth.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	
	public HomeController(){
	}
	
	@GetMapping("/")
	public String home(Model model){
		
		return "home";
	}
}
