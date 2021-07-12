package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.BrandService;
import com.mmit.shop.model.service.CategoryService;
import com.mmit.shop.model.service.ProductService;

@Named
@ViewScoped
public class SearchProductBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Inject
	private ProductService productservice;
	@Inject
	private CategoryService categoryservice;
	@Inject
	private BrandService brandservice;
	
	private List<Product> productList;
	
	@Inject
	private ExternalContext ecxt;
	
	private String cName;
	private String bName;
	@PostConstruct
	private void init() {
		String cid=ecxt.getRequestParameterMap().get("cId");
		String bid=ecxt.getRequestParameterMap().get("bId");
		if(cid !=null) {
			productList=productservice.findByCategoryId(Integer.parseInt(cid));
			
			
		}else if(bid !=null) {
			productList=productservice.findByBrandId(Integer.parseInt(bid));
		}else
			productList=productservice.findAll();
		
	}
	public ProductService getProductservice() {
		return productservice;
	}
	public void setProductservice(ProductService productservice) {
		this.productservice = productservice;
	}
	public CategoryService getCategoryservice() {
		return categoryservice;
	}
	public void setCategoryservice(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}
	public BrandService getBrandservice() {
		return brandservice;
	}
	public void setBrandservice(BrandService brandservice) {
		this.brandservice = brandservice;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public ExternalContext getEcxt() {
		return ecxt;
	}
	public void setEcxt(ExternalContext ecxt) {
		this.ecxt = ecxt;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	
}
