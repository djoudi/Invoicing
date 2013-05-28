package org.openxava.invoicing.calculators;

import javax.persistence.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

public class NextNumberForYearCalculator implements ICalculator { // Un calculador tiene que implementar ICalculator
   /**
	 * 
	 */
	private static final long serialVersionUID = 3544802276051094075L;

	private int year; // Este valor se inyectar� (usando su setter) antes de
						// calcular

	public Object calculate() throws Exception { // Hace el c�lculo
		Query query = XPersistence.getManager() // Una consulta JPA
				.createQuery(
						"select max(i.number) from Invoice i"
								+ " where i.year = :year"); // La consulta
															// devuelve el
															// n�mero de factura
		// m�ximo del a�o indicado
		query.setParameter("year", year); // Ponemos el a�o inyectado como
											// par�metro
		// de la consulta
		Integer lastNumber = (Integer) query.getSingleResult();
		return lastNumber == null ? 1 : lastNumber + 1; // Devuelve el �ltimo
														// n�mero
		// de factura del a�o + 1
		// o 1 si no hay �ltimo n�mero
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}