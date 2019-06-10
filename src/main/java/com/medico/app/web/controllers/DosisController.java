package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.*;
import com.medico.app.web.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value="/dosis")
public class DosisController {

    @Autowired
    private IDosisService service;

    @Autowired
    private IPacienteService srvPaciente;

    @Autowired
    private IMedicoService srvMedico;

    @Autowired
    private IDosisService srvDosis;


    @Autowired
    private IDetalleRecetaService srvDetalleReceta;

    @GetMapping(value="/create" )
    public String create(Model model){
        Dosis dosis=new Dosis();
        model.addAttribute("dosis",dosis);
        return "dosis/form";
    }
    @PostMapping(value="/save" )
    public String save(Dosis dosis, Model model){
        try{
            service.save(dosis);
        }catch (Exception ex){
            model.addAttribute("error: ",ex.toString());
        }
        return "redirect:/dosis/list";
    }

    @GetMapping(value="/generatedosis" )
    public String createDosis(@ModelAttribute("receta_guardada") Receta receta, Model model,
                              RedirectAttributes message, SessionStatus session){
        Paciente paciente = srvPaciente.findById(receta.getPaciente().getIdpersona());
        Medico medico = srvMedico.findById(receta.getMedico().getIdpersona());
        for (int i=0; i<receta.getDetalles().size(); i++){
            ArrayList<Dosis> listaDosis = new ArrayList<Dosis>();
            DetalleReceta detalleReceta = receta.getDetalles().get(i);
            detalleReceta.setNumeroTomas(detalleReceta.getCantidad());
            LocalDateTime fechaDosisAnterior = detalleReceta.getFechaInicio();
            for (int j=0;j<detalleReceta.getNumeroTomas(); j++){
                Dosis nuevaDosis = new Dosis();
                nuevaDosis.setNumero(j+1);
                String descripcionNotificacion = "Recordatorio de dosis del Medicamento: " +
                        detalleReceta.getMedicamento().getNombreComercial() +
                        " del paciente: " + paciente.getNombre() + " " + paciente.getApellido() +
                        " . Tomar/Usar: " + detalleReceta.getPosologia() + ".";
                nuevaDosis.setDescripcion(descripcionNotificacion);
                if(j==0){
                    nuevaDosis.setFechaHora(fechaDosisAnterior);
                }else{
                    nuevaDosis.setFechaHora(
                            nuevaDosis.calcularFechaSiguienteDosis(fechaDosisAnterior, detalleReceta.getFrecuencia(),
                                    detalleReceta.getTipoFrecuencia()));
                }
                fechaDosisAnterior = nuevaDosis.getFechaHora();
                srvDosis.save(nuevaDosis);
                listaDosis.add(nuevaDosis);
            }
            detalleReceta.setDosis(listaDosis);
            srvDetalleReceta.save(detalleReceta);
        }
        String msg = "La receta ha sido creada.";
        message.addFlashAttribute("success", msg);
        return "redirect:/receta/list";
    }

    @GetMapping(value="/retrieve/{id}" )
    public String retrieve(@PathVariable(value = "id") Integer id,
                           Model model){
        Dosis dosis=service.findById(id);
        model.addAttribute("dosis",dosis);
        return "dosis/card";
    }

    @GetMapping(value="/update/{id}" )
    public String update(@PathVariable(value = "id") Integer id,
                         Model model){
        Dosis dosis=service.findById(id);
        model.addAttribute("dosis",dosis);
        return "dosis/form";
    }

    @GetMapping(value="/delete" )
    public String delete(@PathVariable(value = "id") Integer id,
                         Model model){
        try{
            service.delete(id);
        }catch (Exception ex){
            model.addAttribute("error: ",ex.toString());
        }
        return "redirect:/dosis/list";
    }

    @GetMapping(value="/list" )
    public String list(Model model){
        List<Dosis> dosiss=service.findAll();
        model.addAttribute("lista",dosiss);
        return "dosis/list";
    }
}
