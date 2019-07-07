package com.medico.app.web.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medico.app.web.models.entities.Dosis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IDosisDAO extends CrudRepository<Dosis, Integer> {
    @Query("SELECT D FROM Dosis D WHERE D.detalleReceta.iddetalleReceta = ?1 AND D.estado = 0")
    public List<Dosis> findNotTakenPills(Integer idDetalleReceta);
    
    @Query("SELECT D FROM Dosis D WHERE D.estado = 1 AND D.fechaHora BETWEEN ?1 AND ?2")
    public List<Dosis> findAllSuppliedPillsByDateRange(LocalDateTime firtsDate, LocalDateTime nextDate);

    @Query("SELECT D FROM Dosis D WHERE D.estado = 2 AND D.fechaHora BETWEEN ?1 AND ?2")
    public List<Dosis> findAllRejectedPillsByDateRange(LocalDateTime firtsDate, LocalDateTime nextDate);
}
