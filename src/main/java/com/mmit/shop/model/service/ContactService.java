package com.mmit.shop.model.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.model.entity.Contact;

@Stateless
public class ContactService {
	@PersistenceContext
	private EntityManager em;

	public void save(Contact contact) {
		if(contact.getId()==0) {
			em.persist(contact);
		}else {
			em.merge(contact);
		}
		
	}
}
