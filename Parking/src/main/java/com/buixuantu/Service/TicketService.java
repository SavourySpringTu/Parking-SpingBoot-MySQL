package com.buixuantu.Service;

import java.util.List;

import com.buixuantu.entity.PositionsEntity;
import com.buixuantu.entity.TicketEntity;

public interface TicketService {
	TicketEntity findTicketById(String id);
	List<TicketEntity> getAllTicket();
	TicketEntity addTicket(String id,String employee,int price,boolean type,String position,int number);
	TicketEntity updateTicket(String id,int number,int price,boolean type);
	TicketEntity exportTicket(String id);
}
