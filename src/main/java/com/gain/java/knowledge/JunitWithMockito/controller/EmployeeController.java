package com.gain.java.knowledge.JunitWithMockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gain.java.knowledge.JunitWithMockito.model.Employee;
import com.gain.java.knowledge.JunitWithMockito.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@DeleteMapping(value = "/{employee-id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Employee deleteEmployee(
			@PathVariable(name = "employee-id") Integer employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
	@PatchMapping(value = "/{employee-id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@PathVariable(name = "employee-id") Integer employeeId,
			@RequestBody Employee employee) {
		return employeeService.updateEmployee(employeeId, employee);
	}
}
