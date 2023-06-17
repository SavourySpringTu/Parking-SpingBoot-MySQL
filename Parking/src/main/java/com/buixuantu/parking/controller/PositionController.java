package com.buixuantu.parking.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.buixuantu.parking.Service.PositionsService;
import com.buixuantu.parking.entity.PositionsEntity;

@Controller
@RequestMapping("/parking")
public class PositionController {
	@Autowired
	private PositionsService positionService;
	
	@GetMapping("/position")
	public String position(ModelMap model) {
		model.addAttribute("positions",positionService.getAllPositions());
		return "position";
	}
	
	@PostMapping(value="position",params="btn_add_position")
	public String addPosition(ModelMap model,@RequestParam("ip_id") String id) {
		if(positionService.findPositionById(id)!=null) {
			model.addAttribute("positions",positionService.getAllPositions());
			model.addAttribute("mes","Invalid Id");
			return "position";
		}
		positionService.addPosition(id,false);
		model.addAttribute("positions",positionService.getAllPositions());
		return "position";
	}
	
	@PostMapping(value="position",params="btn_delete_position")
	public String deletePosition(ModelMap model,@RequestParam("ip_id") String id) {
		if(positionService.findPositionById(id)==null) {
			model.addAttribute("positions",positionService.getAllPositions());
			model.addAttribute("mes","Id Not Found");
			return "position";
		}
		positionService.deletePositionById(id);
		return "position";
	}
	@PostMapping(value = "position", params = "btn_search_position")
	public String btnSearch(HttpServletRequest request, ModelMap model, @RequestParam("search_position") String id) {
		HttpSession session = request.getSession();
		if (id.equals("") == true) {
			model.addAttribute("positions", positionService.getAllPositions());
			return "position";
		} else if (positionService.findPositionById(id) == null) {
			List<TicketEntity> list = new ArrayList<>();
			model.addAttribute("positions", list);
			return "position";
		} else {
			List<PositionsEntity> list = new ArrayList<>();
			list.add(positionService.findPositionById(id));
			model.addAttribute("positions", list);
			return "position";
		}
	}
}
