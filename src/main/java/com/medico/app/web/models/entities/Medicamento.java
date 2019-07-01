package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="MEDICAMENTO")
public class Medicamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDMEDICAMENTO")
	private Integer idmedicamento;
		
	@Column(name = "COMPONENTEACTIVO")
	@Size(max = 255)
	private String componenteActivo;
	
	@Column(name = "CONCENTRACION")
	@Size(max = 35)
	@NotEmpty
	private String concentracion;
		
	@Column(name = "NOMBRECOMERCIAL")
	@Size(max = 65)
	@NotEmpty
	private String nombreComercial;
	
	@Column(name = "PRECIO")
	@Digits(integer=8, fraction=2)
	private float precio;
	
	@Column(name = "FECHACADUCIDAD")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fechaCaducidad;
		
	@JoinColumn(name="IDVIAADMINISTRACION", referencedColumnName = "IDVIAADMINISTRACION")
	@ManyToOne	
	private ViaAdministracion viaAdministracion;
	
	public Medicamento() {
		super();
	}

	public Medicamento(Integer idmedicamento) {
		super();
		this.idmedicamento = idmedicamento;
	}

	public Integer getIdmedicamento() {
		return idmedicamento;
	}
	
	public String getComponenteActivo() {
		return componenteActivo;
	}

	public void setComponenteActivo(String componenteActivo) {
		this.componenteActivo = componenteActivo;
	}

	public void setIdmedicamento(Integer idmedicamento) {
		this.idmedicamento = idmedicamento;
	}

	public ViaAdministracion getViaAdministracion() {
		return viaAdministracion;
	}

	public void setViaAdministracion(ViaAdministracion viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getConcentracion() {
		return concentracion;
	}

	public void setConcentracion(String concentracion) {
		this.concentracion = concentracion;
	}
	
	public Calendar getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Calendar fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	

	
	
}
