package com.gain.java.knowledge.JunitWithMockito.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.gain.java.knowledge.JunitWithMockito.model.Employee;

@Repository
public class EmployeeDao {

	List<Employee> employees = new CopyOnWriteArrayList<Employee>();

	public List<Employee> getEmployees() {
		return employees;
	}

	public Employee saveEmployee(Employee employee) {
		employees.add(employee);
		return employee;
	}

	public Employee deleteEmployee(Integer employeeId) {
		Employee response = null;
		for (Employee employee : employees) {
			if (employee.getId() == employeeId) {
				employees.remove(employee);
				response = employee;
			}
		}
		return response;
	}

	public Employee updateEmployee(Integer employeeId, Employee updateEmployee) {
		Employee response = null;
		for (Employee employee : employees) {
			if (employee.getId() == employeeId) {
				employees.remove(employee);
				response = updateEmployee(employee, updateEmployee);
				employees.add(response);
			}
		}
		return response;
	}

	private Employee updateEmployee(Employee employee, Employee updateEmployee) {
		if(updateEmployee.getId() >= 0)
			employee.setId(updateEmployee.getId());
		if(updateEmployee.getName() != null && !updateEmployee.getName().trim().equalsIgnoreCase(""))
			employee.setName(updateEmployee.getName());
		if(updateEmployee.getMessage() != null && !updateEmployee.getMessage().trim().equalsIgnoreCase(""))
			employee.setMessage(updateEmployee.getMessage());
		return employee;
	}

}
