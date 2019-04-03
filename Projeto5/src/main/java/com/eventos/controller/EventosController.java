package com.eventos.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private static final Logger logger = Logger.getLogger(EventosController.class.getCanonicalName());
	
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
	
	@GetMapping("/listar")
	public ModelAndView listarEventos() {
		ModelAndView model = new ModelAndView("/listarEventos");
		model.addObject("eventos", eventoService.findAll());
		return model;
	}
	
	@PostMapping
	public ModelAndView salvar(Evento evento, RedirectAttributes redirectAttrs) {
		ModelAndView model = new ModelAndView("/eventos");
		try {
			eventoService.save(evento);
			model.addObject("tiposEventos", tipoEventoService.findAll());
			model.addObject("evento", evento);
			model.addObject("mensagemSucesso", "Parabéns ! Evento incluído com sucesso.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			model.addObject("evento", evento);
			model.addObject("mensagemErro", "Ocorreu um erro inesperado. Favor, entrar em contato com o administrador do sistema.");
		}
		return model;
	}
	
}
