package com.medico.app.web.models.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.medico.app.web.models.dao.IRecetaDAO;
import com.medico.app.web.models.entities.Receta;

@Service
public class RecetaService implements IRecetaService {
	
	@Autowired
	private IRecetaDAO dao;
	
	@Override
	@Transactional
	public void create(Receta receta) {		
		em.persist(receta);
	}

		dao.save(Receta);
	}

	@Override
	@Transactional
	public void update(Receta receta) {
		em.merge(receta);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Receta> findAll() {
		return (List<Receta>) dao.findAll();

	}
}
