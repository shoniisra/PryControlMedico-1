package com.medico.app.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medico.app.web.models.dao.IRecetaDAO;
import com.medico.app.web.models.entities.Receta;
import com.medico.app.web.models.services.IRecetaService;

@Controller
@RequestMapping(value="/receta")
public class RecetaController {

	//Servicio => Inyección de dependencias
	@Autowired
	private IRecetaService service;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Receta receta = new Receta();
		model.addAttribute("receta",receta);
		return "receta/form";
	}
	
	@PostMapping(value="/save")
	public String save(Receta receta, Model model) {
		try {
			service.save(receta);
		}
		catch(Exception ex){			
		}
		return "redirect:/receta/list";
	}
	
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id")Integer id, 
			Model model) {
		Receta receta = service.findById(id);
		model.addAttribute("receta",receta);
		return "receta/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id")Integer id,
			Model model) {
		Receta receta = service.findById(id);
		model.addAttribute("receta",receta);
		return "receta/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id")Integer id, 
			Model model) {
		try {
			service.delete(id);
		}
		catch(Exception ex){			
		}				
		return "redirect:/receta/list";
	}	
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Receta> recetas = service.findAll();
		model.addAttribute("recetas", recetas);
		return "receta/list";		
	}
	
	
	
	
	


}
