package com.eventos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventos.entity.Evento;

/**
 * @author Davi Maçana
 *
 */
@RestController
@RequestMapping("/eventos")
public class EventosController {
	
	@GetMapping
	public ModelAndView eventos() {
		ModelAndView model = new ModelAndView("/eventos");
		model.addObject("evento", new Evento());
		return model;
	}
	
	@PostMapping
	public String salvarNovoProcesso(Evento evento,RedirectAttributes redirectAttrs) {
		System.out.println(evento);
		return "redirect:/dashboard";
	}
	
}
