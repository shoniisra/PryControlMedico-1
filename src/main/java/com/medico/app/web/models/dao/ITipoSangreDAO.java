package com.medico.app.web.models.dao;

import com.medico.app.web.models.entities.TipoSangre;
import org.springframework.data.repository.CrudRepository;

public interface ITipoSangreDAO extends CrudRepository<TipoSangre, Integer> {
}
