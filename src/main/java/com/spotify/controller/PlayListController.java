package com.spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spotify.models.Musica;
import com.spotify.models.PlayList;
import com.spotify.service.PlayListService;

@Controller
public class PlayListController {
	
	@Autowired
	private PlayListService playListService;
	
	@RequestMapping(value = "/playList", method = RequestMethod.POST)
	public String formAddPlayList(PlayList novaPlayList){
		addPlayList(novaPlayList);
		return "redirect:/playList";
	}
	
	@RequestMapping(value = "/playList", method = RequestMethod.GET)
	public ModelAndView listaPlayList() {
		return playListService.listaPlayList();
	}
	
	@RequestMapping(value = "/playList/{idPlayList}")
	public ModelAndView detalhesPlayList(@PathVariable("idPlayList") long idPlayList) {
		return playListService.detalhesPlayList(idPlayList);
	}
	
	public ResponseEntity<Object> addPlayList(PlayList novaPlayList) {
		PlayList playList = playListService.addPlayList(novaPlayList);
		if(playList == null || novaPlayList.getNomePlayList().trim().isEmpty()){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(playList, HttpStatus.CREATED);
		
	}
	

//	@RequestMapping(value = "playList/{idPlayList}", method = RequestMethod.POST)
//	public String formAddMusicaNaPlayList(@PathVariable("idPlayList") long idPlayList, Musica novaMusica) {
//		addMusicaNaPlayList(idPlayList, novaMusica);
//		return "redirect:/playList/{idPlayList}";
//	}
//	
//	public ResponseEntity<Object> addMusicaNaPlayList(@PathVariable("idPlayList") long idPlayList, Musica novaMusica) {
//		PlayList playList = playListService.addMusicaNaPlayList(idPlayList, novaMusica);
//		if(playList == null){
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//		return new ResponseEntity<>(playList, HttpStatus.CREATED);
//		
//	}
		

}
