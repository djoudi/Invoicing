package org.openxava.invoicing.test;

import org.openxava.tests.*;

public class InvoiceTest extends ModuleTestBase {

	private String number; // Para almacenar el número de la factura que
							// probamos

	public InvoiceTest(String testName) {
		super(testName, "Invoicing", "Invoice");
	}

	public void testCreateInvoice() throws Exception { // El método de prueba
		verifyDefaultValues();
		chooseCustomer();
		addDetails();
		setOtherProperties();
		save();
		verifyCreated();
		remove();
	}

	private void verifyDefaultValues() throws Exception {

	}

	private void chooseCustomer() throws Exception {

	}

	private void addDetails() throws Exception {

	}

	private void setOtherProperties() throws Exception {

	}

	private void save() throws Exception {

	}

	private void verifyCreated() throws Exception {

	}

	private void remove() throws Exception {

	}

	private String getCurrentYear() {
		return null;
	}

	private String getCurrentDate() {
		return null;
	}

	
	private String getNumber() {
		return number;
	}

	

}
