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

	public Musica procurarMusica(Musica musica, String userName){
		List<Musica> musicas = musicaRepository.findByEmailUsuario(userName);
		Musica musicaAchada = null;
		for (Musica musica2 : musicas) {
			if(musica2.getNomeMusica().equals(musica.getNomeMusica())){
				musicaAchada = musica2;
			}
		}
		return musicaAchada;
		
	}

	public Musica procurarMusica2(String musica, String userName) {
		List<Musica> musicas = musicaRepository.findByEmailUsuario(userName);
		Musica musicaAchada = null;
		for (Musica musica2 : musicas) {
			if(musica2.getNomeMusica().equals(musica)){
				musicaAchada = musica2;
			}
		}
		return musicaAchada;
	}
	
}
