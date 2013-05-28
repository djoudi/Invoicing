package org.openxava.invoicing.test;

import org.openxava.tests.*;

public class CustomerTest extends ModuleTestBase { //Extiende de ModuleTestBase

	public CustomerTest(String testName) {
		
		super(testName, "Invoicing", // Indicamos el nombre de aplicaci�n(Invoicing)										
				"Customer"); // y nombre de m�dulo (Customer)
	}
	
	// Los m�todos de prueba han de empezar por 'test'
	public void testCreateReadUpdateDelete() throws Exception { 

		// CREAR
		execute("CRUD.new"); // Pulsa el bot�n 'New'
		setValue("number", "77"); // Teclea 77 como valor para el campo 'number'
		setValue("name", "JUNIT Customer"); // Pone valor en el campo 'name'
		// F�jate en la notaci�n del punto para acceder al miembro de la referencia
		setValue("address.street", "JUNIT Street"); 											
		setValue("address.zipCode", "77555"); // Etc
		setValue("address.city", "The JUNIT city"); // Etc
		setValue("address.state", "The JUNIT state"); // Etc
		
		// GUARDAR
		execute("CRUD.save");
		assertNoErrors();
		assertValue("number", ""); // Verifica que el campo 'number' est� vac�o
		assertValue("name", ""); // Verifica que el campo 'name' est� vac�o
		assertValue("address.street", ""); // Etc
		assertValue("address.zipCode", ""); // Etc
		assertValue("address.city", ""); // Etc
		assertValue("address.state", ""); // Etc
		
		// LEER
		setValue("number", "77"); // Pone 77 como valor para el campo 'number'
		execute("CRUD.refresh"); // Pulsa el bot�n 'Refresh'
		assertValue("number", "77"); // Verifica que el campo 'number' tiene un 77
		assertValue("name", "JUNIT Customer"); // y 'name' tiene 'JUNIT Customer'
		assertValue("address.street", "JUNIT Street"); // Etc
		assertValue("address.zipCode", "77555"); // Etc
		assertValue("address.city", "The JUNIT city"); // Etc
		assertValue("address.state", "The JUNIT state"); // Etc
		
		// ACTUALIZAR
		setValue("name", "JUNIT Customer MODIFIED"); // Cambia el valor del campo 'name'
		execute("CRUD.save"); // Pulsa el bot�n 'Search'
		assertNoErrors(); // Verifica que la aplicaci�n no muestra errores
		assertValue("number", ""); // Verifica que el campo 'number' est� vac�o
		assertValue("name", ""); // Verifica que el campo 'name' est� vac�o
		
		// VERIFICA SI SE HA ACTUALIZADO
		setValue("number", "77"); // Pone 77 como valor para el campo 'number'
		execute("CRUD.refresh"); // Pulsa en el bot�n 'Refresh'
		assertValue("number", "77"); // Verifica que el campo 'number' tiene un 77
		assertValue("name", "JUNIT Customer MODIFIED"); // y 'name'
		
		// BORRAR
		execute("CRUD.delete"); // Pulsa en el bot�n 'Delete'
		assertMessage("Customer deleted successfully"); // Verifica que el mensaje
		// 'Customer deleted successfully' se muestra al usuario
	}
}
