package org.openxava.invoicing.model;



import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.invoicing.calculators.*;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String oid; // Añadida como clave oculta autogenerada	

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

	@Stereotype("MEMO")
	private String remarks;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false) // customer es obligatorio
	private Customer customer;
	
	
	@OneToMany(mappedBy="parent", cascade = CascadeType.ALL)
	//@ListProperties("product.number, product.description, quantity")
	private Collection<Detail> details = new ArrayList<Detail>();
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
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
