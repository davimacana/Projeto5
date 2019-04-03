package com.eventos.service;

import java.util.ArrayList;
import java.util.List;
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

	public void salvarUsuario(UsuarioModel usuarioModel) {

		Usuario usuario = new Usuario();

		usuario.setAtivo(true);
		usuario.setLogin(usuarioModel.getLogin());
		usuario.setNome(usuarioModel.getNome());
		usuario.setSobrenome(usuarioModel.getNome());
		usuario.setEmail("davirj9@hotmail.com");
		usuario.setPerfil(usuarioModel.getPerfil());
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		this.usuarioRepository.save(usuario);
	}

	public List<UsuarioModel> consultarUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();
		List<Usuario> usuariosEntity = (List<Usuario>) this.usuarioRepository.findAll();
		usuariosEntity.forEach(usuario -> {

			usuariosModel.add(new UsuarioModel(usuario.getId(), usuario.getNome(), usuario.getLogin(), null,
					usuario.isAtivo(), null));
		});

		return usuariosModel;
	}

	public void excluir(Long codigoUsuario) {

		this.usuarioRepository.deleteById(codigoUsuario);
	}

	public UsuarioModel consultarUsuario(Long codigoUsuario) {

		Optional<Usuario> usuario = this.usuarioRepository.findById(codigoUsuario);

		return new UsuarioModel(usuario.get().getId(), usuario.get().getNome(), usuario.get().getLogin(), null,
				usuario.get().isAtivo(), usuario.get().getPerfil());

	}

	public void alterarUsuario(UsuarioModel usuarioModel) {

		Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioModel.getId());

		usuario.get().setAtivo(usuarioModel.isAtivo());
		usuario.get().setLogin(usuarioModel.getLogin());
		usuario.get().setNome(usuarioModel.getNome());
		if (!StringUtils.isEmpty(usuarioModel.getSenha()))
			usuario.get().setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		this.usuarioRepository.save(usuario.get());
	}
	
	public void alterarUsuario(Usuario usuario, Usuario usuarioLogado) {
		usuarioLogado.setNome(usuario.getNome());
		usuarioLogado.setSobrenome(usuario.getSobrenome());
		usuarioLogado.setSobre(usuario.getSobre());
		usuarioLogado.setEndereco(usuario.getEndereco());
		this.usuarioRepository.save(usuarioLogado);
	}

	public void cadastrarUsuario(Usuario usuario) {
		
	}
	
	public Optional<Usuario> findById(Long id) {
		return this.usuarioRepository.findById(id);
	}
	

}