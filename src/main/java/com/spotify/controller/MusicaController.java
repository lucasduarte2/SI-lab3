package com.spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spotify.models.Album;
import com.spotify.models.Musica;
import com.spotify.service.AlbumService;

@Controller
public class MusicaController {
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value = "/cadastrarMusica", method = RequestMethod.POST)
	public String formAddMusicaNoAlbum(Musica musica, Album album) {
		addMusicaNoAlbum(musica, album);
		return "redirect:/cadastrarMusica";
		
	}
	
	
	public ResponseEntity<Object> addMusicaNoAlbum(Musica musica, Album album) {
		Album album2 = albumService.addMusicaEAlbum(musica, album);
		if(album2 == null || album.getNomeAlbum().trim().isEmpty()){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(album2, HttpStatus.CREATED);
		
	}

}
