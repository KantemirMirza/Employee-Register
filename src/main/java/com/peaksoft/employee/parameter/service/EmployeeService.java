package com.peaksoft.employee.parameter.service;

import com.peaksoft.employee.parameter.model.Employee;
import com.peaksoft.employee.parameter.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	
	//Get All Employees
	public List<Employee> listOfEmployee() {
		return employeeRepository.findAll();
	}
	
	//Get Employee By Id
	public Employee findEmployeeById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}	
	
	//Delete Employee
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	//Update Employee
	public void saveEmployee( Employee employee) {
		employeeRepository.save(employee);
	}

    //Get Employee by username
//	public Employee findByUsername(String un) {
//		return employeeRepository.findByUsername(un);
//	}

//	//Get employee by Keyword
//	public List<Employee> findByKeyword(String keyword) {
//		return employeeRepository.findByKeyword(keyword);
//	}

}
