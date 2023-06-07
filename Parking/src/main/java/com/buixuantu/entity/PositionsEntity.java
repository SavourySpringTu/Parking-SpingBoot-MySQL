package com.buixuantu.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Positions")
public class PositionsEntity {
	@Id
	private String id;
	private boolean status;
	
	@OneToMany(mappedBy="position",fetch = FetchType.EAGER)
	private List<TicketEntity> ticketList;
	
	public PositionsEntity() {
		super();
	}
	
	public PositionsEntity(String id, boolean status) {
		super();
		this.id = id;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
