package com.eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/cadastrar")
public class CadastroController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView cadastrese() {
		ModelAndView model = new ModelAndView("/cadastrar");
		model.addObject("usuario", new Usuario());
		return model;
	}

	@PostMapping
	public ModelAndView cadastrar(Usuario usuario, RedirectAttributes redirectAttrs) {
		ModelAndView model = new ModelAndView("/cadastrar");
		usuarioService.cadastrarUsuario(usuario);
		return model;
	}
}
