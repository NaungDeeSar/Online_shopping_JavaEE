package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.entity.category;

@Stateless
public class BrandService {
	@PersistenceContext
	private EntityManager em;

	public void save(Brand brand) {
		if(brand.getId()==0) {
			em.persist(brand);
		}else {
			em.merge(brand);
		}
		
	}

	public List<Brand> findAll() {
		TypedQuery<Brand> query=em.createNamedQuery("brandAll", Brand.class);
		List<Brand> list= query.getResultList();
		for(Brand b:list) {
			for(Product p:b.getProducts() ) {
				
			}
			
		}
		return list;
	
	}

	public Brand findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Brand.class, id);
	}

}
