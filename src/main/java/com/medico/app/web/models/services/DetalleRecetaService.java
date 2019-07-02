package com.medico.app.web.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medico.app.web.models.dao.IDetalleRecetaDAO;
import com.medico.app.web.models.entities.DetalleReceta;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleRecetaService implements IDetalleRecetaService {

	@Autowired
	private IDetalleRecetaDAO dao;
	
	@Override
	public void save(DetalleReceta detalleReceta) {
		// TODO Auto-generated method stub
		dao.save(detalleReceta);
	}

	@Override
	public DetalleReceta findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<DetalleReceta> findAll() {
		// TODO Auto-generated method stub
		return (List<DetalleReceta>) dao.findAll();
	}

	@Override
	@Transactional
	public List<DetalleReceta> findNotTakenDetalles(Integer idReceta) {
		return dao.findNotTakenDetalles(idReceta);
	}

	@Override
	@Transactional
	public void setDetalleRecetaInactiveStatus(Integer id) {
		dao.setDetalleRecetaInactiveStatus(id);
	}
}
