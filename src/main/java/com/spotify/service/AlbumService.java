package com.spotify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spotify.models.Album;
import com.spotify.models.Musica;
import com.spotify.repository.AlbumRepository;
import com.spotify.repository.MusicaRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private ArtistaService artistaService;

	private String retornaIdUsuario() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (usuarioLogado instanceof UserDetails) {
			username = ((UserDetails) usuarioLogado).getUsername();
		} else {
			username = usuarioLogado.toString();
		}
		return username;
	}

	public Album addMusicaEAlbum(Musica musica, Album album) {
		Album albumExistente = albumRepository.findAllByNomeAlbum(album.getNomeAlbum());
		if (albumExistente != null && albumExistente.getEmailUsuario().equals(retornaIdUsuario())) {
			Musica novaMusica = addMusica(musica, albumExistente);
			addMusicaAoAlbum(novaMusica, album);
			return albumExistente;
		}

		Album novoAlbum = addNovoAlbum(album);
		Musica novaMusica = addMusica(musica, novoAlbum);
		artistaService.addAlbumAoArtista(novoAlbum, novaMusica.getArtista());
		return novoAlbum;
	}

	private Album addNovoAlbum(Album album) {
		album.setEmailUsuario(retornaIdUsuario());
		return albumRepository.save(album);
	}

	private Album addMusicaAoAlbum(Musica musica, Album album) {
		Album albumExistente = albumRepository.findAllByNomeAlbum(album.getNomeAlbum());
		List<Musica> musicas = albumExistente.getMusicas();
		musicas.add(musica);
		albumExistente.setMusicas(musicas);
		return albumRepository.save(albumExistente);
	}

	private Musica addMusica(Musica musica, Album album) {
		musica.setEmailUsuario(retornaIdUsuario());
		musica.setAlbum(album);
		musicaRepository.save(musica);
		return musica;

	}

}
