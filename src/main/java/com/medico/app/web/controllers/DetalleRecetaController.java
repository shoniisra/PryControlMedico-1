package com.medico.app.web.controllers;

import com.medico.app.web.models.entities.DetalleReceta;
import com.medico.app.web.models.services.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/detallereceta")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService service;

    @GetMapping(value = "/create")
    public String create(Model model){
        DetalleReceta detalleReceta = new DetalleReceta();
        model.addAttribute("detalleReceta", detalleReceta);
        return "detallereceta/form";
    }

    @PostMapping(value = "/save")
    public String save(DetalleReceta detalleReceta, Model model){
        try {
            service.save(detalleReceta);
        }catch (Exception ex){
            model.addAttribute("error: ", ex.toString());
        }
        return "redirect:/detallereceta/list";
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrieve(@PathVariable(value = "id") Integer id, Model model){
        DetalleReceta detalleReceta = service.findById(id);
        model.addAttribute("detallereceta", detalleReceta);
        return "detallereceta/card";
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        DetalleReceta detalleReceta = new DetalleReceta();
        model.addAttribute("detallereceta", detalleReceta);
        return "detallereceta/form";
    }

    @GetMapping(value = "/delete")
    public String delete(@PathVariable(value = "id") Integer id, Model model){
        try {
            service.delete(id);
        }catch (Exception ex){
            model.addAttribute("error:", ex.toString());
        }
        return "redirect:/detallereceta/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<DetalleReceta> detalles = service.findAll();
        model.addAttribute("lista",detalles);
        return "detallereceta/list";
    }
    
}
