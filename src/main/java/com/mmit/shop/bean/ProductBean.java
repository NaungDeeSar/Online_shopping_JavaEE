package com.mmit.shop.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable   {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductService productservice;
	
	private List<Product> productList;
	private Product product;
	
	private Part imgPart;
	private Part fileUpload;
	private ServletContext cxt;
	private String imgFolder=null;
	
	@PostConstruct
	private void init() {
		productList=productservice.findAll();		
		String id=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pId");
	    if(id !=null && !id.equals("")) 
	    	
	    	product=productservice.findById(Integer.parseInt(id));
	    else 
	    	product =new Product();
	    
	    cxt=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    imgFolder=cxt.getRealPath("/resources/uploads");
}
	
	public String remove(int id) {
		String photoName=productservice.findPhotoById(id);
		if(photoName !=null) {
			File photo=new File(imgFolder+File.separator+photoName);
			if(photo.exists()) 
				photo.delete();
			
		}
		productservice.remove(id);
		return "/admin/products.xhtml?faces-redirect=true";
	}
	
	public String save() {
		try {
			 if(imgPart !=null) {			
				 
				 String photoName=getPhotoName(imgPart.getSubmittedFileName());				 
				 //edit product (delete photo)
				 
				 if(product.getId() !=0) {
					 String oldPhoto=productservice.findPhotoById(product.getId());
					 if(oldPhoto !=null) {
						 File oldFile=new File(imgFolder + File.separator+oldPhoto);
					   if(oldFile.exists()) {
						   oldFile.delete();
					   }
					 }
				 }
				 
				 
				 imgPart.write(imgFolder + File.separator + photoName);
			     product.setPhoto(photoName);
			 }
			 else
			 {
				 if(product.getId() !=0) {
					 product.setPhoto(productservice.findPhotoById(product.getId()));
				 }
				 
			 }
			
			productservice.save(product);
			return "/admin/products.xhtml?faces-redirect=true";
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:name", new FacesMessage("Product Name is already exists"));
		}
		catch(IOException e) {
			
		}
		return null;
	}
	private String getPhotoName(String submitName) {
		
		//user.jpg
		String tmpName=submitName.substring(0,submitName.lastIndexOf("."));
		String newName="img" + System.currentTimeMillis();
		submitName=submitName.replace(tmpName, newName);
		return submitName;
	}
	public String upload() {
		if(fileUpload !=null && !fileUpload.getSubmittedFileName().equals("")) {
			productservice.uploadData(fileUpload);
		}
		return null;
	}
	public ProductService getProductservice() {
		return productservice;
	}
	public void setProductservice(ProductService productservice) {
		this.productservice = productservice;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Part getImgPart() {
		return imgPart;
	}
	public void setImgPart(Part imgPart) {
		this.imgPart = imgPart;
	}

	public Part getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(Part fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	
}
