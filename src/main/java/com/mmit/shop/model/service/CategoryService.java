package com.mmit.shop.model.service;

import java.util.List;
import java.util.Locale.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.entity.category;
@Stateless
public class CategoryService {
	@PersistenceContext
	private EntityManager em;

	public void save(category category) {
		if(category.getId() == 0) {
			em.persist(category);
		}else {
			em.merge(category);
		}
		
	}

	public List<category> findAll() {
		TypedQuery<category> query=em.createNamedQuery("categoryAll",category.class);
		List<category> list= query.getResultList();
		for(category c:list) {
			for(Product p:c.getProducts() ) {
				
			}
			
		}
		return list;
	}

	public category findById(int id) {		
		return em.find(category.class, id);
	}

	

	
	

}
