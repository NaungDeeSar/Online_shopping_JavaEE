package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity

public class Delivery implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String receiver;
	@Column(nullable = false)
	private int phone;
	private String address;
	private LocalDate receiverDate;
	
	
	@OneToOne(optional = false)
	@JoinColumn(name = "order_id")
	private Orders order;
	
	public Delivery() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int receiverPhone) {
		this.phone = receiverPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getReceiverDate() {
		return receiverDate;
	}
	public void setReceiverDate(LocalDate receiverDate) {
		this.receiverDate = receiverDate;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
   
}
