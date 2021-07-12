package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.category;

import com.mmit.shop.model.service.CategoryService;

@Named
@ViewScoped
public class CategoryBean implements Serializable {   
	private static final long serialVersionUID = 1L;
	@Inject
    private CategoryService service;
    private List<category> categories;
    private category category;
  
    @PostConstruct
    private void init() {    	
         categories=service.findAll();
    	category=new category();
    }
    
    //ajax method
    public void getCategoryInfo(int id) {
    	if(id==0)
    		category=new category();
    	else
    		category=service.findById(id);
    	
    }
   public String Save() {
	    service.save(category);
	   return "/views/category.xhtml?faces-redirect=true";
   }

public List<category> getCategories() {
	return categories;
}

public void setCategories(List<category> categories) {
	this.categories = categories;
}

public category getCategory() {
	return category;
}

public void setCategory(category category) {
	this.category = category;
}



}
