package com.eventos.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.eventos.entity.Usuario;
import com.eventos.model.UsuarioSecurityModel;

/**
 * @author Davi Maçana
 *
 */
@Component
public class SessionComponent {

	public Usuario getUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = ((UsuarioSecurityModel) authentication.getPrincipal()).getUsuario();
		return usuarioLogado;
	}
}
