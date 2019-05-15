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

@Entity
@Table(name="TIPOSANGRE")
public class TipoSangre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIPOSANGRE")
    private Integer idtiposangre;

    @Column(name = "NOMBRE")
    private String nombre;

    @OneToMany(mappedBy = "tipoSangre",fetch=FetchType.LAZY)
    private List<Paciente> pacientes;

    public TipoSangre() {
        super();
    }

    public TipoSangre(Integer id) {
        super();
        this.idtiposangre = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdtiposangre() {
        return idtiposangre;
    }

    public void setIdtiposangre(Integer idtiposangre) {
        this.idtiposangre = idtiposangre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
