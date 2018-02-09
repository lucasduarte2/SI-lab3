package com.spotify.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Musica implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idMusica;
	private String nomeMusica;
	private int duracaoMusica;
	private int anoLancamentoMusica;
	private String artista;
	
	@ManyToOne()
	@JoinColumn(name = "album_id")
	@JsonBackReference
	private Album album;
	
	@ManyToOne()
	@JoinColumn(name = "playList_id")
	@JsonBackReference
	private PlayList playList;
	
	private String emailUsuario;
	
	public Musica() {
		
	}

	public long getIdMusica() {
		return idMusica;
	}

	public void setIdMusica(long idMusica) {
		this.idMusica = idMusica;
	}

	public String getNomeMusica() {
		return nomeMusica;
	}

	public void setNomeMusica(String nomeMusica) {
		this.nomeMusica = nomeMusica;
	}

	public int getDuracaoMusica() {
		return duracaoMusica;
	}

	public void setDuracaoMusica(int duracaoMusica) {
		this.duracaoMusica = duracaoMusica;
	}

	public int getAnoLancamentoMusica() {
		return anoLancamentoMusica;
	}

	public void setAnoLancamentoMusica(int anoLancamentoMusica) {
		this.anoLancamentoMusica = anoLancamentoMusica;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

}
