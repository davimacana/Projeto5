package com.eventos.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventos.entity.Perfil;
import com.eventos.model.UsuarioModel;
import com.eventos.service.UsuarioService;

/**
 * @author Davi Maçana
 *
 */
@Component
public class PostConstructInit {
	
	@Autowired
    private UsuarioService usuarioService;
 
    @PostConstruct
    public void init() {
    	UsuarioModel usuario = new UsuarioModel(); 
    	usuario.setAtivo(true);
    	usuario.setId(1L);
    	usuario.setLogin("admin");
    	usuario.setNome("Davi");
    	usuario.setPerfil(Perfil.ADMIN);
    	usuario.setSenha("123");
    	usuarioService.salvarUsuario(usuario);
    }

}
