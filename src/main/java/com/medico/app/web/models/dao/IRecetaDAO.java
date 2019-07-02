package com.medico.app.web.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medico.app.web.models.entities.Receta;

public interface IRecetaDAO extends CrudRepository<Receta, Integer> {
    @Modifying
    @Query("update Receta R set R.activo = 'false' WHERE R.idreceta = ?1")
    public void setRecetaInactiveStatus(Integer id);
}
