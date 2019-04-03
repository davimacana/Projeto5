package com.eventos.util;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventos.entity.Endereco;
import com.eventos.entity.Evento;
import com.eventos.entity.Perfil;
import com.eventos.entity.TipoEvento;
import com.eventos.model.UsuarioModel;
import com.eventos.repository.TipoEventoRepository;
import com.eventos.service.EventoService;
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
	
	@Autowired
	private EventoService eventoService;
 
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
    	
    	Endereco endereco = new Endereco();
    	endereco.setUf("RJ");
    	endereco.setCidade("Rio de Janeiro");
    	endereco.setBairro("Bento Ribeiro");
    	endereco.setRua("Sapopemba");
    	endereco.setNumero("553");
    	endereco.setCep("21331240");
    	
    	Evento evento = new Evento();
    	evento.setDataEvento(new Date());
    	evento.setDescricaoEvento("Corrida de rua com equipes de desenvolvimento");
    	evento.setNome("Corrida de rua");
    	evento.setEmailResponsavel("davirj9@hotmail.com");
    	evento.setTipoEvento(tipoEvento1);
    	evento.setEndereco(endereco);
    	eventoService.save(evento);
    	
    }

}