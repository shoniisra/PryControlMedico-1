package com.medico.app.web.models.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.medico.app.web.models.entities.Dosis;
import com.medico.app.web.models.entities.reportes.DosisSRData;

public interface IDosisService {

	public void save(Dosis dosis);

	public void delete(Integer id);
	
	public Dosis findById(Integer id);
	
	public List<Dosis> findAll();

	public List<Dosis> findNotTakenPills(Integer idDetalleReceta);

	public DosisSRData findAllSuppliedPillsByDateRange(LocalDate firtsDate);

	public DosisSRData findAllRejectedPillsByDateRange(LocalDate firtsDate);

}
