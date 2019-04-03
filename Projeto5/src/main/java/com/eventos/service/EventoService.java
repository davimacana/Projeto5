package com.eventos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventos.entity.Evento;
import com.eventos.repository.EventoRepository;

/**
 * @author Davi Maçana
 *
 */
@Component
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	public void save(Evento evento) {
		this.eventoRepository.save(evento);
	}
	
	public Iterable<Evento> findAll() {
		return this.eventoRepository.findAll();
	}
	
	public Optional<Evento> findById(Long id) {
		return this.eventoRepository.findById(id);
	}

}
