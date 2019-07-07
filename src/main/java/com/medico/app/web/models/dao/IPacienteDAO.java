package com.medico.app.web.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medico.app.web.models.entities.Paciente;
import com.medico.app.web.models.entities.reportes.PacienteRangoEdad;

import java.util.List;

public interface IPacienteDAO extends CrudRepository<Paciente, Integer> {
//    @Query(value = "SELECT new com.medico.app.web.models.entities.PacienteRangoEdad(P.nombre, P.apellido) FROM Paciente P")
//    public List<PacienteRangoEdad> countPacientsByAgeRange();
}
