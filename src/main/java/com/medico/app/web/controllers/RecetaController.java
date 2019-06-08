package com.medico.app.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.medico.app.web.models.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medico.app.web.models.services.IMedicamentoService;
import com.medico.app.web.models.services.IMedicoService;
import com.medico.app.web.models.services.IPacienteService;
import com.medico.app.web.models.services.IRecetaService;

@Controller
@RequestMapping(value="/receta")
@SessionAttributes({"receta","title"})
public class RecetaController {

	//Servicio => Inyección de dependencias
	@Autowired
	private IRecetaService service;

	@Autowired
	private IMedicoService srvMedico;

	@Autowired
	private IPacienteService srvPaciente;

	@Autowired
	private IMedicamentoService srvMedicamento;
		
	@GetMapping(value="/create")
	public String create(Model model) {
		Receta receta = new Receta();
		model.addAttribute("title","Registro de nueva receta");
		model.addAttribute("receta",receta);
		
		List<Paciente> pacientes = srvPaciente.findAll();
		List<Medico> medicos = srvMedico.findAll();
		
		model.addAttribute("pacientes",pacientes);
		model.addAttribute("medicos",medicos);
		
		return "receta/form";
	}
	
	@PostMapping(value="/save")
	public String save(@Valid Receta receta, BindingResult result,
					   Model model, RedirectAttributes message,
					   SessionStatus session) {
		try {
			if(result.hasErrors()) {        		
        		return "receta/form";
        	}
			if (receta.getIdreceta() != null ){
				String msg = "La receta ha sido actualizada.";
				message.addFlashAttribute("success", msg);
				service.save(receta);
				session.setComplete();
			}else{ // Si creo una receta creo sus dosis
				service.save(receta);
				session.setComplete();
				message.addFlashAttribute("receta_guardada", receta);
				return "redirect:/dosis/generatedosis";
			}
		}
		catch(Exception ex){
			message.addFlashAttribute("error", ex.toString());
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
	
	@PostMapping(value="/addDetail", produces="application/json")
	public @ResponseBody List<DetalleReceta> addDetalleReceta(
			@RequestBody DetalleReceta detail, @SessionAttribute(name="receta") Receta receta) {
		if(receta.getDetalles() == null)
		{
			List<DetalleReceta> detalles = new ArrayList<>();
			receta.setDetalles(detalles);
		}
		System.out.println(detail.getFechaInicio());
		System.out.println(detail.getNumeroTomas());
		Medicamento medicamento = srvMedicamento.findById(detail.getMedicamentoId());
		detail.setMedicamento(medicamento);
		receta.getDetalles().add(detail);
    	return receta.getDetalles();
    }
	
	
	
	


}
