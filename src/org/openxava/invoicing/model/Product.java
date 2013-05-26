package org.openxava.invoicing.model;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Product {

	@Id
	@Column(length = 9)
	private int number;
	
	@Column(length = 50)
	@Required
	private String description;
	
	@ManyToOne( // La referencia se almacena como una relación en la base de datos
	fetch = FetchType.LAZY, // La referencia se carga bajo demanda
	optional = true) // La referencia puede estar sin valor	
	@DescriptionsList // Así la referencia se visualiza usando un combo	
	private Category category; // Una referencia Java convencional

	@Stereotype("MONEY")
	// La propiedad price se usa para almacenar dinero
	private BigDecimal price; // BigDecimal se suele usar para dinero

	@Stereotype("PHOTO")
	// El usuario puede ver y cambiar una foto
	private byte[] photo;

	@Stereotype("IMAGES_GALLERY")
	// Una galería de fotos completa está disponible
	@Column(length = 32)
	// La cadena de 32 de longitud es para almacenar la clave de la galería
	private String morePhotos;

	@Stereotype("MEMO")
	// Esto es para un texto grande, se usará un área de texto o equivalente
	private String remarks;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getMorePhotos() {
		return morePhotos;
	}

	public void setMorePhotos(String morePhotos) {
		this.morePhotos = morePhotos;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
