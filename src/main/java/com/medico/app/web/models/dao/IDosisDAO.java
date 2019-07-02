package com.medico.app.web.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medico.app.web.models.entities.Dosis;

import java.util.List;

public interface IDosisDAO extends CrudRepository<Dosis, Integer> {
    @Query("SELECT D FROM Dosis D WHERE D.detalleReceta.iddetalleReceta = ?1 AND D.estado <> 1")
    public List<Dosis> findNotTakenPills(Integer idDetalleReceta);
}
