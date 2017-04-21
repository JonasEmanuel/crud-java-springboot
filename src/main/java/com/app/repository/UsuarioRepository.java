package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
