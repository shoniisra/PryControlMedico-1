package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
	private Integer iddetalleReceta;
		
	@Column(name = "CANTIDAD")
	@Min(value = 1)
	private Integer cantidad;
	//Cantidad del medicamento en la receta 
	//ej: 1 frasco / 10 pastillas / 1 inyección
	
	@Column(name="ACTIVO")
	private Boolean activo;//se está tomando el medicamento o no
	
	@Size(max = 255)
	@Column(name = "OBSERVACION")
	private String observacion;//justificacion de por qué se interrumpe el tratamiento

	@Column(name = "NUMEROTOMAS")
	@Min(value = 0)
	private Integer numeroTomas;
	//Cantidad de veces que el medicamento va 
	//a ser suministrado al paciente
	
	@Column(name = "FRECUENCIA")
	@Min(value = 1)
	private Integer frecuencia;
	//Valor numérico del intervalo de frecuencia 
	//ej:[8] horas, [3] veces al día, [1] mensual
	//se utilizará para realizar el cálculo de horas de cada dosis
		
	@Column(name = "TIPOFRECUENCIA")
	private Integer tipoFrecuencia;
	//Complemento para el cálculo de cada dosis, ej: horas, días, semanas
	//En el front-end es un 'combobox' con un valor enumerado
	
	@Column(name = "TIPODOSIS")
	private Integer tipoDosis;
	
	@Size(max = 255)
	@Column(name = "POSOLOGIA")
	private String posologia;
	//Cantidad del medicamento que se va a suministrar al paciente en cada dosis
	//ej: 10ml , 1 pastilla, 1 aplicación, 1 inyección

	@Column(name = "FECHAINICIO", columnDefinition = "TIMESTAMP")
	private LocalDateTime fechaInicio;
	//Fecha y hora en que se suministra al paciente la 1ra dosis
	
	@JoinColumn(name="IDMEDICAMENTO", referencedColumnName = "IDMEDICAMENTO")//claves foraneas
	@ManyToOne
	private Medicamento medicamento;
	
	@Transient
	private Integer medicamentoId;
	
	@Transient
	private String descripcionTipoFrecuencia;
	
	@Transient
	private String descripcionTipoDosis;

	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL) //creo la lista de dosis en detalles receta
	@JoinColumn(name = "IDDETALLERECETA")
	private List<Dosis> dosis;
	
	public DetalleReceta() {
		super();
	}

	public DetalleReceta(Integer iddetalleReceta) {
		super();
		this.iddetalleReceta = iddetalleReceta;
	}

	public Integer getIddetalleReceta() {
		return iddetalleReceta;
	}


	public void setIddetalleReceta(Integer iddetalleReceta) {
		this.iddetalleReceta = iddetalleReceta;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Integer getNumeroTomas() {
		return numeroTomas;
	}


	public void setNumeroTomas(Integer numeroTomas) {
		this.numeroTomas = numeroTomas;
	}


	public Integer getFrecuencia() {
		return frecuencia;
	}


	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}


	public Integer getTipoFrecuencia() {
		return tipoFrecuencia;
	}


	public void setTipoFrecuencia(Integer tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}


	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}


	public List<Dosis> getDosis() {
		return dosis;
	}


	public void setDosis(List<Dosis> dosis) {
		this.dosis = dosis;
	}


	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getPosologia() {
		return posologia;
	}


	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public Integer getMedicamentoId() {
		return medicamentoId;
	}

	public void setMedicamentoId(Integer medicamentoId) {
		this.medicamentoId = medicamentoId;
	}
	
	

	public Integer getTipoDosis() {
		return tipoDosis;
	}

	public void setTipoDosis(Integer tipoDosis) {
		this.tipoDosis = tipoDosis;
	}

	public String getDescripcionTipoFrecuencia() {
		switch(this.tipoFrecuencia) {
			case 0:
				return "Una sola vez";
			case 1:
				return "Horas";
			case 2:
				return "Diaria";
			case 3:
				return "Semanal";
			case 4:
				return "Mensual";			
		}
		return "";
	}

	
	public String getDescripcionTipoDosis() {
		switch(this.tipoDosis) {
			case 0:
				return "Temporal";
			case 1:
				return "Indefinido";		
		}
		return "";
	}
}