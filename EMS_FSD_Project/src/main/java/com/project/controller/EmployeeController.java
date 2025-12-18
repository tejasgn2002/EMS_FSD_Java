package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Employee;
import com.project.model.User;
import com.project.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
	private final EmployeeService service;

	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/home/create")
	public String createPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login";
		}
		return "create";
	}
	
	@PostMapping("/home/create")
	public String createEmployee(@RequestParam String name,
			@RequestParam int age,
			@RequestParam int des,HttpSession session,Model model) {
		String designation="";
		double salary=0;
		System.out.println(name+":"+age+":"+des);
		
		int count = 0;
		for(int i = 0;i<name.length();i++) {
			if(name.charAt(i) == ' ') {
				count++;
			}
		}
		if(count > 2) {
			model.addAttribute("error","Name Can't have more than 2 Space...");	
			return "create";
		}
		if(!(age >= 20 && age <= 60)) {
			model.addAttribute("error","Enter the valid age...");	
			return "create";
		}

		switch(des) {
		case 1:
			salary = 20000;
			designation = "Programmer";
			break;
		case 2:
			salary = 25000;
			designation = "Manager";
			break;
		case 3:
			salary = 15000;
			designation = "Tester";
			break;
		}
		service.insertEmployee(new Employee(null, name, age, designation, salary));
		model.addAttribute("success","Inserted Successfully...");
		return "create";
	}
	
	@GetMapping("/home/display")
	public String displayPage(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login";
		}
		
		List<Employee> employees = service.fetchAllEmployee();
		model.addAttribute("employees", employees);
		return "display";
	}
	
	@GetMapping("/home/increment")
	public String incrementPage(HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			return "redirect:/login";
		}
		return "increment";
	}
	
	@GetMapping("/home/increment/hike")
	public String incrementByIdPage(@RequestParam int id,HttpSession session,Model model) {
		
		Optional<Employee> employee = service.fetchById(id);
		if(employee.isEmpty()) {
			model.addAttribute("error", "Id:"+id+" not found");
		}
		else {
			model.addAttribute("employee", (Employee) employee.get());//("employee", employee.get());
		}
		return "increment";
	}
	
	@PostMapping("/home/increment")
	public String percentageOfHikeForEmployee(@RequestParam int id,@RequestParam int increment,HttpSession session,Model model) {
		
		Optional<Employee> byId = service.fetchById(id);
		if(!(increment > 0 && increment <= 10)) {
			model.addAttribute("error", "Salary increment should be 1% to 10%");
			return "increment";
		}

		double salary = (byId.get().getSalary() + (byId.get().getSalary() * (increment/100.0)));
		byId.get().setSalary(salary);
		service.insertEmployee(byId.get());
		model.addAttribute("employee", (Employee) byId.get());
		model.addAttribute("success", "Salary incremented Successfully");

		return "increment";
	}
	
}
