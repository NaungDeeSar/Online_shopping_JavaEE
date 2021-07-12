package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@NamedQuery(name = "product.findAll",query="SELECT p FROM Product p ORDER BY p.created_at ASC")
@NamedQuery(name="product.Photo",query="SELECT p.photo FROM Product p WHERE p.id= :pId")
@NamedQuery(name="Product.findByCategoryId",query="SELECT p.photo FROM Product p WHERE p.category.id= :cId")

@NamedQuery(name="Product.findByBrandI",query="SELECT p.photo FROM Product p WHERE p.brand.id= :bId")

@NamedQuery(name = "Product.findByNameBrandCategory",query="SELECT p FROM Product p WHERE p.brand.id=:bId AND p.category.id =:cId AND p.name=:pname")
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private category category;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	private int price;
	@Column(columnDefinition = "TEXT")
	private String productDetail;
	private String photo;
	@CreationTimestamp
    private LocalDate created_at;
    @UpdateTimestamp
    private LocalDate updated_at;
	    @ManyToOne
	    @JoinColumn(name = "created_by")
		private Users created_by;
	    @ManyToOne
	    @JoinColumn(name = "updated_by")
	    private Users updated_by;
	
	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public category getCategory() {
		return category;
	}

	public void setCategory(category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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

	public Users getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Users created_by) {
		this.created_by = created_by;
	}

	public Users getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Users updated_by) {
		this.updated_by = updated_by;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
   
}
