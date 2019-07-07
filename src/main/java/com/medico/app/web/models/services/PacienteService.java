package com.medico.app.web.models.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medico.app.web.models.dao.IPacienteDAO;
import com.medico.app.web.models.entities.Paciente;
import com.medico.app.web.models.entities.reportes.PacienteRangoEdad;

@Service
public class PacienteService implements IPacienteService {

	@Autowired
	private IPacienteDAO dao;
	
	@Override
	public void save(Paciente paciente) {
		// TODO Auto-generated method stub
		dao.save(paciente);
	}

	@Override
	public Paciente findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		return (List<Paciente>) dao.findAll();
	}

	@Override
	public List<PacienteRangoEdad> countPacientsByAgeRange() {
		List<Paciente> pacientes = findAll();
		LocalDate fechaActual = LocalDate.now();
		int [] contadorEdades = {0,0,0,0,0,0};
		ArrayList<PacienteRangoEdad> cantidadRango = new ArrayList<PacienteRangoEdad>();
		try{
			for (Paciente paciente: pacientes) {
				int edadActual = (int)ChronoUnit.YEARS.between(paciente.getNacimiento(), fechaActual);
				if (edadActual>=0 && edadActual<=3){
					contadorEdades[0] += 1;
				}else if(edadActual>=4 && edadActual<=12){
					contadorEdades[1] +=1;
				}else if(edadActual>=13 && edadActual<=19){
					contadorEdades[2] +=1;
				}else if(edadActual>=20 && edadActual<=35){
					contadorEdades[3] +=1;
				}else if(edadActual>=36 && edadActual<=65){
					contadorEdades[4] +=1;
				}else if(edadActual>=66){
					contadorEdades[5] +=1;
				}
			}
			cantidadRango.add(new PacienteRangoEdad("Infantes 0 - 3 años", contadorEdades[0]));
			cantidadRango.add(new PacienteRangoEdad("Niños 4 - 12 años", contadorEdades[1]));
			cantidadRango.add(new PacienteRangoEdad("Adolescentes 13 - 19 años", contadorEdades[2]));
			cantidadRango.add(new PacienteRangoEdad("Jovenes 20 - 35 años", contadorEdades[3]));
			cantidadRango.add(new PacienteRangoEdad("Adultos 36 - 65 años", contadorEdades[4]));
			cantidadRango.add(new PacienteRangoEdad("Adultos Mayores > 66 años", contadorEdades[5]));
		}catch (Exception e){
			System.out.println(e);
		}
		return cantidadRango;
	}

}
