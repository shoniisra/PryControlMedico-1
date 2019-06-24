package com.medico.app.web.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medico.app.web.models.dao.IRecetaDAO;
import com.medico.app.web.models.entities.Receta;

@Service
public class RecetaService implements IRecetaService {
	
	@Autowired
	private IRecetaDAO dao;
	
	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Receta> findAll() {
		return (List<Receta>) dao.findAll();

	}

	@Override
	@Transactional
	public void save(Receta receta) {
		try {
			dao.save(receta);
		}
		catch(Exception ex) {
			throw ex;
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public Receta findById(Integer id) {		
		return dao.findById(id).get();
	}
}
