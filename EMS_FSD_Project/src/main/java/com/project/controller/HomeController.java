package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String homePage(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			return "redirect:/login";
		}
		
		return "home";
	}

	
}
