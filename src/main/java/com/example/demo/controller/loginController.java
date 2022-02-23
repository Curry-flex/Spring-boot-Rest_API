package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class loginController {
	
	@GetMapping("login")
	String getloginForm() {
		
		return "login";
	}
	
	@GetMapping("employee")
	String getloginEmployeeForm() {
		
		return "employee";
	}
	
	@GetMapping("logout")
	String getLogout() {
		
		return "login";
	}

}
