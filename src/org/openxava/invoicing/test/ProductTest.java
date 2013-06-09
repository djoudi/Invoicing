package org.openxava.invoicing.test;

import java.math.*;
import static org.openxava.jpa.XPersistence.*;
import org.openxava.invoicing.model.*;
import org.openxava.tests.*;

public class ProductTest extends ModuleTestBase {

	private Author author; // Declaramos la entidades a crear
	private Category category; // como miembros de instancia para
	private Product product1; // que estén disponibles en todos los métodos de
								// prueba
	private Product product2; // y puedan ser borradas al final de cada prueba

	public ProductTest(String nameTest) {
		super(nameTest, "Invoicing", "Product");
		// TODO Auto-generated constructor stub
	}

	// setUp() se ejecuta siempre antes de cada prueba
	@Override
	protected void setUp() throws Exception {
		createProducts(); // Crea los datos usados en las pruebas
		super.setUp();
	}

	// tearDown() se ejecuta siempre después de cada prueba
	@Override
	protected void tearDown() throws Exception {
		super.tearDown(); // Necesario, ModuleTestBase cierra recursos aquí
		removeProducts(); // Se borran los datos usado para pruebas
	}

	public void testRemoveFromList() throws Exception {

		setConditionValues( // Establece los valores para filtrar los datos
		new String[] { "", "JUNIT" });

		setConditionComparators( // Pone los comparadores para filtrar los datos
		new String[] { "=", "contains_comparator" });

		execute("List.filter"); // Pulsa el botón para filtrar
		assertListRowCount(2); // Verifica que hay 2 filas
		checkRow(1); // Seleccionamos la fila 1 (que resulta ser la segunda)
		execute("CRUD.deleteSelected"); // Pulsa en el botón para borrar
		assertListRowCount(1); // Verifica que ahora solo hay una fila
	}

	public void testChangePrice() throws Exception {
		// Buscar product1
		execute("CRUD.new");
		setValue("number", Integer.toString(product1.getNumber())); // (1)

		execute("CRUD.refresh");
		assertValue("price", "10.00");
		
		// Cambiar el precio
		setValue("price", "12.00");
		execute("CRUD.save");
		assertNoErrors();
		assertValue("price", "");
		
		// Verificar
		setValue("number", Integer.toString(product1.getNumber())); // (1)
		execute("CRUD.refresh");
		assertValue("price", "12.00");
	}

	private void createProducts() {
		// Crear objetos Java
		author = new Author(); // Se crean objetos de Java convencionales
		author.setName("JUNIT Author"); // Usamos setters como se suele hacer
										// con Java
		category = new Category();
		category.setDescription("JUNIT Category");

		product1 = new Product();
		product1.setNumber(900000001);
		product1.setDescription("JUNIT Product 1");
		product1.setAuthor(author);
		product1.setCategory(category);
		product1.setPrice(new BigDecimal("10"));

		product2 = new Product();
		product2.setNumber(900000002);
		product2.setDescription("JUNIT Product 2");
		product2.setAuthor(author);
		product2.setCategory(category);
		product2.setPrice(new BigDecimal("20"));

		// Marcar los objetos como persistentes
		getManager().persist(author); // getManager() es de XPersistence
		commit(); // El contexto persistente actual se termina, y author pasa a
					// estar desasociado
		getManager().remove(author); // Falla porque author está desasociado
		author = getManager().merge(author); // Reasocia author al contexto
												// actual
		getManager().remove(author); // Funciona

		getManager().persist(category); // persist() marca el objeto como
										// persistente
		getManager().persist(product1); // para que se grabe en la base de datos
		getManager().persist(product2);

		// Confirma los cambios en la base de datos
		commit(); // commit() es de XPersistence. Graba todos los objetos en la
					// base de datos
		// y confirma la transacción

	}

	private void removeProducts() {// Llamado desde tearDown()
		// por tanto ejecutado después de cada prueba
		remove(product1, product2, author, category); // remove() borra
		commit(); // Confirma los datos en la base de datos, en este caso
					// borrando datos
	}

	private void remove(Object... entities) { // Usamos argumentos varargs
		for (Object entity : entities) { // Iteramos por todos los argumentos
			getManager().remove(getManager().merge(entity)); // Borrar(1)
		}
	}
}
