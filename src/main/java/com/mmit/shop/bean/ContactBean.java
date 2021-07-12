package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Contact;
import com.mmit.shop.model.service.ContactService;

@Named
@ViewScoped
public class ContactBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ContactService service;
	
	private Contact contact;
	@PostConstruct
	private void init() {		
		contact=new Contact();
		
	}
	public String save() {
		service.save(contact);
		return "/index.xhtml?faces-redirect=true";
	}
	public ContactService getService() {
		return service;
	}
	public void setService(ContactService service) {
		this.service = service;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}
