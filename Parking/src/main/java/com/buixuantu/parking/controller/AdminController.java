package com.buixuantu.parking.controller;

import com.buixuantu.parking.entity.EmployeeEntity;
import com.buixuantu.parking.entity.TicketEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buixuantu.parking.Service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/parking")
public class AdminController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/admin")
	public String admin(ModelMap model) {
		model.addAttribute("employees",employeeService.getAllEmployee());
		return "admin";
	}
	
	@PostMapping(value="admin",params="btn_add_employee")
	public String addEmployee(ModelMap model,@RequestParam("ip_id") String id,@RequestParam("ip_role") String role,@RequestParam("ip_name") String name,@RequestParam("ip_password") String password){
		if(employeeService.findEmployeeById(id)!=null) {
			model.addAttribute("employees",employeeService.getAllEmployee());
			model.addAttribute("mes","Invalid Id");
			return "admin";
		}
		employeeService.addEmployee(id,role,name,password);
		model.addAttribute("employees",employeeService.getAllEmployee());
		return "admin";
	}
	
	@PostMapping(value="admin",params="btn_update_employee")
	public String updateEmployee(ModelMap model,@RequestParam("ip_id") String id,@RequestParam("ip_role") String role,@RequestParam("ip_name") String name,@RequestParam("ip_password") String password){
		if(employeeService.findEmployeeById(id)==null) {
			model.addAttribute("employees",employeeService.getAllEmployee());
			model.addAttribute("mes","Id Not Found");
			return "admin";
		}
		employeeService.updateEmployeeById(id,role,name,password);
		model.addAttribute("employees",employeeService.getAllEmployee());
		return "admin";
	}
	
	@PostMapping(value="admin",params="btn_delete_employee")
	public String deleteEmployee(ModelMap model,@RequestParam("ip_id") String id) {
		if(employeeService.findEmployeeById(id)==null) {
			model.addAttribute("employees",employeeService.getAllEmployee());
			model.addAttribute("mes","Id Not Found");
			return "admin";
		}
		else if(employeeService.findEmployeeById(id).getTicketList()!=null) {
			model.addAttribute("employees",employeeService.getAllEmployee());
			model.addAttribute("mes","Cannot Delete");
			return "admin";
		}
		employeeService.deleteEmployeeById(id);
		model.addAttribute("employees",employeeService.getAllEmployee());
		return "admin";
	}

	@PostMapping(value = "admin", params = "btn_search_employee")
	public String btnSearch(HttpServletRequest request, ModelMap model, @RequestParam("search_employee") String id) {
		HttpSession session = request.getSession();
		if (id.equals("") == true) {
			model.addAttribute("employees", employeeService.getAllEmployee());
			return "admin";
		} else if (employeeService.findEmployeeById(id) == null) {
			List<TicketEntity> list = new ArrayList<>();
			model.addAttribute("employees", list);
			return "admin";
		} else {
			List<EmployeeEntity> list = new ArrayList<>();
			list.add(employeeService.findEmployeeById(id));
			model.addAttribute("employees", list);
			return "admin";
		}
	}
}
