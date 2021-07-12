package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;

@Named
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Orders order;
	@Inject
	private ProductService pservice;
	@PostConstruct
	private void init() {
		order=new Orders();
		
	}
	
	public void addToCart(int pId) {
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId() ==pId) {
				od.setSubQty(od.getSubQty() +1);
				return;
			}
			
		}
		Product p=pservice.findById(pId);
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setProduct(p);
		orderDetail.setSubQty(1);
		order.addOrderItem(orderDetail);
		
		
	}
	public String removeFromCart(OrderDetail od) {		
		order.getDetails().remove(od);
		return "cart.xhtml?faces-redirect=true";
		
	}
	public void updateQty() {
		order.getDetails().forEach(od->System.out.println(String.format("%s,%d",od.getProduct().getBrand(),od.getSubQty())));
	}
	public int getItemCount() {
		return  order.getDetails().size();
	}
	
	public void qtyPlus(int id) {
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId() ==id) {
				od.setSubQty(od.getSubQty()+1);
				return;
			}
		}
		
	}
	public void qtyMinus(int id) {
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId() ==id) {
				od.setSubQty(od.getSubQty()-1);
				return;
			}
		}
		
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	

}
