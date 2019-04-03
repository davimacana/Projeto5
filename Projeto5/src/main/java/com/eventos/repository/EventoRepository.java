package com.eventos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventos.entity.Evento;

/**
 * @author Davi Maçana
 *
 */
@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {
}
