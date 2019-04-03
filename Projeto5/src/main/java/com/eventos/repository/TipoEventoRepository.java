package com.eventos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventos.entity.TipoEvento;

/**
 * @author Davi Maçana
 *
 */
@Repository
public interface TipoEventoRepository extends CrudRepository<TipoEvento, Long> {
}
