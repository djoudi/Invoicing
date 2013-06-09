package org.openxava.invoicing.test;

import java.text.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.tests.*;
import org.openxava.util.*;

import static org.openxava.jpa.XPersistence.*; // Para usar JPA

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
		execute("CRUD.new");
		assertValue("year", getCurrentYear());
		assertValue("number", getNumber());
		assertValue("date", getCurrentDate());
	}

	private void chooseCustomer() throws Exception {
		setValue("customer.number", "1");
		assertValue("customer.name", "FRANCISCO JAVIER PANIZA LUCAS");
	}

	private void addDetails() throws Exception {
		// Añadir una línea de detalle
		assertCollectionRowCount("details", 0); // La colección esta vacía

		// Pulsa en el botón para añadir un nuevo elemento
		// viewObject es necesario para determinar
		// a que colección nos referimos
		execute("Collection.new", "viewObject=xava_view_details");
		setValue("product.number", "1");
		assertValue("product.description",
				"Peopleware: Productive Projects and Teams");
		setValue("quantity", "2");

		// Graba el elemento de la colección sin cerrar el diálogo
		execute("Collection.saveAndStay");

		// No hay errores al grabar el detalle
		assertNoErrors();
		// Añadir otro detalle
		setValue("product.number", "2");
		assertValue("product.description", "Arco iris de lágrimas");
		setValue("quantity", "1");

		// Graba el elemento de la colección cerrando el diálogo
		execute("Collection.save");
		assertNoErrors();
		assertCollectionRowCount("details", 2); // Ahora tenemos 2 filas
	}

	private void setOtherProperties() throws Exception {
		setValue("remarks", "This is a JUNIT test");
	}

	private void save() throws Exception {
		execute("CRUD.save");
		assertNoErrors();
		assertValue("customer.number", "");
		assertCollectionRowCount("details", 0);
		assertValue("remarks", "");
	}

	private void verifyCreated() throws Exception {
		setValue("year", getCurrentYear()); // El año actual en el campo año
		setValue("number", getNumber()); // El número de la factura usada en la
											// prueba
		execute("CRUD.refresh"); // Carga la factura desde la DB
		// En el resto de la prueba confirmamos que los valores son los
		// correctos
		assertValue("year", getCurrentYear());
		assertValue("number", getNumber());
		assertValue("date", getCurrentDate());
		assertValue("customer.number", "1");
		assertValue("customer.name", "FRANCISCO JAVIER PANIZA LUCAS");
		assertCollectionRowCount("details", 2);
		// Fila 0
		assertValueInCollection("details", 0, "product.number", "1");
		assertValueInCollection("details", 0, "product.description",
				"Peopleware: Productive Projects and Teams");
		assertValueInCollection("details", 0, "quantity", "2");
		// Fila 1
		assertValueInCollection("details", 1, "product.number", "2");
		assertValueInCollection("details", 1, "product.description",
				"Arco iris de lágrimas");
		assertValueInCollection("details", 1, "quantity", "1");
		assertValue("remarks", "This is a JUNIT test");
	}

	private void remove() throws Exception {
		execute("CRUD.delete");
		assertNoErrors();
	}

	// Año actual en formato cadena
	private String getCurrentYear() {
		// La forma típica de hacerlo con Java
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	// Fecha actual como una cadena en formato corto
	private String getCurrentDate() {
		// La forma típica de hacerlo con Java
		return DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
	}

	// El número de factura para una factura nueva
	private String getNumber() {
		// Usamos inicialización vaga
		if (number == null) {
			// Una consulta JPA para obtener el último número
			Query query = getManager().createQuery(
					"select max(i.number) from Invoice i where i.year = :year");
			// Dates es una utilidad de OpenXava
			query.setParameter("year", Dates.getYear(new Date()));
			Integer lastNumber = (Integer) query.getSingleResult();
			if (lastNumber == null)
				lastNumber = 0;
			// Añadimos 1 al último número de factura
			number = Integer.toString(lastNumber + 1);
		}
		return number;
	}

}
