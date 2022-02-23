package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNofFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService  {
     private EmployeeRepository res;
     
	public EmployeeServiceImp(EmployeeRepository res) {
		super();
		this.res = res;
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return res.save(emp);
	}

	@Override
	public List<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return res.findAll();
	}

	@Override
	public Employee getEmpByID(int id) {
		// TODO Auto-generated method stub
		return res.findById(id).orElse(new Employee());
	}

	@Override
	public Employee update(Employee emp, int id) {
	   Employee existingemployee =res.findById(id).orElseThrow(()-> new ResourceNofFoundException("Employee", "id", id));
	   existingemployee.setFirstname(emp.getFirstname());
	   existingemployee.setLastname(emp.getLastname());
	   existingemployee.setEmail(emp.getEmail());
	   
	   res.save(existingemployee);
		return existingemployee;
	}

	@Override
	public void delete(int id) {
		//check if employee with that id exist
		res.findById(id).orElseThrow(()->new ResourceNofFoundException("Employee", "ID", id));
		res.deleteById(id);
		
	}

	

}
