package com.medico.app.web.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medico.app.web.models.dao.IMedicamentoDAO;
import com.medico.app.web.models.entities.Medicamento;

@Service
public class MedicamentoService implements IMedicamentoService {

	@Autowired
	private IMedicamentoDAO dao;
	
	@Override
	@Transactional
	public void save(Medicamento medicamento) {		
		dao.save(medicamento);
	}

	@Override
	@Transactional(readOnly=true)
	public Medicamento findById(Integer id) {		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {		
		dao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Medicamento> findByNombre(String criteria) {		
		return dao.findByNombre(criteria.toLowerCase());
	}

	@Override
	@Transactional(readOnly=true)
	public List<Medicamento> findByComponenteActivoLike(String criteria) {		
		return dao.findByComponenteActivo(criteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Medicamento> findByAll() {
		return (List<Medicamento>) dao.findAll();
	}

}
