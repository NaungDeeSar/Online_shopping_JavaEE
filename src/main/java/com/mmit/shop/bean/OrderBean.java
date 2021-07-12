package com.mmit.shop.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Orders.Status;
import com.mmit.shop.model.service.BrandService;
import com.mmit.shop.model.service.OrderService;

@Named
@ViewScoped
public class OrderBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private OrderService orderservice;
	
	private List<Orders> orderList;
	@PostConstruct
	private void init() {
		
		orderList=orderservice.findAll();
	}
	
	//ajax call from admin orderlist
	public void ChangeOrderStatus(int oId,String status) {
		
		Orders o=orderservice.findById(oId);
		if("Received".equals(status)) {
			o.setReceiveDate(LocalDate.now());
		     o.setStatus(Status.Received);
		    
		}
		else if("Delivered".equals(status))
		{
			o.setStatus(Status.Delivered);
			o.getDelivery().setReceiverDate(LocalDate.now());
		}
		 orderservice.changeStatus(o);
		 orderList=orderservice.findAll();
		
	}
	
	public OrderService getOrderservice() {
		return orderservice;
	}
	
	public void setOrderservice(OrderService orderservice) {
		this.orderservice = orderservice;
	}
	public List<Orders> getOrderList() {
		return orderList;
	}
	
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
}
