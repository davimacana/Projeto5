package com.eventos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Davi Maçana
 *
 */
@Table(name="usuario")
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  	
	@Column(name="id_usuario")
	private Long id;
	private String	nome;
	private String login;
	private String senha;
	private boolean ativo;
	@Enumerated(EnumType.STRING)
	private Perfil perfil;	
	
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
