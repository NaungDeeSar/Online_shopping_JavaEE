package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.Role;
import com.mmit.shop.model.service.UserService;

@Named
@ViewScoped
public class RegisterBean implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userservice;	
	
	private Users user;
	 @Inject
     private  LoginBean loginBean;
	 @Inject
	 private FacesContext cxt;
	 @PostConstruct
		private void init() {		
		
			String id=cxt.getExternalContext().getRequestParameterMap().get("uId");
			
			if(id !=null && !id.equals(""))
				user=userservice.findById(Integer.parseInt(id));
			else
				user =new Users();
			
				user.setRole(Role.Customer);
		}
	 
	 public String save() {
			try {
				userservice.createUser(user);
				return "/front_end/login.xhtml?faces-redirect=true";
				
			} catch (EJBException e) {
				FacesContext cxt=FacesContext.getCurrentInstance();
				cxt.addMessage("userForm:loginId", new FacesMessage("LoginId is already exists"));
			}
			return  null;
		}
		
	 
	 
	 
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	 
		

}
