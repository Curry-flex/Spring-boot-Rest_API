package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	  Employee saveEmployee(Employee emp);
	  List<Employee> getEmployee();
	  Employee getEmpByID(int id);
	  Employee update(Employee emp ,int id);
	  void delete(int id);
	  
	
}
