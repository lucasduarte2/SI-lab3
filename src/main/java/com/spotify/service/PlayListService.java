package com.spotify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.spotify.models.Musica;
import com.spotify.models.PlayList;
import com.spotify.repository.MusicaRepository;
import com.spotify.repository.PlayListRepository;

@Service
public class PlayListService {
	
	@Autowired
	private PlayListRepository playListRepository;
	
	@Autowired
	private MusicaService musicaService;
	
	@Autowired
	private MusicaRepository musicaRepository;

	
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
	

	public PlayList addPlayList(PlayList novaPlayList) {
		PlayList playList = procurarPlayList(novaPlayList);
		if(playList == null) {
			novaPlayList.setEmailUsuario(retornaUserName());
			playListRepository.save(novaPlayList);
		}
		return playList;
		
	}
	
	public PlayList deletaPlayList(long idPlayList) {
		PlayList playList = playListRepository.findByIdPlayList(idPlayList);
		playListRepository.delete(playList);
		return playList;
	}
	
	
	public ModelAndView listaPlayList() {
		ModelAndView mv = new ModelAndView("playList");
		Iterable<PlayList> playList = playListRepository.findAllByEmailUsuario(retornaUserName());
		mv.addObject("playLists", playList);
		return mv;
	}
	
	public ModelAndView detalhesPlayList(@PathVariable("idPlayList") long idPlayList) {
		PlayList playList = playListRepository.findByIdPlayList(idPlayList);
		ModelAndView mv = new ModelAndView("detalhes/detalhesPlayList");
		mv.addObject("playList", playList);
		return mv;
	}
	
	private PlayList procurarPlayList(PlayList playList) {
		List<PlayList> playLists = playListRepository.findAllByEmailUsuario(retornaUserName());
		PlayList playListEncontrada = null;
		for (PlayList playList2 : playLists) {
			if(playList2.getNomePlayList().equals(playList.getNomePlayList())){
				playListEncontrada = playList2;
			}
		}
		return playListEncontrada;
	}
	
	public PlayList addMusicaNaPlayList(@PathVariable("idPlayList") long idPlayList, Musica novaMusica){
		PlayList playList = playListRepository.findByIdPlayList(idPlayList);
		Musica musica = musicaService.procurarMusica(novaMusica, retornaUserName());
		List<Musica> musicasDaPlayListDoUsuario = playList.getMusicas();
		if(!musicasDaPlayListDoUsuario.contains(musica)){
			musicasDaPlayListDoUsuario.add(musica);
			musica.setPlayList(playList);
			musicaRepository.save(musica);
		}
		playList.setMusicas(musicasDaPlayListDoUsuario);
		return playListRepository.save(playList);
	}
	
	
	
	public PlayList deletaMusicaDaPlayList(String musica) {
		Musica musica2 = musicaService.procurarMusica2(musica, retornaUserName());
		PlayList playList = playListRepository.findByIdPlayList(musica2.getPlayList().getIdPlayList());
		List<Musica> musicasDaPlayListDoUsuario = playList.getMusicas();
		musicasDaPlayListDoUsuario.remove(musica2);
		playList.setMusicas(musicasDaPlayListDoUsuario);
		musica2.setPlayList(null);
		musicaRepository.save(musica2);
		return playListRepository.save(playList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
