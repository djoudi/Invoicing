package org.openxava.invoicing.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Product {
	
	@Id 
	@Column(length=9)
	private int number;
	
	@Column(length=50) 
	@Required
	private int description;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDescription() {
		return description;
	}

	public void setDescription(int description) {
		this.description = description;
	}
	
	
	
}
