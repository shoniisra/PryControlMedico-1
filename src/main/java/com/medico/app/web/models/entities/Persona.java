package com.medico.app.web.models.entities;

import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDPERSONA")
	private Integer idpersona;
	
	@Size(max = 15)
	@Column(name = "CEDULA")
	private String cedula;
	
	@Size(max = 35)
	@Column(name = "NOMBRES")
	@NotEmpty(message = "Campo Obligatorio")
	private String nombre;
	
	@Size(max = 35)
	@Column(name = "APELLIDOS")
	@NotEmpty
	private String apellido;
	
	@Size(max = 15)
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Size(max = 35)
	@Column(name = "EMAIL")
	@Email
	private String email;
	
	public Persona() {
		super();
	}

	public Persona(Integer idpersona) {
		super();
		this.idpersona = idpersona;
	}

	public Integer getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(Integer idpersona) {
		this.idpersona = idpersona;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

	
	
	

	
}
