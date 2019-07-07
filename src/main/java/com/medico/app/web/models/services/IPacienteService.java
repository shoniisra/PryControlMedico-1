package com.medico.app.web.models.services;

import java.util.List;

import com.medico.app.web.models.entities.Paciente;
import com.medico.app.web.models.entities.reportes.PacienteRangoEdad;

public interface IPacienteService {

	public void save(Paciente paciente);
	
	public Paciente findById(Integer id);
	
	public void delete(Integer id);
	
	public List<Paciente> findAll();

	public List<PacienteRangoEdad> countPacientsByAgeRange();
}
