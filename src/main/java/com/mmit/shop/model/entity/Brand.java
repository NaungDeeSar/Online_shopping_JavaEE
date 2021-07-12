package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity implementation class for Entity: Brand
 *
 */
@Entity
@NamedQuery(name = "brandAll",query="SELECT b FROM Brand b")
@NamedQuery(name="Brand.findByName",query="SELECT b FROM Brand b WHERE b.name=:name")
public class Brand implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private static final long serialVersionUID = 1L;
	  @Column(unique = true, nullable = false)
		private String name;
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
	    @OneToMany(mappedBy = "brand")
	    private List<Product> products=new ArrayList<Product>();
	public Brand() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (Id != other.Id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   
}
