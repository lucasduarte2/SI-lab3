package com.spotify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.spotify.models.Album;
import com.spotify.models.Artista;
import com.spotify.repository.ArtistaRepository;

@Service
public class ArtistaService {

	private final String FAVORITO = "favorite";
	private final String NFAVORITO = "favorite_border";
	private final String IMAGEMDEFAULT = "https://e-solutions.axa.com.br/img/profile-default.jpg";

	@Autowired
	private ArtistaRepository artistaRepository;

	private String retornaUserName() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (usuarioLogado instanceof UserDetails) {
			username = ((UserDetails) usuarioLogado).getUsername();
		} else {
			username = usuarioLogado.toString();
		}
		return username;
	}

	public Artista addArtista(Artista novoArtista) {
		Artista artistaExistente = procurarArtista(novoArtista.getNomeArtista());
		if (artistaExistente == null) {
			if (novoArtista.getLinkDaImagem() == null || novoArtista.getLinkDaImagem().trim().isEmpty()) {
				novoArtista.setLinkDaImagem(IMAGEMDEFAULT);
			}
			novoArtista.setFavorito(NFAVORITO);
			novoArtista.setEmailUsuario(retornaUserName());
			artistaRepository.save(novoArtista);
			return novoArtista;
		}
		return null;

	}

	public ModelAndView listaArtistas() {
		ModelAndView mv = new ModelAndView("paginaInicial");
		Iterable<Artista> artistas = artistaRepository.findAllByEmailUsuario(retornaUserName());
		mv.addObject("artistas", artistas);
		return mv;
	}

	public ModelAndView detalhesArtista(@PathVariable("id") long id) {
		Artista artista = artistaRepository.findById(id);
		ModelAndView mv = new ModelAndView("detalhes/detalhesArtista");
		mv.addObject("artista", artista);
		return mv;
	}

	public Artista addClassificacaoEUltimaMusicaArtista(@PathVariable("id") long id, Artista artista) {
		Artista artista2 = artistaRepository.findById(id);
		if (artista.getUltimaMusicaEscutada() != null) {
			artista2.setUltimaMusicaEscutada(artista.getUltimaMusicaEscutada());
		} else {
			artista2.setClassificacaoArtista(artista.getClassificacaoArtista());
		}
		return artistaRepository.save(artista2);
	}

	public Artista favoritaArtista(long id) {
		Artista artista = artistaRepository.findById(id);
		if (artista.getFavorito().equalsIgnoreCase(NFAVORITO)) {
			artista.setFavorito(FAVORITO);
		} else {
			artista.setFavorito(NFAVORITO);
		}
		return artistaRepository.save(artista);
	}

	public Artista pesquisarArtista(Artista artista) {
		List<Artista> artistasExistente = artistaRepository.findByEmailUsuario(retornaUserName());
		Artista artista2 = null;
		for (Artista artista3 : artistasExistente) {
			if (artista3.getNomeArtista().equals(artista.getNomeArtista())) {
				artista2 = artista3;
			}
		}
		return artista2;
	}

	public Artista addAlbumAoArtista(Album album, String nomeArtista) {
		Artista artistaDoUsuario = procurarArtista(nomeArtista);
		List<Album> albuns = artistaDoUsuario.getAlbuns();
		albuns.add(album);
		artistaDoUsuario.setAlbuns(albuns);
		return artistaRepository.save(artistaDoUsuario);
	}

	private Artista procurarArtista(String nomeArtista) {
		List<Artista> artistas = artistaRepository.findAllByNomeArtistaIgnoreCaseContaining(nomeArtista);
		Artista artistaDoUsuario = null;
		for (Artista artista : artistas) {
			if (artista.getEmailUsuario().equals(retornaUserName())) {
				artistaDoUsuario = artista;
			}
		}
		return artistaDoUsuario;
	}
	

}
