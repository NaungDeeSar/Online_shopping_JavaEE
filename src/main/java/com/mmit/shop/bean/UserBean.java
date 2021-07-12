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

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userservice;
	
	private List<Users> userList;
	private Users user;
	 @Inject
     private  LoginBean loginBean;
	 @Inject
	 private FacesContext cxt;
	 
	@PostConstruct
	private void init() {		
		userList=userservice.findAll();
		String id=cxt.getExternalContext().getRequestParameterMap().get("uId");
		
		if(id !=null && !id.equals(""))
			user=userservice.findById(Integer.parseInt(id));
		else
			user =new Users();
	}
	
	public String save() {
		try {
			userservice.createUser(user);
			return "/admin/users.xhtml?faces-redirect=true";
			
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("userForm:loginId", new FacesMessage("LoginId is already exists"));
		}
		return  null;
	}
	
	public String remove(int id) {		
	    userservice.remove(id);
		return "/admin/users.xhtml?faces-redirect=true";
	}
	
	
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public List<Users> getUserList() {
		return userList;
	}
	public void setUserList(List<Users> userList) {
		this.userList = userList;
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
