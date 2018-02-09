package com.spotify.repository;

import org.springframework.data.repository.CrudRepository;

import com.spotify.models.Usuario;

public interface UsuarioRepository  extends CrudRepository<Usuario,String> {
	Usuario findAllByEmailUsuario(String name);
	Usuario findById(long id);
}
