package com.buixuantu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buixuantu.Service.EmployeeService;
import com.buixuantu.entity.EmployeeEntity;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/getList")
	public Iterable<EmployeeEntity> getListEmployee(){
		return employeeService.getAllEmployee();
	}
}
