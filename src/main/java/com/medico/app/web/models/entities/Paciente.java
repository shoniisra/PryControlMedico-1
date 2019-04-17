package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="PACIENTE")
public class Paciente extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(max=255)
	@Column(name = "ALERGIAS")
	private String alergias;
	
	@Size(max=3)
	@Column(name = "TIPOSANGRE")
	private String tipoSangre;
	
	@OneToMany(mappedBy="paciente", fetch = FetchType.LAZY)
	private List<Receta> recetas;

	public Paciente() {
		super();
	}
	
	public Paciente(Integer id) {
		super();
		this.setIdpersona(id);
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
	
	

}




