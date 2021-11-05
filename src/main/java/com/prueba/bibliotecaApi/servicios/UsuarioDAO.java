package com.prueba.bibliotecaApi.servicios;

import com.prueba.bibliotecaApi.models.Usuario;

import java.util.List;

public interface UsuarioDAO {
  List<Usuario> findAll();

  Usuario save(Usuario usuario);

  Usuario getById(Long id);

  void delete(Usuario usuario);

  Usuario findByNombre(String nombre);
  
}
