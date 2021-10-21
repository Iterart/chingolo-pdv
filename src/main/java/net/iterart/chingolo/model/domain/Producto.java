package net.iterart.chingolo.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long numero;

	@Column(name = "bar_code", length = 13)
	private String codigoBarras;

	@Column(name = "descr", nullable = false, unique = true, length = 110)
	@NotBlank(message = "La descripción es requerida")
	private String descripcion;

	@Column(name = "link_img", nullable = true, unique = false, length = 255)
	private String linkImagen;

	@NotNull(message = "El precio de costo es requerido")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal precioCosto;

	@NotNull(message = "El precio de venta es requerido")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal precioVenta;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal precioEspecial;

	@NotNull(message = "El Stock Actual es requerido")
	@Column(name = "stock")
	private int stock;

	@NotNull(message = "El Stock Crítico es requerido")
	@Column(name = "stock_min")
	private int stockCritico;

	@Column(name = "obs")
	private String nota;

	@NotNull(message = "La categoría es requerida")
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@Column(name = "act", columnDefinition = "bit default 1")
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
