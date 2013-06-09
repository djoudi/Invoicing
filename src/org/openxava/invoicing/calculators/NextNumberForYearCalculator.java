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

	// Este valor se inyectar� (usando su setter) antes de calcular
	private int year;

	// Hace el c�lculo
	public Object calculate() throws Exception {
		// Una consulta JPA
		// La consulta devuelve el n�mero de factura m�ximo del a�o indicado
		Query query = XPersistence.getManager().createQuery(
				"select max(i.number) from CommercialDocument i where i.year = :year");
		// Ponemos el a�o inyectado como par�metro de la consulta
		query.setParameter("year", year);
		Integer lastNumber = (Integer) query.getSingleResult();
		// Devuelve el �ltimo n�mero de factura del a�o + 1 o 1 si no hay �ltimo
		// n�mero
		return lastNumber == null ? 1 : lastNumber + 1;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}