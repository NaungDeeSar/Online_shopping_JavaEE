package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

@Entity
@NamedQuery(name = "Orders.findAll",query="SELECT o FROM Orders o ORDER BY o.orderDate")
@NamedQuery(name="Orders.findByLoginId",query="SELECT o FROM Orders o WHERE o.customer.id=:loginId")
public class Orders implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private  Users customer;
	@CreationTimestamp
	private LocalDate orderDate;
	
	private LocalDate receiveDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	public enum Status{
		Pending,Received,Delivered
	}
	@OneToMany(mappedBy = "order", cascade = PERSIST)
	private List<OrderDetail> details=new ArrayList<OrderDetail>();
	
	@OneToOne(mappedBy = "order", cascade = { PERSIST, MERGE })
	private Delivery delivery=new Delivery();
	
	@PrePersist
	private void initializeOrderStatus() {
		status=status.Pending;
	}
	public void addDelivery(Delivery d) {
		d.setOrder(this);
		this.setDelivery(d);
		
		
	}
	public void addOrderItem(OrderDetail detail) {
		detail.setOrder(this);
		details.add(detail);
	}
	
	public int getTotalQty() {
		return details.stream().mapToInt(d->d.getSubQty()).sum();

	}
	public int getTotalAmount() {
		return  details.stream().mapToInt(d->d.getProduct().getPrice() * d.getSubQty()).sum();
	}
	
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	public Orders() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}


	public Users getCustomer() {
		return customer;
	}


	public void setCustomer(Users customer) {
		this.customer = customer;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public List<OrderDetail> getDetails() {
		return details;
	}


	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	
   
}
