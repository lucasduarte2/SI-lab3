package com.spotify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spotify.models.Usuario;
import com.spotify.repository.UsuarioRepository;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = ur.findAllByEmailUsuario(email);
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
	}
	
}
