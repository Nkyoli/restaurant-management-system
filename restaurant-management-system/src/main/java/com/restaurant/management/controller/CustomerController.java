package com.restaurant.management.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.management.dto.UserDto;
import com.restaurant.management.model.User;
import com.restaurant.management.service.UserService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private UserService userService;

	@Autowired
	public CustomerController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "registrationPage";
	}

	@PostMapping("/proccessRegistration")
	public String proccessRegistrationForm(@Valid @ModelAttribute("userDto") UserDto userDto,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "registrationPage";
		}
		Optional<User> optionalUser = userService.findByUsername(userDto.getUsername());

		if (optionalUser.isEmpty()) {
			userService.save(userDto);
			model.addAttribute("registrationSuccess", "You successfully Registered!");
			return "index";
		}
		model.addAttribute("registrationError", "User already Exists!");
		return "registrationPage";
	}
}
