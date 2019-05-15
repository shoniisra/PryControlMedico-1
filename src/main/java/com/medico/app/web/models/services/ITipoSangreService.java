package com.medico.app.web.models.services;

import com.medico.app.web.models.entities.TipoSangre;

import java.util.List;

public interface ITipoSangreService {
    public TipoSangre findById(Integer id);

    public List<TipoSangre> findAll();
}
