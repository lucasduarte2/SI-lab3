package com.spotify.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class PlayList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPlayList;
	private String nomePlayList;
	
	@OneToMany(mappedBy = "playList", targetEntity = Musica.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Musica> musicas;
	private String emailUsuario;
	
	public long getIdPlayList() {
		return idPlayList;
	}
	public void setIdPlayList(long idPlayList) {
		this.idPlayList = idPlayList;
	}
	public String getNomePlayList() {
		return nomePlayList;
	}
	public void setNomePlayList(String nomePlayList) {
		this.nomePlayList = nomePlayList;
	}
	public List<Musica> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
}
