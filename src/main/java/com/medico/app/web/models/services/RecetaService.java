package com.medico.app.web.models.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Receta> findAll() {
		return (List<Receta>) dao.findAll();

	}

	@Override
	public void save(Receta receta) {
		dao.save(receta);
		
	}

	@Override
	public Receta findById(Integer id) {		
		return dao.findById(id).get();
	}
}
