package com.buixuantu.parking.controller;

import com.buixuantu.parking.Service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parking")
public class RevenueController {
	@Autowired
	private RevenueService revenueService;
	
	@GetMapping("/revenue")
	public String revenue(ModelMap model) {
		model.addAttribute("revenue",revenueService.getAllRevenue());
		return "revenue";
	}
}
