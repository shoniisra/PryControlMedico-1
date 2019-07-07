package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.Dosis;
import com.medico.app.web.models.entities.reportes.DosisSuministradaRechazada;
import com.medico.app.web.models.entities.reportes.PacienteRangoEdad;
import com.medico.app.web.models.services.IDosisService;
import com.medico.app.web.models.services.IPacienteService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@Controller
@RequestMapping(value="/reportes")
public class ReporteController {
    @Autowired
    private IPacienteService srvPaciente;
    
    @Autowired
    private IDosisService srvDosis;
    
    @GetMapping(value="/")
    public String index(Model model) {
        return "reportes";
    }
    
	@PostMapping(value="/agerange", produces="application/json")
	public @ResponseBody List<PacienteRangoEdad> ageRange() {
		List<PacienteRangoEdad> listaReporte = srvPaciente.countPacientsByAgeRange();
    	return listaReporte;
    }

    @PostMapping(value="/srdosis", produces="application/json")
    public @ResponseBody DosisSuministradaRechazada srDosis() {
        DosisSuministradaRechazada dosisRechazadasSuministradasByDay = new DosisSuministradaRechazada();
        LocalDate fechaActual = LocalDate.now();
        LocalDate firstDayOfWeek = fechaActual.with(previousOrSame(MONDAY));
        dosisRechazadasSuministradasByDay.setDaysInList(firstDayOfWeek);
        System.out.print(dosisRechazadasSuministradasByDay.getCategory());
        dosisRechazadasSuministradasByDay.addDosisData(srvDosis.findAllSuppliedPillsByDateRange(firstDayOfWeek));
        dosisRechazadasSuministradasByDay.addDosisData(srvDosis.findAllRejectedPillsByDateRange(firstDayOfWeek));
        return dosisRechazadasSuministradasByDay;
    }
}
