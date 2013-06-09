package org.openxava.invoicing.model;

import javax.persistence.*;
import javax.persistence.Entity;

import org.openxava.annotations.*;

@Entity
@View(members="product, quantity") // En la misma línea, ya que están separado por comas
public class Detail extends Identifiable{
	
	@ManyToOne // Sin lazy fetching porque falla al quitar un detalle desde el padre
	private CommercialDocument parent;
	
	private int quantity;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@ReferenceView("Simple") // Product se visualiza usando su vista Simple
	@NoFrame // No se usa un marco alrededor de los datos de product
	private Product product;
	
	public CommercialDocument getParent() {
		return parent;
	}
	public void setParent(CommercialDocument parent) {
		this.parent = parent;
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
