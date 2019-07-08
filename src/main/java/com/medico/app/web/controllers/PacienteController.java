package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.*;
import com.medico.app.web.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Validator;
import java.util.List;

@Controller
@RequestMapping(value="/paciente")
@SessionAttributes({"paciente","title"})
public class PacienteController {

    @Autowired
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
                model.addAttribute("paciente",paciente);
                return "paciente/form";
            }
            System.out.println("*1****************hubo error");
            String msg = paciente.getIdpersona() == null ? paciente.getNombre() + " ha sido agregado." : paciente.getNombre() + " ha sido actualizado.";
            service.save(paciente);
            System.out.println("*222****************hubo error");
            session.setComplete();
            message.addFlashAttribute("success", msg);
            System.out.println("*333****************hubo error");
            
        }catch (Exception ex){
            message.addFlashAttribute("error", "Algo no ha salido Bien");
            System.out.println(ex.toString());
            model.addAttribute("error", "Algo no ha salido Bien");
            model.addAttribute("paciente",paciente);
            return "paciente/form";
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
        model.addAttribute("title","Datos de Paciente: " + paciente.getNombre());
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