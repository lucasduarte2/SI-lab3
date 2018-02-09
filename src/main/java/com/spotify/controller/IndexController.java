package com.spotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/spotify")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/cadastrarMusica")
	public String cadastroMusicaEAlbum() {
		return "cadastroMusicaEAlbum";
	}
	
	@RequestMapping(value = "/cadastroArtista")
	public String cadastroArtista() {
		return "cadastroArtista";
	}
	
	@RequestMapping(value = "/playList")
	public String addPlayList() {
		return "playList";
	}
}
