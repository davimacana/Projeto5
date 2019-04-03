package com.eventos.controller;

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
		usuarioService.cadastrarUsuario(usuario);
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
		usuarioService.cadastrarUsuario(usuario);
		return model;
	}

	
}
