package net.iterart.chingolo.model.domain;

public class Categoria {
	
	private Long numero;
	private String nombre;
	
	public Categoria() {
		
	}

	public Categoria(Long numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return  numero + " - " + nombre;
	}
	
	
	

}
