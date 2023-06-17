package com.buixuantu.parking.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.buixuantu.parking.Service.EmployeeService;
import com.buixuantu.parking.Service.TicketService;
import com.buixuantu.parking.entity.EmployeeEntity;
import com.buixuantu.parking.middlerware.MiddlewareLogin;
import com.buixuantu.parking.middlerware.RoleMiddleware;
import com.buixuantu.parking.middlerware.UserMiddleware;

@Controller
@RequestMapping("/parking")
	public class LoginController{
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/login")
	public String login()
	{	
		return "login";
	}
	
	@RequestMapping(value="home",params="btnLogin",method=RequestMethod.POST)
	public String btnLogin(ModelMap model, HttpSession session, @RequestParam("uname") String name, @RequestParam("psw") String password)
	{
		System.out.println(name);
		System.out.println(password);
		MiddlewareLogin middleware = new UserMiddleware(name,password);
		middleware.setNextChain(new RoleMiddleware());
		System.out.println(middleware);
		
		if(name.equals("Admin")==true && password.equals("123")==true) {
			model.addAttribute("employees",employeeService.getAllEmployee());
			return "admin";
		}
		else if(employeeService.login(name,password) != null) {
			EmployeeEntity emp = employeeService.findEmployeeById(name);
			model.addAttribute("id_employee",emp.getId());
			session.setAttribute("id_employee", emp.getId());
			model.addAttribute("tickets",ticketService.getAllTicket());
			return "home";
		}
		return "login";
	}
}
