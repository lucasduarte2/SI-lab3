package com.spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spotify.models.Usuario;
import com.spotify.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/spotify", method = RequestMethod.POST)
	public String formCadastroUsuario(Usuario novoUsuario){
		cadastroUsuario(novoUsuario);
		return "redirect:/login";
	}
	
	
	private ResponseEntity<Object> cadastroUsuario(Usuario novoUsuario) {
		Usuario usuario = usuarioService.cadastroUsuario(novoUsuario);
		if(usuario == null){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}

}
