package com.eventos.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventos.entity.Perfil;
import com.eventos.entity.TipoEvento;
import com.eventos.model.UsuarioModel;
import com.eventos.repository.TipoEventoRepository;
import com.eventos.service.UsuarioService;

/**
 * @author Davi Maçana
 *
 */
@Component
public class PostConstructInit {
	
	@Autowired
    private UsuarioService usuarioService;
	
	@Autowired
    private TipoEventoRepository tipoEventoRepository;
 
    @PostConstruct
    public void init() {
    	UsuarioModel usuario = new UsuarioModel(); 
    	usuario.setAtivo(true);
    	usuario.setLogin("admin");
    	usuario.setNome("Davi");
    	usuario.setPerfil(Perfil.ADMIN);
    	usuario.setSenha("123");
    	usuarioService.salvarUsuario(usuario);
    	
    	TipoEvento tipoEvento1 = new TipoEvento();
    	tipoEvento1.setDescricaoTipo("Corrida");
    	tipoEventoRepository.save(tipoEvento1);
    	
    	TipoEvento tipoEvento2 = new TipoEvento();
    	tipoEvento2.setDescricaoTipo("Natação");
    	tipoEventoRepository.save(tipoEvento2);
    }

}
