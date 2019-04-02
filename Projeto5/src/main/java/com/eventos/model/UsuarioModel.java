package com.eventos.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.eventos.entity.Perfil;

/**
 * @author Davi Maçana
 *
 */
public class UsuarioModel {
	
	private Long id;

	@NotEmpty(message ="O Nome é de preenchimento obrigatório.")
	private String nome;

	@NotEmpty(message ="O Login é de preenchimento obrigatório.")
	private String login;

	@NotEmpty(message ="A Senha é de preenchimento obrigatório.")
	private String senha;
	
	@NotEmpty(message ="O perfil é de preenchimento obrigatório.")
	private Perfil perfil;

	private boolean ativo;

	public UsuarioModel(){

		System.out.println("Passei " + LocalDate.now());
	}

	public UsuarioModel(Long id, String nome, String login, String senha, boolean ativo, Perfil perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.perfil = perfil;
	}	

	public Long getId() {		
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
