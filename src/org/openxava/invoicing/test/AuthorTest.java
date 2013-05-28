package org.openxava.invoicing.test;

import org.openxava.tests.*;

public class AuthorTest extends ModuleTestBase {

	public AuthorTest(String nameTest, String module) {
		super(nameTest, "Invoicing", "Author");
	}

	public void testReadAuthor() throws Exception {
		assertValueInList(0, 0, "JAVIER CORCOBADO"); // El primer Author en la
		// lista es JAVIER CORCOBADO
		execute("Mode.detailAndFirst"); // Al cambiar a modo detalle
		// se visualiza el primero objeto de la lista
		assertValue("name", "JAVIER CORCOBADO");
		assertCollectionRowCount("products", 2); // Tiene 2 productos
		assertValueInCollection("products", 0, // Fila 0 de products
				"number", "2"); // tiene “2” en la columna “number”
		assertValueInCollection("products", 0, "description",
				"Arco iris de lágrimas");
		assertValueInCollection("products", 1, "number", "3");
		assertValueInCollection("products", 1, "description", "Ritmo de sangre");
	}

}
