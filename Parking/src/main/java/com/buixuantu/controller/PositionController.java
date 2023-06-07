package com.buixuantu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buixuantu.Service.PositionsService;
import com.buixuantu.entity.PositionsEntity;

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
	public String addPosition(@RequestParam("ip_id") String id) {
		PositionsEntity ps = new PositionsEntity(id,false);
		positionService.addPosition(ps);
		return "position";
	}
	@PostMapping(value="position",params="btn_delete_position")
	public String deletePosition(@RequestParam("ip_id") String id) {
		positionService.deletePositionById(id);
		return "position";
	}
}
