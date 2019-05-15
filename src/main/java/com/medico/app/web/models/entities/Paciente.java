package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="PACIENTE")
public class Paciente extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	@Column(name = "ALERGIAS")
	@NotEmpty
	private String alergias;
	
	@JoinColumn(name="IDTIPOSANGRE", referencedColumnName = "IDTIPOSANGRE")
	@ManyToOne
	private TipoSangre tipoSangre;
	
	@OneToMany(mappedBy="paciente", fetch=FetchType.LAZY)//LAZY, trae los valores de los atributos y no todo el listado 
	private List<Receta> recetas;

	public Paciente() {
		super();
	}

	public Paciente(Integer Id) {
		super();
		this.setIdpersona(Id);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public TipoSangre getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(TipoSangre tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
}
