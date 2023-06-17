package com.buixuantu.parking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buixuantu.parking.Service.PositionsService;
import com.buixuantu.parking.Service.RevenueService;
import com.buixuantu.parking.Service.TicketService;
import com.buixuantu.parking.entity.TicketEntity;
import com.buixuantu.parking.entity.PositionsEntity;
import com.buixuantu.parking.entity.EmployeeEntity;
import com.buixuantu.parking.entity.RevenueEntity;

@Controller
@RequestMapping("/parking")
public class HomeController {
	@Autowired
	private TicketService ticketService;
	@Autowired
	private PositionsService positionService;
	@Autowired
	private RevenueService revenueService;
	
	@GetMapping("/home")
	public String home(HttpServletRequest request, ModelMap model){
		HttpSession session = request.getSession();
		if(session.getAttribute("id_employee")==null)
		{
			return "login";
		}
		ListAndId(request, model);
		return "home";
	}
	
	@PostMapping(value="home",params="btn_confirm_ticket")
	public String addTicket(HttpServletRequest request,ModelMap model, @RequestParam("ip_id") String id,@RequestParam("ip_price") int price,@RequestParam("ip_type") boolean type,@RequestParam("ip_number") int number,@RequestParam("ip_position") String position) {
		HttpSession session = request.getSession();
		if(ticketService.findTicketById(id) != null || positionService.findPositionById(position)==null || ticketService.checkTicket(number)==false || positionService.checkPosition(position)==false) {
			model.addAttribute("tickets",ticketService.getAllTicket());
			model.addAttribute("id_employee",(String )session.getAttribute("id_employee"));
			return "home";
		}

		ticketService.addTicket(id,(String )session.getAttribute("id_employee"),price,type,position,number);
		revenueService.updateRevenueDay();
		model.addAttribute("tickets",ticketService.getAllTicket());
		model.addAttribute("id_employee",(String )session.getAttribute("id_employee"));
		return "home";
	}
	
	@PostMapping(value="home",params="btn_update_ticket")
	public String updateTicket(HttpServletRequest request,ModelMap model, @RequestParam("ip_id") String id,@RequestParam("ip_price") int price,@RequestParam("ip_type") boolean type,@RequestParam("ip_number") int number,@RequestParam("ip_position") String position) {
		LocalDate d =LocalDate.now();
		if(ticketService.findTicketById(id).isStatus()==true) {
			ListAndId(request, model);
			model.addAttribute("mes","Ticket has been Export");
			return "home";
		}
		else if(ticketService.findTicketById(id).getPrice()!=price && ticketService.findTicketById(id).getRevenue().getTime()!=d){
			ListAndId(request, model);
			model.addAttribute("mes","Cannot Update Price");
			return "home";
		}
		ticketService.updateTicket(id, number,price, type,position);
		revenueService.updateRevenueDay();
		ListAndId(request, model);
		return "home";
	}
	
	@PostMapping(value="home",params="btn_export_ticket")
	public String exportTicket(HttpServletRequest request,ModelMap model, @RequestParam("ip_id") String id) {
		if(ticketService.findTicketById(id) == null) {
			ListAndId(request, model);
			model.addAttribute("mes","Id Not Found");
			return "home";
		}
		else if(ticketService.findTicketById(id).isStatus()==true) {
			ListAndId(request, model);
			model.addAttribute("mes","Ticket has been Export");
			return "home";
		}
		ticketService.exportTicket(id);
		ListAndId(request, model);
		return "home";
	}
	public void ListAndId(HttpServletRequest request,ModelMap model) {
		HttpSession session = request.getSession();
		model.addAttribute("tickets",ticketService.getAllTicket());
		model.addAttribute("id_employee",(String )session.getAttribute("id_employee"));
	}

	// ======================== SEARCH ===========================

	@PostMapping(value = "home", params = "btn_search_ticket")
	public String btnSearch(HttpServletRequest request, ModelMap model,@RequestParam("search_ticket") String id) {
		HttpSession session = request.getSession();
		if(id.equals("")==true){
			model.addAttribute("tickets",ticketService.getAllTicket());
			model.addAttribute("id_employee",(String )session.getAttribute("id_employee"));
			return "home";
		}
		else if(ticketService.findTicketById(id)==null){
			List<TicketEntity> list = new ArrayList<>();
			model.addAttribute("tickets", list);
			model.addAttribute("id_employee", (String) session.getAttribute("id_employee"));
			return "home";
		}
		else {
			List<TicketEntity> list = new ArrayList<>();
			list.add(ticketService.findTicketById(id));
			model.addAttribute("tickets", list);
			model.addAttribute("id_employee", (String) session.getAttribute("id_employee"));
			return "home";
		}
	}
}
