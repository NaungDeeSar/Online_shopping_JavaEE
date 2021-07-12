package com.mmit.security;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.Role;
import com.mmit.shop.model.service.UserService;
@ApplicationScoped
@Singleton
@Startup
public class AdminUserCreation {
	@Inject
	private UserService service;
	
	@PostConstruct
	private void init() {	
		Users user=new Users();
		user.setAddress("mkn");
		user.setLoginId("admin@gmail.com");
		user.setPassword("admin");
		user.setRole(Role.Admin);
		user.setUserName("Admin");			
	    //service.createUser(user);		
	}
}
