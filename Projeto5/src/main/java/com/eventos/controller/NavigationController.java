package com.eventos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Davi Ma�ana
 *
 */
@RestController
public class NavigationController {
	
	@GetMapping
    public ModelAndView menu() {
		ModelAndView model = new ModelAndView("/dashboard");
        return model;
    }
}
