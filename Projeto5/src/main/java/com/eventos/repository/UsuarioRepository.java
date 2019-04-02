package com.eventos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventos.entity.Usuario;

/**
 * @author Davi Maçana
 *
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findByLogin(String login);
}
