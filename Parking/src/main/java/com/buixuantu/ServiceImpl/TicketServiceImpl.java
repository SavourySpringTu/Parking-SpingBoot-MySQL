package com.buixuantu.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixuantu.Repository.TicketRepository;
import com.buixuantu.Service.EmployeeService;
import com.buixuantu.Service.PositionsService;
import com.buixuantu.Service.RevenueService;
import com.buixuantu.Service.TicketService;
import com.buixuantu.entity.EmployeeEntity;
import com.buixuantu.entity.PositionsEntity;
import com.buixuantu.entity.RevenueEntity;
import com.buixuantu.entity.TicketEntity;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private PositionsService positionService;
	@Autowired
	private RevenueService revenueSerive;
	@Autowired
	private EmployeeService employeeService; 
	
	@Override
	public List<TicketEntity> getAllTicket() {
		return ticketRepository.findAll();
	}

	@Override
	public TicketEntity addTicket(String id,String employee,int price,boolean type,String position,int number) {
		Date time = new Date();
		PositionsEntity ps = positionService.findPositionById(position);
		ps.setStatus(true);
		EmployeeEntity em = employeeService.findEmployeeById(employee);
		RevenueEntity rv = revenueSerive.findRevenueByTime(time);
		List<RevenueEntity> list = revenueSerive.getAllRevenue();
		int x=0;
		if(rv==null) {
			for(RevenueEntity a :list) {
				x++;
			}
			RevenueEntity a = new RevenueEntity(x,time);
			revenueSerive.addRevenue(a);
			TicketEntity b = new TicketEntity(id,time,type,price,number,false,em,a,ps);
			return ticketRepository.save(b);
		}else {
			TicketEntity b = new TicketEntity(id,time,type,price,number,false,em,rv,ps);
			return ticketRepository.save(b);
		}
	}

	@Override
	public TicketEntity findTicketById(String id) {
		return ticketRepository.findById(id).orElse(null);
	}

	@Override
	public TicketEntity updateTicket(String id, int number, int price, boolean type) {
		TicketEntity tmp = ticketRepository.findById(id).orElse(null);
		tmp.setNumber_car(number);
		tmp.setPrice(price);
		tmp.setTicket_type(type);
		return ticketRepository.save(tmp);
	}

	@Override
	public TicketEntity exportTicket(String id) {
		TicketEntity tmp = ticketRepository.findById(id).orElse(null);
		tmp.setStatus(true);
		return ticketRepository.save(tmp);
	}
}
