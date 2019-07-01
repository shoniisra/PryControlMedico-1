package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.Medicamento;
import com.medico.app.web.models.entities.ViaAdministracion;
import com.medico.app.web.models.services.IMedicamentoService;
import com.medico.app.web.models.services.IViaAdministracionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/medicamento")
@SessionAttributes({"medicamento","title"})
public class MedicamentoController {

    @Autowired
    private IMedicamentoService service;
    
    @Autowired
    private IViaAdministracionService srvViaAdministacion;

    @GetMapping(value="/create" )
    public String create(Model model){        
    	List<ViaAdministracion> vias = srvViaAdministacion.findAll();
    	Medicamento medicamento=new Medicamento();
        model.addAttribute("title","Registro de nuevo medicamento");
        model.addAttribute("medicamento",medicamento);
        model.addAttribute("vias",vias);        
        return "medicamento/form";
    }

    @PostMapping(value="/save" )
    public String save(@Valid Medicamento medicamento, BindingResult result,
    		Model model,    		 
    		RedirectAttributes message, SessionStatus session){
        try{        	
        	if(result.hasErrors()) {        		
        		return "medicamento/form";
        	}                  
        	String msg = medicamento.getIdmedicamento() == null ? medicamento.getNombreComercial() + " ha sido agregado a la lista de medicamentos." : medicamento.getNombreComercial() + " ha sido actualizado.";
        	service.save(medicamento);
            session.setComplete();            
            message.addFlashAttribute("success", msg);
        }catch (Exception ex){
        	message.addFlashAttribute("error",ex.toString());
        }
        return "redirect:/medicamento/list";
    }

    @GetMapping(value="/retrieve/{id}" )
    public String retrieve(@PathVariable(value = "id") Integer id,
                           Model model){
        Medicamento medicamento=service.findById(id);
        model.addAttribute("medicamento",medicamento);
        return "medicamento/card";
    }

    @GetMapping(value="/update/{id}" )
    public String update(@PathVariable(value = "id") Integer id,
                         Model model){
        Medicamento medicamento=service.findById(id);
        model.addAttribute("medicamento",medicamento);
        model.addAttribute("title","Actualización de medicamento: " + medicamento.getNombreComercial());
        List<ViaAdministracion> vias = srvViaAdministacion.findAll();
        model.addAttribute("vias",vias);        
        return "medicamento/form";
    }

    @GetMapping(value="/delete/{id}" )
    public String delete(@PathVariable(value = "id") Integer id,
                         Model model, RedirectAttributes message){
        try{
            service.delete(id);
            message.addFlashAttribute("success", "Medicamento eliminado correctamente");
        }catch (Exception ex){
            model.addAttribute("error: ",ex.toString());
        }

        return "redirect:/medicamento/list";
    }

    @GetMapping(value="/list" )
    public String list(Model model){
        List<Medicamento> medicamentos=service.findByAll();
        model.addAttribute("lista",medicamentos);
        model.addAttribute("title","Listado de medicamentos");
        return "medicamento/list";
    }
    
    @GetMapping(value = "/find/{criteria}", produces = { "application/json" })
	public @ResponseBody List<Medicamento> findByNombre(@PathVariable String criteria) {
		List<Medicamento> medicamentosNC = service.findByNombre(criteria);
		List<Medicamento> medicamentosCA = service.findByComponenteActivoLike(criteria);
		List<Medicamento> result = new ArrayList<>();
		result.addAll(medicamentosCA);
		result.addAll(medicamentosNC);
		return result;
	}
    
}
