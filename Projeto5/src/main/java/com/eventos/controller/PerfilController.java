package com.eventos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.eventos.entity.Usuario;
import com.eventos.model.UsuarioSecurityModel;

/**
 * @author Davi Maçana
 *
 */
@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	@GetMapping
	public ModelAndView perfil() {
		ModelAndView model = new ModelAndView("/perfil");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = ((UsuarioSecurityModel) authentication.getPrincipal()).getUsuario();
		model.addObject("usuario", usuario);
		return model;
	}
}
