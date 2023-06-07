package com.buixuantu.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Revenue")
public class RevenueEntity {
	@Id
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private Date time;
	private int total;
	
	@OneToMany(mappedBy="revenue",fetch = FetchType.EAGER)
	private List<TicketEntity> ticketList;
	
	public RevenueEntity() {
		super();
	}
	
	public RevenueEntity(int id,Date time) {
		super();
		this.id=id;
		this.time = time;
	}
	public long getId() {
		return id;
	}
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
