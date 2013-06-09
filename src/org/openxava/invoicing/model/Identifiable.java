package org.openxava.invoicing.model;

import javax.persistence.*;
import org.hibernate.annotations.*;
import org.openxava.annotations.*;

@MappedSuperclass
public class Identifiable {

	@Id @GeneratedValue(generator = "system-uuid") @Hidden
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String oid; // La definición de propiedad incluye anotaciones de OX y JPA

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	
	
}
