package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.Paciente;
import com.medico.app.web.models.entities.Receta;
import com.medico.app.web.models.services.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/paciente")
public class PacienteController {

    @Autowired
    //private IPacienteDAO service;
    private IPacienteService service;

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value="/create" )
    public String create(Model model){
        Paciente paciente=new Paciente();
        model.addAttribute("title","Registro de nuevo paciente");
        model.addAttribute("paciente",paciente);
        return "paciente/form";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping(value="/save" )
    public String save(Paciente paciente, BindingResult result,
                       Model model,
                       RedirectAttributes message, SessionStatus session){
        try{
            if(result.hasErrors()) {
                return "paciente/form";
            }
            String msg = paciente.getIdpersona() == null ? paciente.getNombre() + " ha sido agregado." : paciente.getNombre() + " ha sido actualizado.";            
            service.save(paciente);
            session.setComplete();
            message.addFlashAttribute("success", msg);
        }catch (Exception ex){
            model.addAttribute("error: ",ex.toString());
        }
        return "redirect:/paciente/list";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping(value="/retrieve/{id}" )
    public String retrieve(@PathVariable(value = "id") Integer id,
                           Model model){
        Paciente paciente=service.findById(id);
        List<Receta> recetas = paciente.getRecetas();
        model.addAttribute("recetas",recetas);
        model.addAttribute("paciente",paciente);
        model.addAttribute("title","Actualización de paciente: " + paciente.getNombre());
        return "paciente/card";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value="/update/{id}" )
    public String update(@PathVariable(value = "id") Integer id,
                         Model model){
        Paciente paciente=service.findById(id);
        model.addAttribute("paciente",paciente);
        return "paciente/form";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value="/delete/{id}" )
    public String delete(@PathVariable(value = "id") Integer id,
                         Model model, RedirectAttributes message){
        try{
            service.delete(id);
            message.addFlashAttribute("success", "Paciente eliminado correctamente");
        }catch (Exception ex){
            model.addAttribute("error: ",ex.toString());
        }
        return "redirect:/paciente/list";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(value="/list" )
    public String list(Model model){
        List<Paciente> pacientes=service.findAll();
        model.addAttribute("lista",pacientes);
        model.addAttribute("title","Listado de pacientes");
        return "paciente/list";
    }
}