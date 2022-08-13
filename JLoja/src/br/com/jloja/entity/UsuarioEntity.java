package br.com.jloja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "usuario")
@NamedQueries({@NamedQuery(name = "UsuarioEntity.buscarPorCodigo",
query = "SELECT usu FROM UsuarioEntity usu Where usu.usuario = :codigo"),
	
@NamedQuery(name = "UsuarioEntity.listar",
query = "SELECT usu FROM UsuarioEntity usu"),

@NamedQuery(name = "UsuarioEntity.login",
query = "SELECT usu FROM UsuarioEntity usu" + "WHERE usu.login = :login AND usu.senha = :senha")})

public class UsuarioEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private Long idusuario;
    
    @Column(length = 60)
	private String nome;
	
    @Column(length = 20)
	private String login;
    
    @Column(length = 50)
	private String senha;
    
    @Column(length = 1)
	private char situacao;

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
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

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(char situacao) {
		this.situacao = situacao;
	}
    
    
}
