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
		String usuario = retornaIdUsuario();
		Album albumExistente = albumUsuario(album, usuario);
		if (albumExistente != null ) {
			Musica novaMusica = addMusica(musica, albumExistente);
			addMusicaAoAlbum(novaMusica, album);
			return albumExistente;
		}

		Album novoAlbum = addNovoAlbum(album, usuario);
		Musica novaMusica = addMusica(musica, novoAlbum);
		artistaService.addAlbumAoArtista(novoAlbum, novaMusica.getArtista());
		return novoAlbum;
	}
	
	private Album addNovoAlbum(Album album, String usuario) {
		album.setEmailUsuario(usuario);
		return albumRepository.save(album);
	}
	
	private Album albumUsuario(Album album, String usuario){
		List<Album> albuns = albumRepository.findAllByEmailUsuario(usuario);
		for (Album album2 : albuns) {
			if(album.getNomeAlbum().equals(album2.getNomeAlbum())){
				return album2;
			}
		}
		return null;
	}

	private Album addMusicaAoAlbum(Musica musica, Album album) {
		Album albumExistente = albumUsuario(album, retornaIdUsuario());
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
