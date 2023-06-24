package com.buixuantu.parking.controller;

import com.buixuantu.parking.Service.WarehouseService;
import com.buixuantu.parking.entity.TicketEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	@Autowired
	private WarehouseService warehouseService;
	
	@GetMapping("/login")
	public String login()
	{
		warehouseService.checkTimeTicket();
		return "login";
	}
	
	@RequestMapping(value="home",params="btnLogin",method=RequestMethod.POST)
	public String btnLogin(ModelMap model, HttpSession session, @RequestParam("uname") String name, @RequestParam("psw") String password)
	{
		System.out.println(name);
		System.out.println(password);
		
		if(employeeService.login(name,password).getId().equals(name)==true && employeeService.login(name,password).getPassword().equals(password)==true && employeeService.login(name,password).getRole().getName().equals("Admin")==true) {
			EmployeeEntity emp = employeeService.findEmployeeById(name);
			model.addAttribute("id_employee",emp.getId());
			session.setAttribute("id_employee", emp.getId());
			Sort sort = Sort.by("id");
			Pageable pageable = PageRequest.of(0, 5,sort);
			Page<EmployeeEntity> page = employeeService.findAllPage(pageable);
			model.addAttribute("employees",page);
			model.addAttribute("p",0);
			return "admin";
		}
		else if(employeeService.login(name,password) != null) {
			System.out.println("zo day");
			EmployeeEntity emp = employeeService.findEmployeeById(name);
			Sort sort = Sort.by("id");
			Pageable pageable = PageRequest.of(0, 5,sort);
			model.addAttribute("id_employee",emp.getId());
			session.setAttribute("id_employee", emp.getId());
			System.out.println("zo day 1");
			Page<TicketEntity> page = ticketService.findAllPage(pageable);
			System.out.println("zo day 2");
			model.addAttribute("tickets",page);
			model.addAttribute("p",0);
			return "home";
		}
		else{
			return "login";
		}
	}
}
