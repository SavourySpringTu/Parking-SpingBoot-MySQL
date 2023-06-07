package com.buixuantu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buixuantu.Service.PositionsService;
import com.buixuantu.Service.RevenueService;
import com.buixuantu.Service.TicketService;
import com.buixuantu.entity.TicketEntity;
import com.buixuantu.entity.PositionsEntity;
import com.buixuantu.entity.EmployeeEntity;
import com.buixuantu.entity.RevenueEntity;

@Controller
@RequestMapping("/parking")
public class HomeController {
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping("/home")
	public String listTicket(ModelMap model) {
		System.out.println("toi day ne");
		model.addAttribute("tickets",ticketService.getAllTicket());
		return "home";
	}
	@PostMapping(value="home",params="btn_confirm_ticket")
	public String addTicket(@RequestParam("ip_id") String id,@RequestParam("ip_price") int price,@RequestParam("ip_type") boolean type,@RequestParam("ip_number") int number,@RequestParam("ip_position") String position) {
		String a= "NV01";
		ticketService.addTicket(id,a,price,type,position,number);
		return "home";
	}
	@PostMapping(value="home",params="btn_update_ticket")
	public String updateTicket(@RequestParam("ip_id") String id,@RequestParam("ip_price") int price,@RequestParam("ip_type") boolean type,@RequestParam("ip_number") int number) {
		ticketService.updateTicket(id, number,price, type);
		return "home";
	}
	@PostMapping(value="home",params="btn_export_ticket")
	public String exportTicket(@RequestParam("ip_id") String id) {
		ticketService.exportTicket(id);
		return "home";
	}
}
