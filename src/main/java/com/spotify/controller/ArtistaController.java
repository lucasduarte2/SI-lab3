package com.spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spotify.models.Artista;
import com.spotify.service.ArtistaService;

@Controller
public class ArtistaController {

	@Autowired
	private ArtistaService artistaService;

	@RequestMapping(value = { "/paginaInicial"}, method = RequestMethod.POST)
	public String pesquisarArtista(Artista artista) {
		Artista artistaEncontrado = artistaService.pesquisarArtista(artista);
		if(artistaEncontrado == null){
			return "redirect:/paginaInicial";
		}
		return "redirect:/artista/" + artistaEncontrado.getId();
	}
	
	@RequestMapping(value = "/cadastroArtista", method = RequestMethod.POST)
	public String formAddArtista(Artista novoArtista) {
		addArtista(novoArtista);
		return "redirect:/cadastroArtista";
	}

	@RequestMapping(value = "/paginaInicial", method = RequestMethod.GET)
	public ModelAndView listaArtistas() {
		return artistaService.listaArtistas();
	}

	@RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesArtista(@PathVariable("id") long id) {
		return artistaService.detalhesArtista(id);
	}

	@RequestMapping(value = "/artista/{id}", method = RequestMethod.POST)
	public String addClassificacaoEUltimaMusicaArtista(@PathVariable("id") long id, Artista artista) {
		artistaService.addClassificacaoEUltimaMusicaArtista(id, artista);
		return "redirect:/artista/{id}";
	}

	@RequestMapping("/favoritarArtista")
	public String favoritaArtista(long id) {
		artistaService.favoritaArtista(id);
		return "redirect:/paginaInicial";
	}


	public ResponseEntity<Object> addArtista(Artista novoArtista) {
		Artista artista = artistaService.addArtista(novoArtista);
		if (artista == null || artista.getNomeArtista().trim().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(artista, HttpStatus.CREATED);
	}
	
}
