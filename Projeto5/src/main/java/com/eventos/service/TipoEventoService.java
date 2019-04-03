package com.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventos.entity.TipoEvento;
import com.eventos.repository.TipoEventoRepository;

/**
 * @author Davi Maçana
 *
 */
@Component
public class TipoEventoService {
	
	@Autowired
	private TipoEventoRepository tipoEventoRepository;
	
	public void save(TipoEvento tipo) {
		this.tipoEventoRepository.save(tipo);
	}
	
	public Iterable<TipoEvento> findAll() {
		return this.tipoEventoRepository.findAll();
	}

}
