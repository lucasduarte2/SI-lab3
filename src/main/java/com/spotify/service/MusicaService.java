package com.spotify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotify.models.Musica;
import com.spotify.repository.MusicaRepository;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	public Musica addMusica(Musica novaMusica, String username){
		List<Musica> musicas = musicaRepository.findByEmailUsuario(username);
		if(musicas.contains(novaMusica)){
			return musicaRepository.save(novaMusica);
		}
		return null;
		
	}

}
