package com.medico.app.web.models.services;


import com.medico.app.web.models.dao.IDosisDAO;
import com.medico.app.web.models.entities.Dosis;
import com.medico.app.web.models.entities.reportes.DosisSRData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DosisService implements IDosisService {

    @Autowired
    private IDosisDAO dao;

    @Override
    public void save(Dosis medicamento) {
        // TODO Auto-generated method stub
        dao.save(medicamento);
    }

    @Override
    public Dosis findById(Integer id) {
        // TODO Auto-generated method stub
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        dao.deleteById(id);
    }

    @Override
    public List<Dosis> findAll() {
        // TODO Auto-generated method stub
        return (List<Dosis>) dao.findAll();
    }

    @Override
    @Transactional
    public List<Dosis> findNotTakenPills(Integer idDetalleReceta) {
        return dao.findNotTakenPills(idDetalleReceta);
    }

    @Override
    @Transactional
    public DosisSRData findAllSuppliedPillsByDateRange(LocalDate firtsDate){
        LocalDate diaSemana = firtsDate;
        List<Integer> listaCantidadDosisSuministradas = new ArrayList<>();
        for (int i=1; i<=7; i++){
            List<Dosis> dosisSuministradas =
                    dao.findAllSuppliedPillsByDateRange(
                            LocalDateTime.of(diaSemana, LocalTime.MIN), LocalDateTime.of(diaSemana, LocalTime.MAX));
            diaSemana = diaSemana.plusDays(1);
            listaCantidadDosisSuministradas.add(dosisSuministradas.size());
        }
        DosisSRData datosChart = new DosisSRData("Suministrados", listaCantidadDosisSuministradas);
    	return datosChart;
    }

    @Override
    @Transactional
    public DosisSRData findAllRejectedPillsByDateRange(LocalDate firtsDate){
        LocalDate diaSemana = firtsDate;
        List<Integer> listaCantidadDosisRechazadas = new ArrayList<>();
        for (int i=1; i<=7; i++){
            List<Dosis> dosisRechazadas =
                    dao.findAllRejectedPillsByDateRange(
                            LocalDateTime.of(diaSemana, LocalTime.MIN), LocalDateTime.of(diaSemana, LocalTime.MAX));
            diaSemana = diaSemana.plusDays(1);
            listaCantidadDosisRechazadas.add(dosisRechazadas.size());
        }
        DosisSRData datosChart = new DosisSRData("Rechazados", listaCantidadDosisRechazadas);
        return datosChart;
    }

}