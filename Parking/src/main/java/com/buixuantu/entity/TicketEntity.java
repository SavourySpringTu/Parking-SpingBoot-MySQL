package com.buixuantu.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ticket")
public class TicketEntity {
	@Id
	private String id;
	private Date time;
	private boolean ticket_type;
	private int price;
	private int number_car;
	private boolean status;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_employee")
    private EmployeeEntity employee;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_revenue")
    private RevenueEntity revenue;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_position")
    private PositionsEntity position;

    public TicketEntity() {
    	super();
    }
    
	public TicketEntity(String id, Date time, boolean ticket_type, int price, int number_car, boolean status,
			EmployeeEntity employee, RevenueEntity revenue, PositionsEntity position) {
		super();
		this.id = id;
		this.time = time;
		this.ticket_type = ticket_type;
		this.price = price;
		this.number_car = number_car;
		this.status = status;
		this.employee = employee;
		this.revenue = revenue;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isTicket_type() {
		return ticket_type;
	}

	public void setTicket_type(boolean ticket_type) {
		this.ticket_type = ticket_type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber_car() {
		return number_car;
	}

	public void setNumber_car(int number_car) {
		this.number_car = number_car;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public RevenueEntity getRevenue() {
		return revenue;
	}

	public void setRevenue(RevenueEntity revenue) {
		this.revenue = revenue;
	}

	public PositionsEntity getPosition() {
		return position;
	}

	public void setPosition(PositionsEntity position) {
		this.position = position;
	}
}
