package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/addEmployee")
public class EmployeeController {
	
	private EmployeeService service;

	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}
	
	//Build create REST employee API
	
	@PostMapping
	@PreAuthorize("hasAuthority('employee:write')")
	public ResponseEntity<Employee> save(@RequestBody Employee emp){
		
		return new ResponseEntity<Employee>(service.saveEmployee(emp), HttpStatus.CREATED);
	}
	
	//build  get All employee API
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN','ROLE_ADMININTERN')")
  public List<Employee> getEmployee(){
	  
	  return service.getEmployee();
  }
    
    //find employee by ID
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN','ROLE_ADMININTERN')")
    ResponseEntity<Employee> getEmployee(@PathVariable("id") int id){
    	
    	return new ResponseEntity<Employee>(service.getEmpByID(id), HttpStatus.OK);
    }



//find employee by ID
@PutMapping("{id}")
@PreAuthorize("hasAuthority('employee:write')")
ResponseEntity<Employee> update(@PathVariable("id") int id, @RequestBody Employee emp){
	
	return new ResponseEntity<Employee>(service.update(emp, id), HttpStatus.OK);
}

//Delete employee
@DeleteMapping("{id}")
@PreAuthorize("hasAuthority('employee:write')")
ResponseEntity<String> delete(@PathVariable("id") int id){
	
	service.delete(id);
	
	return new ResponseEntity<String>("Delete successfully!.", HttpStatus.OK);
}
}


