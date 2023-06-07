package com.buixuantu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buixuantu.Service.EmployeeService;

@Controller
@RequestMapping("/parking")
public class AdminController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/admin")
	public String admin(ModelMap model) {
		model.addAttribute("employees",employeeService.getAllEmployee());
		return "admin";
	}
	
	@PostMapping(value="admin",params="btn_add_employee")
	public String addEmployee(@RequestParam("ip_id") String id,@RequestParam("ip_name") String name,@RequestParam("ip_password") String password){
		employeeService.addEmployee(id,name,password);
		return "admin";
	}
	
	@PostMapping(value="admin",params="btn_update_employee")
	public String updateEmployee(@RequestParam("ip_id") String id,@RequestParam("ip_name") String name,@RequestParam("ip_password") String password){
		employeeService.updateEmployeeById(id,name,password);
		return "admin";
	}
	
	@PostMapping(value="admin",params="btn_delete_employee")
	public String updateEmployee(@RequestParam("ip_id") String id) {
		employeeService.deleteEmployeeById(id);
		return "admin";
	}
}
