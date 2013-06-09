package org.openxava.invoicing.calculators;

import javax.persistence.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

// Un calculador tiene que implementar ICalculator
public class NextNumberForYearCalculator implements ICalculator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3544802276051094075L;

	// Este valor se inyectará (usando su setter) antes de calcular
	private int year;

	// Hace el cálculo
	public Object calculate() throws Exception {
		// Una consulta JPA
		// La consulta devuelve el número de factura máximo del año indicado
		Query query = XPersistence.getManager().createQuery(
				"select max(i.number) from CommercialDocument i where i.year = :year");
		// Ponemos el año inyectado como parámetro de la consulta
		query.setParameter("year", year);
		Integer lastNumber = (Integer) query.getSingleResult();
		// Devuelve el último número de factura del año + 1 o 1 si no hay último
		// número
		return lastNumber == null ? 1 : lastNumber + 1;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}