package org.openxava.invoicing.model;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.openxava.annotations.*;

@Entity
//Extiende de Identifiable por tanto no necesita tener una propiedad id
public class Author extends Identifiable{

	@Column(length = 50)
	@Required
	private String name;

	@OneToMany(mappedBy = "author")
	@ListProperties("number, description, price")
	private Collection<Product> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

}
