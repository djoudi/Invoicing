package org.openxava.invoicing.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class Invoice extends CommercialDocument {

	@OneToMany(mappedBy="invoice")
	private Collection<Order> orders; // A�adimos colecci�n de entidades Order

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}	
	
}
