package com.medico.app.web.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="DETALLERECETA")
public class DetalleReceta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDDETALLERECETA")
	private Integer iddetallereceta;
	
	@Column(name = "CANTIDAD")
	@Min(value=1)
	private Integer cantidad;
	
	@Column(name = "ACTIVO")
	private Boolean activo; //Si fue interrumpido no estaría activo
	
	@Size(max=255)
	@Column(name = "OBSERVACION")
	private String observacion;
	
	@JoinColumn(name="IDRECETA", referencedColumnName="IDRECETA")
	@ManyToOne
	private Receta receta;
			
	
	public DetalleReceta() {		
	}
		
	public DetalleReceta(Integer id) {
		this.iddetallereceta = id;
	}
	
	
	
	
	

}
