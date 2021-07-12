package com.mmit.shop.model.service;

import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.security.AppException;
import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Users;
@Stateless
public class UserService {
	
	@Inject
	private Pbkdf2PasswordHash encoder;
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	Principal principal;
	
	public List<Users> findAll() {
		
		return em.createNamedQuery("user.findAll",Users.class)
				.setParameter("loginId", principal.getName())
				.getResultList();
	}
	
	
	public Users findByLoginId(String caller) {		
			   try {
					return em.createNamedQuery("users.findByEmail",Users.class)
							.setParameter("loginId", caller)
							.getSingleResult();
				
			} catch (NoResultException e) {
				
			}
	   return null;
	   
	}
	public void createUser(Users user) {
		if(user.getId()==0) {
			user.setPassword(encoder.generate(user.getPassword().toCharArray()));
			em.persist(user);
		}
		else
		{
			em.merge(user);
		}
		
		
	}


	public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
		Users loginUser=findByLoginId(principal.getName());
		
		if(!encoder.verify(oldPassword.toCharArray(), loginUser.getPassword())) {
			// not match old password
			throw new AppException("Incorrect Old Password!");
		}
		if(!newPassword.equals(confirmPassword)) {
			throw new AppException("confirmPassword do not match!");
		}
		
		loginUser.setPassword(encoder.generate(newPassword.toCharArray()));
		
	}


	public void remove(int id) {
		Users u=em.find(Users.class, id);
		     em.remove(u);	
	}


	public Users findById(int id) {		
		return em.find(Users.class, id);
	}

	

}
