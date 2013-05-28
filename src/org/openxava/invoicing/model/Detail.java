package org.openxava.invoicing.model;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

@Entity
@View(members="product, quantity") // En la misma línea, ya que están separado por comas
public class Detail {
	
	@ManyToOne // Sin lazy fetching porque falla al quitar un detalle desde el padre
	private Invoice parent;
	
	@Id @GeneratedValue(generator="system-uuid") @Hidden
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length=32)
	private String oid;
		
	private int quantity;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@ReferenceView("Simple") // Product se visualiza usando su vista Simple
	@NoFrame // No se usa un marco alrededor de los datos de product
	private Product product;
	
	public Invoice getParent() {
		return parent;
	}
	public void setParent(Invoice parent) {
		this.parent = parent;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}	
	
}
