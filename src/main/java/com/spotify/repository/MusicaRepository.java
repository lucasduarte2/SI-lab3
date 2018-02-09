package com.spotify.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spotify.models.Musica;

public interface MusicaRepository extends CrudRepository<Musica, String>{

	Musica findByNomeMusica(String nomeMusica);

	List<Musica> findByEmailUsuario(String userName);

}
