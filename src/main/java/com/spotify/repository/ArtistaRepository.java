package com.spotify.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spotify.models.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, String> {

	Artista findById(long id);
	List<Artista> findAllByNomeArtistaIgnoreCaseContaining(String nomeArtista);
	List<Artista> findAllByEmailUsuario(String emailArtista);
	Artista findByNomeArtista(String nomeArtista);

}