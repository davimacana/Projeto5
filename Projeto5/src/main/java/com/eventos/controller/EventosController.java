package com.eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventos.entity.Evento;
import com.eventos.service.EventoService;
import com.eventos.service.TipoEventoService;

/**
 * @author Davi Maçana
 *
 */
@RestController
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private TipoEventoService tipoEventoService;
	
	@Autowired
	private EventoService eventoService;
	
	@GetMapping
	public ModelAndView eventos() {
		ModelAndView model = new ModelAndView("/eventos");
		model.addObject("evento", new Evento());
		model.addObject("tiposEventos", tipoEventoService.findAll());
		return model;
	}
	
	@PostMapping
	public ModelAndView salvar(Evento evento, RedirectAttributes redirectAttrs) {
		eventoService.save(evento);
		ModelAndView model = new ModelAndView("/eventos");
		model.addObject("tiposEventos", tipoEventoService.findAll());
		model.addObject("evento", evento);
		return model;
	}
	
}
