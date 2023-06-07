package com.buixuantu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.buixuantu.Service.EmployeeService;
import com.buixuantu.entity.EmployeeEntity;

@Controller
@RequestMapping("/parking")
	public class LoginController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping(value="home",params="btnLogin")
	public String btnLogin(ModelMap model,@RequestParam("uname") String name,@RequestParam("psw") String password)
	{
		System.out.println(name);
		System.out.println(password);
		if(name.equals("admin")==true && password.equals("123")==true) {
			return "admin";
		}
		else if(employeeService.login(name,password) != null) {
			EmployeeEntity emp = employeeService.findEmployeeById(name);
			model.addAttribute("employee",emp);
			return "home";
		}
		return "login";
	}
}
