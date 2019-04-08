package com.eventos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.eventos.entity.Usuario;
import com.eventos.model.UsuarioModel;
import com.eventos.model.UsuarioSecurityModel;
import com.eventos.repository.UsuarioRepository;

/**
 * @author Davi Maçana
 *
 */
@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException, DisabledException {

		Usuario usuario = usuarioRepository.findByLogin(login);

		if (usuario == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");

		if (!usuario.isAtivo())
			throw new DisabledException("Usuário não está ativo no sistema!");

		return new UsuarioSecurityModel(usuario);
	}

	public void excluir(Long codigoUsuario) {
		this.usuarioRepository.deleteById(codigoUsuario);
	}

	public UsuarioModel consultarUsuario(Long codigoUsuario) {

		Optional<Usuario> usuario = this.usuarioRepository.findById(codigoUsuario);

		return new UsuarioModel(usuario.get().getId(), usuario.get().getNome(), usuario.get().getLogin(), null,
				usuario.get().isAtivo(), usuario.get().getPerfil());
	}
	
	public void alterarUsuario(Usuario usuario, Usuario usuarioLogado) {
		usuarioLogado.setNome(usuario.getNome());
		usuarioLogado.setSobrenome(usuario.getSobrenome());
		usuarioLogado.setSobre(usuario.getSobre());
		usuarioLogado.setEndereco(usuario.getEndereco());
		this.usuarioRepository.save(usuarioLogado);
	}

	public void cadastrarUsuario(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> findById(Long id) {
		return this.usuarioRepository.findById(id);
	}

	public void recuperarSenha(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
	

}