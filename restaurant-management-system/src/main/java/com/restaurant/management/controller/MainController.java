package com.restaurant.management.controller;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurant.management.model.Menu;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.MenuRepository;
import com.restaurant.management.service.UserService;

@Controller
public class MainController {
	private UserService userService;
	private MenuRepository menuRepository;
	
	@Autowired
	public MainController(UserService userService, MenuRepository menuRepository) {
		this.userService = userService;
		this.menuRepository = menuRepository;
	}

	@GetMapping("/home")
	public String index() {
		return "index";
	}
	
	@GetMapping("/loginPage")
	public String home(Model model, Principal principal) {
		String username = principal.getName();
		List<Menu> menus = menuRepository.findAll();
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
		model.addAttribute("menus", menus);
		return "customerPage";
	}
	
	@PostMapping("/processOrder")
	public String processOrder(Model model, @RequestParam Map<String, String> requestParams, Principal  principal) {
		Map<String, Integer> order = new HashMap<>();
		String username = principal.getName();
		User user = userService.findByUsername(username).get();
		List<Menu> menus = menuRepository.findAll();
		List<Menu> customerOrder = new ArrayList<>();
		Double finalTotal = 0.0;
		
		requestParams.forEach((k, v) -> {
			try {
				if(Integer.parseInt(v) != 0) order.put(k, Integer.parseInt(v));
			} catch(NumberFormatException e) {
				e.getStackTrace();
			}});
		
		for(Menu menu : menus) {
			order.forEach((k, v) -> 
				{if(menu.getId().equals(k)) {
					menu.setQuantity(v);
					customerOrder.add(menu);
				};});	
		}
		
		for(Menu menu : customerOrder) {
			menu.setTotalPrice();
			finalTotal += menu.getTotalPrice();
		}
		
		model.addAttribute("finalTotal", finalTotal);
		model.addAttribute("user", user);
		model.addAttribute("customerOrder", customerOrder);
		return "processOrder";
	}
	
}
