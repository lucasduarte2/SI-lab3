package com.spotify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotify.models.Usuario;
import com.spotify.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario cadastroUsuario(Usuario novoUsuario) {
		Usuario usuario = usuarioRepository.findAllByEmailUsuario(novoUsuario.getEmailUsuario());
		if(usuario == null){
			return usuarioRepository.save(novoUsuario);	
		}
		return null;
	}

}
