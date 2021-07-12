package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQuery(name = "user.findAll",query="SELECT u FROM Users u WHERE u.loginId <> :loginId")
@NamedQuery(name = "users.findByEmail",query = "SELECT u FROM Users u WHERE u.loginId =:loginId")
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	private String userName;
	@Column(unique = true)
	@NotEmpty(message = "* Required loginId")
	private String loginId;
	private String password;
	private String address;
	private int phone;
	@Enumerated(EnumType.STRING)
	private Role role;
	public enum Role{
		Admin,Staff,Customer
	}
	@CreationTimestamp
    private LocalDate created_at;
    @UpdateTimestamp
    private LocalDate updated_at;
   
	public Users() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}
	public LocalDate getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
   
}
