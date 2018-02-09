package com.spotify.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spotify.models.PlayList;

public interface PlayListRepository extends CrudRepository<PlayList, String>{
	List<PlayList> findAllByEmailUsuario(String emailArtista);
	List<PlayList> findAllByNomePlayList(String nomePlayList);
	PlayList findByNomePlayList(String nomePlayList);
	PlayList findByIdPlayList(long idPlayList);

}
