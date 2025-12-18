package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.model.Employee;
import com.project.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository repo;

	public EmployeeService(EmployeeRepository repo) {
		this.repo = repo;
	}
	
	public Employee insertEmployee(Employee employee) {
		return repo.save(employee);
	}
	
	public List<Employee> fetchAllEmployee(){
		return repo.findAll();
	}
	
	public Optional<Employee> fetchById(int id) {
		return repo.findById((Integer) id);
	}
	
}
