package com.eventos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Davi Maçana
 *
 */
@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	@GetMapping
    public ModelAndView menu() {
		ModelAndView model = new ModelAndView("/perfil");
        return model;
    }
}
