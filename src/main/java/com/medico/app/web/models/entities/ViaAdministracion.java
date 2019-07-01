package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="VIAADMINISTRACION")
public class ViaAdministracion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDVIAADMINISTRACION")
	private Integer idviadaministracion;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CATEGORIA")
	private String categoria;
	
	@JsonIgnore
	@OneToMany(mappedBy = "viaAdministracion",fetch=FetchType.LAZY)    
    private List<Medicamento> medicamentos;
		
	public ViaAdministracion() {
		super();
	}

	public ViaAdministracion(Integer id) {
		super();
		this.idviadaministracion = id;
	}

	public Integer getIdviadaministracion() {
		return idviadaministracion;
	}

	public void setIdviadaministracion(Integer idviadaministracion) {
		this.idviadaministracion = idviadaministracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	
	
	

}
