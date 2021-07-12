package com.mmit.shop.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.category;
import com.mmit.shop.model.service.CategoryService;

@Named
public class CategoryConverter implements Converter<category>{

	@Inject
	private CategoryService service;
	@Override
	public category getAsObject(FacesContext context, UIComponent component, String value) {
		if(value !=null)
			 return service.findById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, category value) {
		if(value !=null)
			return String.valueOf(value.getId());
		return null;
	}

}
