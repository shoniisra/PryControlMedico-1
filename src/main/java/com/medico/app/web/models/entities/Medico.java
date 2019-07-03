package com.medico.app.web.models.entities;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

	@Size(max = 25)
	@Column(name = "CREADOPOR")
	private String creadoPor;

	@Column(name = "CREADOEN")
	private LocalDateTime creadoEn;

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

	@PrePersist
	public void prePersist() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		creadoPor = auth.getName();
		creadoEn = LocalDateTime.now();
	}
}
