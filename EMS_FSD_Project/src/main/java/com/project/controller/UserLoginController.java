package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.User;
import com.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {
	private final UserService service;
	
	public UserLoginController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/login")
	public String returnLoginPage() {
		System.out.println("Login page...");
		return "login";
	}


	@PostMapping("/login")
	public String login(@RequestParam String username,
			@RequestParam String password,HttpSession session,
			Model model) {
		
		User user = service.findUsernameAndPassword(username, password);
		
		if(user != null) {
			session.setAttribute("user",user);
			return "redirect:/home";
		}
		
		System.out.println("Invalid username and password...");
		model.addAttribute("error", "Invalid username and password");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("Logout...");
		session.invalidate();
		return "redirect:/login";
	}
}
