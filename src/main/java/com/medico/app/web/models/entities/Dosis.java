package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="DOSIS")
public class Dosis implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDDOSIS")
	private Integer iddosis;

	@Column(name = "FECHAHORA", columnDefinition = "TIMESTAMP")
	private LocalDateTime fechaHora;
	
	@Column(name = "NUMERO")
	@Min(value = 1)
	private Integer numero;
	
	@Size(max = 255)
	@Column(name = "DESCRIPCION")
	private String descripcion;//justificacion de por qué se interrumpe el tratamiento
	
	@Column(name = "ESTADO")
	@Min(value = 0)
	private Integer estado = 0;

	@Transient
	private String descripcionEstadoDosis;

	@JoinColumn(name="IDDETALLERECETA", referencedColumnName = "IDDETALLERECETA")//claves foraneas
	@ManyToOne
	private DetalleReceta detalleReceta;

	public Dosis() {
		super();
	}

	public Dosis(Integer iddosis) {
		super();
		this.iddosis = iddosis;
	}

	public Integer getIddosis() {
		return iddosis;
	}

	public void setIddosis(Integer iddosis) {
		this.iddosis = iddosis;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getDescripcionEstadoDosis() {
		switch(this.estado) {
			case 0:
				return "Pendiente";
			case 1:
				return "Notificado";
		}
		return "";
	}
	public LocalDateTime calcularFechaSiguienteDosis(LocalDateTime fechaHoraDosisAnterior, int frecuencia, int tipoFrecuencia){
		LocalDateTime fechaHoraNuevaDosis = fechaHoraDosisAnterior;
		switch(tipoFrecuencia) {
			case 0:
				fechaHoraNuevaDosis = fechaHoraDosisAnterior; // Una sola vez
				break;
			case 1:
				fechaHoraNuevaDosis = fechaHoraDosisAnterior.plusHours(frecuencia); //Horas
				break;
			case 2:
				fechaHoraNuevaDosis = fechaHoraDosisAnterior.plusDays(frecuencia); // Diaria
				break;
			case 3:
				fechaHoraNuevaDosis = fechaHoraDosisAnterior.plusWeeks(frecuencia); // Semanal
				break;
			case 4:
				fechaHoraNuevaDosis = fechaHoraDosisAnterior.plusMonths(frecuencia); //Mensual
				break;
		}
		return fechaHoraNuevaDosis;
	}

	public DetalleReceta getDetalleReceta() {
		return detalleReceta;
	}

	public void setDetalleReceta(DetalleReceta detalleReceta) {
		this.detalleReceta = detalleReceta;
	}

}