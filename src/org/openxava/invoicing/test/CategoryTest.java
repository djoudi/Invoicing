package org.openxava.invoicing.test;

import org.openxava.tests.*;

public class CategoryTest extends ModuleTestBase {

	public CategoryTest(String testName) {
		super(testName, "Invoicing", "Category");
	}

	public void testCategoriesInList() throws Exception {
		assertValueInList(0, 0, "MUSIC"); // Fila 0 columan 0 contiene “MUSIC”
		assertValueInList(1, 0, "BOOKS"); // Fila 1 columna 0 contiene “BOOKS”
		assertValueInList(2, 0, "SOFTWARE"); // Fila 2 columna 0 contiene
												// “SOFTWARE”
	}

}
