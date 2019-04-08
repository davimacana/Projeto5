package com.eventos.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventos.entity.Usuario;
import com.eventos.service.UsuarioService;

/**
 * @author Davi Maçana
 *
 */
@RestController
public class CadastroController {
	
	private static final Logger logger = Logger.getLogger(PerfilController.class.getCanonicalName());
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView model = new ModelAndView("/cadastrar");
		model.addObject("usuario", new Usuario());
		return model;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(Usuario usuario, RedirectAttributes redirectAttrs) {
		ModelAndView model = new ModelAndView("/cadastrar");
		try{
			usuarioService.cadastrarUsuario(usuario);
			model.addObject("mensagemSucesso", "Parabéns ! Usuário inserido com sucesso.");
		} catch(Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			model.addObject("mensagemErro", "Ocorreu um erro inesperado. Favor, entrar em contato com o administrador do sistema.");
		}
		return model;
	}
	
	@GetMapping("/recuperaSenha")
	public ModelAndView recuperaSenha() {
		ModelAndView model = new ModelAndView("/recuperaSenha");
		model.addObject("usuario", new Usuario());
		return model;
	}
	
	@PostMapping("/recuperaSenha")
	public ModelAndView recuperaSenha(Usuario usuario, RedirectAttributes redirectAttrs) {
		ModelAndView model = new ModelAndView("/recuperaSenha");
		try{
			usuarioService.recuperarSenha(usuario);
			model.addObject("mensagemSucesso", "Você receberá um email contendo sua nova senha.");
		} catch(Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			model.addObject("mensagemErro", "Ocorreu um erro inesperado. Favor, entrar em contato com o administrador do sistema.");
		}
		
		return model;
	}

	
}
