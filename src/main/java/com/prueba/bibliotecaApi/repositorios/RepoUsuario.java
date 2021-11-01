package com.prueba.bibliotecaApi.repositorios;

import com.prueba.bibliotecaApi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUsuario extends JpaRepository<Usuario, Long> {
  Usuario findByNombre(String nombre);
}
