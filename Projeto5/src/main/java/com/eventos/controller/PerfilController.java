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

import com.eventos.entity.Usuario;
import com.eventos.service.UsuarioService;
import com.eventos.util.SessionComponent;

/**
 * @author Davi Maçana
 *
 */
@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	private static final Logger logger = Logger.getLogger(PerfilController.class.getCanonicalName());
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SessionComponent sessionComponent;
	
	@GetMapping
	public ModelAndView perfil() {
		ModelAndView model = new ModelAndView("/perfil");
		Usuario usuarioLogado = sessionComponent.getUsuarioLogado();
		model.addObject("usuario", usuarioService.findById(usuarioLogado.getId()).get());
		return model;
	}
	
	@PostMapping
	public ModelAndView salvar(Usuario usuario, RedirectAttributes redirectAttrs) {
		ModelAndView model = new ModelAndView("/perfil");
		Usuario usuarioLogado = sessionComponent.getUsuarioLogado();
		try {
			usuarioService.alterarUsuario(usuario, usuarioLogado);
			model.addObject("usuario", usuarioLogado);
			redirectAttrs.addFlashAttribute("mensagemSucessoEditar", "Parabéns ! Perfil alterado com sucesso.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			model.addObject("usuario", usuarioLogado);
			redirectAttrs.addFlashAttribute("mensagem", "Ocorreu um erro inesperado. Favor, entrar em contato com o administrador do sistema.");
		}
		
		return model;
	}
}
