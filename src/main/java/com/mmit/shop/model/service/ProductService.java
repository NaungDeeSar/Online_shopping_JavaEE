package com.mmit.shop.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.entity.category;

@Stateless
public class ProductService {
	@PersistenceContext
	private EntityManager em;
    @Inject
     private  LoginBean loginBean;
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("product.findAll",Product.class).getResultList();
	}

	public void save(Product product) {
		if(product.getId()==0) {
			product.setCreated_by(loginBean.getLoginUser());
			em.persist(product);
		}else {
			Product p=findById(product.getId());
			product.setCreated_by(p.getCreated_by());
			product.setCreated_at(p.getCreated_at());
			product.setUpdated_by(loginBean.getLoginUser());
			em.merge(product);
		}
		
	}

	public Product findById(int id) {		
		return em.find(Product.class, id);
	}

	public void remove(int id) {
		Product p=em.find(Product.class,id);
		em.remove(p);
		
	}

	public String findPhotoById(int id) {		
		return em.createNamedQuery("product.Photo",String.class)
				.setParameter("pId", id)
				.getSingleResult();
	}

	public List<Product> findByCategoryId(int cid) {
		TypedQuery<Product> query=em.createNamedQuery("Product.findByCategoryId", Product.class);
		query.setParameter("cId", cid);
		return  query.getResultList();
	}

	public List<Product> findByBrandId(int bid) {
		TypedQuery<Product> query=em.createNamedQuery("Product.findByBrandId", Product.class);
		query.setParameter("bId", bid);
		return query.getResultList();
	}

	public void uploadData(Part fileUpload) {
	    //create reader
		try {
			  BufferedReader br=new  BufferedReader(new InputStreamReader(fileUpload.getInputStream()));
				String line= null;
				while((line=br.readLine()) !=null) {
					  createProduct(line);
				}
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private void createProduct(String line) {
		String data[]=line.split("\t");
		if(data.length ==5) {
			category c=getCategory(data[1]);
			Brand b=getBrand(data[2]);
			Product p=getProduct(c,b,data[0]);
			
			p.setPrice(Integer.parseInt(data[3]));
			p.setProductDetail(data[4]);
			
		}
		
	}

	private Product getProduct(category c, Brand b, String name) {
		Product p=new Product();
		TypedQuery<Product> query=em.createNamedQuery("Product.findByNameBrandCategory", Product.class);
		query.setParameter("bId", b.getId());
		query.setParameter("cId", c.getId());
		query.setParameter("pname", name);
		try {
			p=query.getSingleResult();
		} catch (Exception e) {
			p=new Product();
			p.setBrand(b);
			p.setCategory(c);
			p.setName(name);
		}
		return p;
	}

	private Brand getBrand(String bname) {
		TypedQuery<Brand> query=em.createNamedQuery("Brand.findByName", Brand.class);
		query.setParameter("name", bname);
		Brand bd=null;
		try {
			bd=query.getSingleResult();
		} catch (Exception e) {
			bd=new Brand();
			bd.setName(bname);
			em.persist(bd);
		}
		return bd;
	}

	private category getCategory(String cname) {
		TypedQuery<category> query=em.createNamedQuery("category.findByName", category.class);
		query.setParameter("name", cname);
		category cat=null;
		try {
			cat=query.getSingleResult();
		} catch (Exception e) {
			cat=new category();
			cat.setName(cname);
			em.persist(cat);
		}
		return cat;
	}

	
}
