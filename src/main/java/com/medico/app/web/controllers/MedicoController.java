package com.medico.app.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medico.app.web.models.entities.Medico;
import com.medico.app.web.models.entities.Receta;
import com.medico.app.web.models.services.IMedicoService;

@Controller
@RequestMapping(value="/medico")
public class MedicoController {
  
	@Autowired
	private IMedicoService service;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Medico medico = new Medico();
		model.addAttribute("medico",medico);
		return "medico/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id")Integer id, 
			Model model) {
		Medico medico = service.findById(id);
		model.addAttribute("medico",medico);
		return "medico/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id")Integer id,
			Model model) {
		Medico medico = service.findById(id);
		model.addAttribute("medico",medico);
		return "medico/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id")Integer id, 
			Model model) {
		try {
			service.delete(id);
		}
		catch(Exception ex){
			model.addAttribute("error", ex.toString());
		}
		return "redirect:/medico/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Medico> medicos = service.findAll();
		model.addAttribute("medicos", medicos);
		return "medico/list";		
	}
	
	@PostMapping(value="/save")
	public String save(Medico medico, Model model) {
		try {
			service.save(medico);
		}
		catch(Exception ex) {
			model.addAttribute("error", ex.toString());
		}	
		return "redirect:/medico/list";
	}
}












