package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="PACIENTE")
public class Paciente extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	@Column(name = "ALERGIAS")
	@NotEmpty
	private String alergias;
	
	@Size(max = 3)
	@Column(name = "TIPOSANGRE")
	@NotEmpty
	private String tipoSangre;
	
	@Size(max = 255)
	@Column(name = "ANTECEDENTES")
	@NotEmpty
	private String antecedentes;
	
	@Size(max = 25)
	@Column(name = "CREADOPOR")
	@NotEmpty
	private String creadoPor;
	
	@OneToMany(mappedBy="paciente", fetch=FetchType.LAZY)//LAZY, trae los valores de los atributos y no todo el listado 
	private List<Receta> recetas;

	public Paciente() {
		super();
	}

	public Paciente(Integer Id) {
		super();
		this.setIdpersona(Id);
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	
	@PrePersist
    public void prePersist() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        creadoPor = auth.getName();        
    }
	
}
