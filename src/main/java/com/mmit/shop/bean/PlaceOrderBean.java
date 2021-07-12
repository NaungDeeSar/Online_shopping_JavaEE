package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.OrderService;

@Named
@ViewScoped
public class PlaceOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String receiverName;
	private String receiverAddress;
	private int receiverPhone;
	@Inject
	private OrderService service;
	@Inject
	private LoginBean loginBean;
	@Inject
	private CartBean cartBean;
	
	//private List<Orders> order;
	private List<OrderDetail> orderDetails;
	@PostConstruct
	private void init() {
		Users user=loginBean.getLoginUser();
		receiverName=user.getUserName();
		receiverAddress=user.getAddress();
		receiverPhone=user.getPhone();	
		//order=new ArrayList<Orders>();
		orderDetails=new ArrayList<OrderDetail>();
	}
	public String PlaceOrder() {
		service.saveOrder(cartBean.getOrder(),receiverName,receiverAddress,receiverPhone);
		cartBean.setOrder(new Orders());
		return "/index.xhtml?faces-redirect=true";
	}
	public String getReceiverName() {
		return receiverName;
	}

	public void getOrderDetail(int orderId) {
		Orders o=service.findById(orderId);
		orderDetails=o.getDetails();
		
		
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public int getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(int receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public List<Orders> getOrder() {
		return service.findByLoginId(loginBean.getLoginUser().getId());
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	
	

}
