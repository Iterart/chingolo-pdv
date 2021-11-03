package net.iterart.chingolo.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = " * Ingresá el nombre de usuario")
	@Size(max = 65)
	//@Email(message = " * El formato de email ingresado no es válido")
	@Column(unique = true)
	private String email;

	@NotEmpty(message = " * Ingresá una contraseña")
	@Size(max = 70, min  = 6, message = " * La contraseña debe tener al menos 6 caracteres")
	private String password;

	@Column(name = "ruta_imagen")
	private String rutaImagen;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm z")
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@Column(name = "activo", columnDefinition = "boolean default 1")
	private boolean activo;

	@Column(name = "token_exp", columnDefinition = "boolean default 0")
	private boolean tokenExpired;
	
	@Column(name = "rec_email", columnDefinition = "boolean default 0")
	private boolean recibirEmail;

	@NotNull(message = "El rol es requerido")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id", referencedColumnName = "id")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Rol rol;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		activo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public boolean isRecibirEmail() {
		return recibirEmail;
	}

	public void setRecibirEmail(boolean recibirEmail) {
		this.recibirEmail = recibirEmail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return email;
	}

}
