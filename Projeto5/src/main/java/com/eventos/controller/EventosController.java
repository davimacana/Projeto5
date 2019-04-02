package com.eventos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Davi Ma�ana
 *
 */
@RestController
@RequestMapping("/eventos")
public class EventosController {
	
	@GetMapping
    public ModelAndView menu() {
		ModelAndView model = new ModelAndView("/eventos");
        return model;
    }
}
