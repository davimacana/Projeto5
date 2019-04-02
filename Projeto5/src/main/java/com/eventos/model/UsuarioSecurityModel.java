package com.eventos.model;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.eventos.entity.Usuario;

/**
 * @author Davi Maçana
 *
 */
public class UsuarioSecurityModel extends User {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public UsuarioSecurityModel(Usuario usuario) {
		super(usuario.getLogin(), usuario.getSenha(), usuario.isAtivo(), true, true, true, AuthorityUtils.createAuthorityList(usuario.getPerfil().toString()));
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
