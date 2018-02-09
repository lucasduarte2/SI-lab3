package com.spotify.repository;

import org.springframework.data.repository.CrudRepository;

import com.spotify.models.Album;

public interface AlbumRepository extends CrudRepository<Album, String> {

	Album findAllByNomeAlbum(String nomeAlbum);

}
