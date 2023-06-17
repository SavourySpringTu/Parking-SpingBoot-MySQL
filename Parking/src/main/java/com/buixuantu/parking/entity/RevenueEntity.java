package com.buixuantu.parking.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Revenue")
public class RevenueEntity {
	@Id
	private long id;
	private LocalDate time;
	private int total;
	
	@OneToMany(mappedBy="revenue",fetch = FetchType.EAGER)
	private List<TicketEntity> ticketList = new ArrayList<>() ;
	
	public RevenueEntity() {
		super();
	}
	
	public RevenueEntity(int id, LocalDate time) {
		super();
		this.id=id;
		this.time = time;
	}
	public long getId() {
		return id;
	}
	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<TicketEntity> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<TicketEntity> ticketList) {
		this.ticketList = ticketList;
	}
}
