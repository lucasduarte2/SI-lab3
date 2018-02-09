package com.spotify.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Artista implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nomeArtista;
	private String linkDaImagem;
	private String ultimaMusicaEscutada;
	private Integer classificacaoArtista;
	private String favorito;
	private String emailUsuario;
	@OneToMany()
	@JsonManagedReference
	private List<Album> albuns;
	
	public Artista(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeArtista() {
		return nomeArtista;
	}

	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}

	public String getLinkDaImagem() {
		return linkDaImagem;
	}

	public void setLinkDaImagem(String linkDaImagem) {
		this.linkDaImagem = linkDaImagem;
	}

	public String getUltimaMusicaEscutada() {
		return ultimaMusicaEscutada;
	}

	public void setUltimaMusicaEscutada(String ultimaMusicaEscutada) {
		this.ultimaMusicaEscutada = ultimaMusicaEscutada;
	}

	public Integer getClassificacaoArtista() {
		return classificacaoArtista;
	}

	public void setClassificacaoArtista(Integer classificacaoArtista) {
		this.classificacaoArtista = classificacaoArtista;
	}

	public String getFavorito() {
		return favorito;
	}

	public void setFavorito(String favorito) {
		this.favorito = favorito;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

}
