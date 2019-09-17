package com.restaurant.management.controller;

import java.security.Principal;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurant.management.model.User;
import com.restaurant.management.service.UserService;

@Controller
public class MainController {
	
	private UserService userService;
	
	@Autowired
	public MainController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/home")
	public String index() {
		return "index";
	}
	
	@GetMapping("/loginPage")
	public String home(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username).get();
		model.addAttribute("user", user);
		
		LocalTime time = LocalTime.now();
		int hour = time.getHour();
		if(hour <= 12) {
			model.addAttribute("greetUser", "Good Morning, ");
		} else if(hour <= 18) {
			model.addAttribute("greetUser", "Good Afternoon, ");
		} else {
			model.addAttribute("greetUser", "Good Evening, ");
		}
		return "customerPage";
	}
	
}
