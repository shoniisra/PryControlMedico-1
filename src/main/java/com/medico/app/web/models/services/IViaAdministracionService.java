package com.medico.app.web.models.services;


import java.util.List;

import com.medico.app.web.models.entities.ViaAdministracion;

public interface IViaAdministracionService {
	
	public ViaAdministracion findById(Integer id);

	public List<ViaAdministracion> findAll();
}
