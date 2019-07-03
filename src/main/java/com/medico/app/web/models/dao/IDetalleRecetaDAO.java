package com.medico.app.web.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medico.app.web.models.entities.DetalleReceta;

import javax.transaction.Transactional;
import java.util.List;

public interface IDetalleRecetaDAO extends CrudRepository<DetalleReceta, Integer> {
    @Query("SELECT D FROM DetalleReceta D WHERE D.receta.idreceta = ?1 AND D.activo = 'TRUE'")
    public List<DetalleReceta> findNotTakenDetalles(Integer idReceta);

    @Modifying
    @Transactional
    @Query("update DetalleReceta D set D.activo = 'false' WHERE D.iddetalleReceta = ?1")
    public void setDetalleRecetaInactiveStatus(Integer id);
}
