package net.iterart.chingolo.model.domain;

import java.math.BigDecimal;

public class Producto {

	private Long numero;
	private String codigoBarras;
	private String descripcion;
	private String linkImagen;
	private BigDecimal precioCosto;
	private BigDecimal precioVenta;
	private BigDecimal precioEspecial;
	private int stock;
	private int stockCritico;
	private String nota;
	private boolean activo;

	public Producto() {

	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}

	public BigDecimal getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(BigDecimal precioCosto) {
		this.precioCosto = precioCosto;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getPrecioEspecial() {
		return precioEspecial;
	}

	public void setPrecioEspecial(BigDecimal precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockCritico() {
		return stockCritico;
	}

	public void setStockCritico(int stockCritico) {
		this.stockCritico = stockCritico;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Producto [descripcion=" + descripcion + "]";
	}

}
