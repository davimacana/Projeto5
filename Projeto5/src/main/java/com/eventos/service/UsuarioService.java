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

	/***
	 * CONSULTA UM USUÁRIO POR LOGIN
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException, DisabledException {

		Usuario usuario = usuarioRepository.findByLogin(login);

		if (usuario == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");

		if (!usuario.isAtivo())
			throw new DisabledException("Usuário não está ativo no sistema!");

		return new UsuarioSecurityModel(usuario);
	}

	/***
	 * SALVA UM NOVO REGISTRO DE USUÁRIO
	 * 
	 * @param usuarioModel
	 */
	public void salvarUsuario(UsuarioModel usuarioModel) {

		Usuario usuario = new Usuario();

		usuario.setAtivo(true);
		usuario.setLogin(usuarioModel.getLogin());
		usuario.setNome(usuarioModel.getNome());
		usuario.setPerfil(usuarioModel.getPerfil());
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		this.usuarioRepository.save(usuario);
	}

	/***
	 * CONSULTA OS USUÁRIOS CADASTRADOS
	 * 
	 * @return
	 */
	public List<UsuarioModel> consultarUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();
		List<Usuario> usuariosEntity = (List<Usuario>) this.usuarioRepository.findAll();
		usuariosEntity.forEach(usuario -> {

			usuariosModel.add(new UsuarioModel(usuario.getId(), usuario.getNome(), usuario.getLogin(), null,
					usuario.isAtivo(), null));
		});

		return usuariosModel;
	}

	/**
	 * DELETA UM USUÁRIO PELO CÓDIGO
	 */
	public void excluir(Long codigoUsuario) {

		this.usuarioRepository.deleteById(codigoUsuario);
	}

	/***
	 * CONSULTA UM USUÁRIO PELO SEU CÓDIGO
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public UsuarioModel consultarUsuario(Long codigoUsuario) {

		Optional<Usuario> usuario = this.usuarioRepository.findById(codigoUsuario);

		return new UsuarioModel(usuario.get().getId(), usuario.get().getNome(), usuario.get().getLogin(), null,
				usuario.get().isAtivo(), usuario.get().getPerfil());

	}

	/**
	 * ALTERA AS INFORMAÇÕES DO USUÁRIO
	 */
	public void alterarUsuario(UsuarioModel usuarioModel) {

		Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioModel.getId());

		usuario.get().setAtivo(usuarioModel.isAtivo());
		usuario.get().setLogin(usuarioModel.getLogin());
		usuario.get().setNome(usuarioModel.getNome());
		if (!StringUtils.isEmpty(usuarioModel.getSenha()))
			usuario.get().setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		this.usuarioRepository.save(usuario.get());
	}

}