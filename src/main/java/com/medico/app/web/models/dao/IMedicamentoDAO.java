package com.medico.app.web.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medico.app.web.models.entities.Medicamento;

public interface IMedicamentoDAO extends CrudRepository<Medicamento, Integer> {

	@Query("SELECT M FROM Medicamento M WHERE LOWER(M.nombreComercial) LIKE CONCAT('%',?1,'%')")
	public List<Medicamento> findByNombre(String criteria);
	
	public List<Medicamento> findByComponenteActivo(String criteria);
	
}
