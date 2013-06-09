package org.openxava.invoicing.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.invoicing.calculators.*;

@Entity
@View(members= // Esta vista no tiene nombre, por tanto será la vista usada por defecto
"year, number, date;" + // Separados por coma significa en la misma línea
"customer;" + // Punto y coma significa nueva línea
"details;" +
"remarks"
)
//Añadimos el modificador abstract
abstract public class CommercialDocument extends Identifiable { 

	@Column(length = 4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int year;
	
	@Column(length = 6)
	@DefaultValueCalculator(
			value=NextNumberForYearCalculator.class, 
			properties = @PropertyValue(name = "year")// Para inyectar el valor de year de Invoice
												//	en el calculador antes de llamar a calculate()
	)
	private int number;

	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class) // Fecha actual
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false) // customer es obligatorio
	@ReferenceView("Simple") // La vista llamada 'Simple' se usará para visualizar esta referencia
	private Customer customer;
		
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
	@ListProperties("product.number, product.description, quantity")
	private Collection<Detail> details = new ArrayList<Detail>();
	
	@Stereotype("MEMO")
	private String remarks;

	public Collection<Detail> getDetails() {
		return details;
	}

	public void setDetails(Collection<Detail> details) {
		this.details = details;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
