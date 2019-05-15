package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.Medico;
import com.medico.app.web.models.entities.TipoSangre;
import com.medico.app.web.models.services.IMedicoService;
import com.medico.app.web.models.services.ITipoSangreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping(value="/medico")
public class MedicoController {

	@Autowired
	//private IMedicoDAO service;
	private IMedicoService service;


	@GetMapping(value="/create" )
	public String create(Model model){
		Medico medico=new Medico();
		model.addAttribute("title","Registro de nuevo medico");
		model.addAttribute("medico",medico);
		return "medico/form";
	}

	@PostMapping(value="/save" )
	public String save(Medico medico, BindingResult result,
					   Model model,
					   RedirectAttributes message, SessionStatus session){
		try{
			if(result.hasErrors()) {
				return "medico/form";
			}
			String msg = medico.getIdpersona() == null ? medico.getNombre() + " ha sido agregado." : medico.getNombre() + " ha sido actualizado.";

			service.save(medico);
			session.setComplete();
			message.addFlashAttribute("success", msg);
		}catch (Exception ex){
			model.addAttribute("error: ",ex.toString());
		}
		return "redirect:/medico/list";
	}

	@GetMapping(value="/retrieve/{id}" )
	public String retrieve(@PathVariable(value = "id") Integer id,
						   Model model){
		Medico medico=service.findById(id);
		model.addAttribute("medico",medico);
		return "medico/card";
	}

	@GetMapping(value="/update/{id}" )
	public String update(@PathVariable(value = "id") Integer id,
						 Model model){
		Medico medico=service.findById(id);
		model.addAttribute("medico",medico);
		model.addAttribute("title","Actualización de medico: " + medico.getNombre());
		return "medico/form";
	}

	@GetMapping(value="/delete/{id}" )
	public String delete(@PathVariable(value = "id") Integer id,
						 Model model, RedirectAttributes message){
		try{
			service.delete(id);
			message.addFlashAttribute("success", "Medico eliminado correctamente");

		}catch (Exception ex){
			model.addAttribute("error: ",ex.toString());
		}
		return "redirect:/medico/list";
	}

	@GetMapping(value="/list" )
	public String list(Model model){
		List<Medico> medicos=service.findAll();
		model.addAttribute("lista",medicos);
		model.addAttribute("title","Listado de medicos");
		return "medico/list";
	}
}