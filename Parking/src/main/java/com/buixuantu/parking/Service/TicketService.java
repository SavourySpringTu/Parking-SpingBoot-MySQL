package com.buixuantu.parking.Service;

import java.util.List;

import com.buixuantu.parking.entity.TicketEntity;

public interface TicketService {
	TicketEntity findTicketById(String id);
	List<TicketEntity> getAllTicket();
	TicketEntity addTicket(String id,String employee,int price,boolean type,String position,int number);
	TicketEntity updateTicket(String id,int number,int price,boolean type,String position);
	TicketEntity exportTicket(String id);
	boolean checkTicket(int number);
}
