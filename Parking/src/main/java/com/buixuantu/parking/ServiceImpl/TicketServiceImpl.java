package com.buixuantu.parking.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixuantu.parking.Repository.RevenueRepository;
import com.buixuantu.parking.Repository.TicketRepository;
import com.buixuantu.parking.Service.EmployeeService;
import com.buixuantu.parking.Service.PositionsService;
import com.buixuantu.parking.Service.RevenueService;
import com.buixuantu.parking.Service.TicketService;
import com.buixuantu.parking.entity.EmployeeEntity;
import com.buixuantu.parking.entity.PositionsEntity;
import com.buixuantu.parking.entity.RevenueEntity;
import com.buixuantu.parking.entity.TicketEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private PositionsService positionService;
	@Autowired
	private RevenueService revenueService;
	@Autowired
	private EmployeeService employeeService; 
	@Autowired
	private RevenueRepository revenueRepository;
	
	@Override
	public List<TicketEntity> getAllTicket() {
		return ticketRepository.findAll();
	}

	@Override
	public TicketEntity addTicket(String id,String employee,int price,boolean type,String position,int number) {
		// ============== time =================
		LocalDateTime t = LocalDateTime.now();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String tu=t.format(formatter);
		LocalDateTime time = LocalDateTime.parse(tu, formatter);
		//====================================
		
		PositionsEntity ps = positionService.findPositionById(position);
		ps.setStatus(true);
		EmployeeEntity em = employeeService.findEmployeeById(employee);
		RevenueEntity rv = revenueService.findRevenueByTime(date);
		int x=0;
		if(rv==null) {
			for(RevenueEntity a :revenueService.getAllRevenue()) {
				x++;
			}
			RevenueEntity a = new RevenueEntity(x,date);
			TicketEntity b = new TicketEntity(id,time,type,price,number,false,em,a,ps);
			ticketRepository.save(b);
			List<TicketEntity> list = a.getTicketList();
			list.add(b);
			a.setTicketList(list);
			revenueRepository.save(a);
			return ticketRepository.save(b);
		}else {
			RevenueEntity a =revenueService.findRevenueByTime(date);
			TicketEntity b = new TicketEntity(id,time,type,price,number,false,em,rv,ps);
			ticketRepository.save(b);
			List<TicketEntity> list = a.getTicketList();
			list.add(b);
			a.setTicketList(list);
			revenueRepository.save(a);
			return ticketRepository.save(b);
		}
	}

	@Override
	public TicketEntity findTicketById(String id) {
		return ticketRepository.findById(id).orElse(null);
	}

	@Override
	public TicketEntity updateTicket(String id, int number, int price, boolean type,String position) {
		TicketEntity tmp = ticketRepository.findById(id).orElse(null);
		tmp.getPosition().setStatus(false);
		PositionsEntity ps = positionService.findPositionById(position);
		ps.setStatus(true);
		tmp.setPosition(positionService.findPositionById(position));
		tmp.setNumber_car(number);
		tmp.setPrice(price);
		tmp.setTicket_type(type);
		return ticketRepository.save(tmp);
	}

	@Override
	public TicketEntity exportTicket(String id) {
		TicketEntity tmp = ticketRepository.findById(id).orElse(null);
		tmp.setStatus(true);
		tmp.getPosition().setStatus(false);
		return ticketRepository.save(tmp);
	}

	@Override
	public boolean checkTicket(int number) {
		for(TicketEntity a : ticketRepository.findAll()) {
			if(a.getNumber_car() ==number && a.isStatus()==false) {
				return false;
			}
		}
		return true;
	}
}
