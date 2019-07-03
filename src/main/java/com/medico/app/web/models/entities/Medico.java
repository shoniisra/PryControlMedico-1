package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="MEDICO")
public class Medico extends Persona implements Serializable{

	private static final long serialVersionUID = 1L; //Aplica para serialización

	@Size(max = 35)
	@Column(name = "LICENCIA")
	@NotEmpty
	private String licencia;

	@Size(max = 255)
	@Column(name = "ESPECIALIDAD")
	private String especialidad;
	
	@Column(name = "CREADOPOR")
	private String creadoPor;
	
	@Column(name = "CREADOEN")
	private LocalDateTime creadoEn;
	
	@Column(name = "ACTUALIZADOPOR")
	private String actualizadoPor;
	
	@Column(name = "ACTUALIZADOPEN")
	private LocalDateTime actualizadoEn;
	
	@OneToMany(mappedBy="medico", fetch=FetchType.LAZY)//LAZY, trae los valores de los atributos y no todo el listado 
	private List<Receta> recetas;
	
	
	public Medico() {
		super();
	}

	public Medico(Integer id) {
		super();
		this.setIdpersona(id);
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}

	public String getActualizadoPor() {
		return actualizadoPor;
	}

	public void setActualizadoPor(String actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	public LocalDateTime getActualizadoEn() {
		return actualizadoEn;
	}

	public void setActualizadoEn(LocalDateTime actualizadoEn) {
		this.actualizadoEn = actualizadoEn;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
	
	@PrePersist
    public void prePersist() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        creadoPor = auth.getName();  
        creadoEn = LocalDateTime.now();
    }
	
	@PreUpdate
	public void preUpdate() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        actualizadoPor = auth.getName();  
        actualizadoEn = LocalDateTime.now();
	}
	
	
}
