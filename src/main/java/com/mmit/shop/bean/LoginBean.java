package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import com.mmit.security.AppException;
import com.mmit.shop.model.entity.Users;

@Named
@SessionScoped
public class LoginBean implements  Serializable {
	
	private static final long serialVersionUID = 1L;
		@NotEmpty(message="Required login Id")
        private String loginId;
		@NotEmpty(message="Required Password")
        private String password;
        
	@Inject
	private ExternalContext exContext;
	@Inject
	private  SecurityContext securityContext;
	private Users loginUser;
	@PostConstruct
	public void init() {
		loginUser=new Users();
	}
	public String login() {
		try {
			HttpServletRequest req=(HttpServletRequest) exContext.getRequest();
			HttpServletResponse  resp=(HttpServletResponse) exContext.getResponse();
			
			UsernamePasswordCredential credential=new UsernamePasswordCredential(loginId, password);
			
			
			AuthenticationStatus  status=securityContext.authenticate(req, resp, AuthenticationParameters.withParams().credential(credential));
			if(status == AuthenticationStatus.SUCCESS ) {
				return "/index";
			}
		} catch (AppException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public Users getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}
	
}
