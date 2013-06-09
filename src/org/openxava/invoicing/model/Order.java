package org.openxava.invoicing.model;

import javax.persistence.*;

@Entity
public class Order extends CommercialDocument {

	@ManyToOne
	private Invoice invoice; // Añadida referencia a Invoice

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	
}
