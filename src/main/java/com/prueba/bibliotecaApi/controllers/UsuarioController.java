package com.prueba.bibliotecaApi.controllers;

import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.servicios.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
public class UsuarioController {

  @Autowired
  private UsuarioDAO repositorio;

  @GetMapping(value = "/usuarios")
  public ResponseEntity<List<Usuario>> darTodosLoUsuarios() {
    return ResponseEntity.ok().body(repositorio.findAll());
  }

  @PostMapping(value = "/usuario/save")
  public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario) {
    URI uri = URI.create(ServletUriComponentsBuilder.
        fromCurrentContextPath().
        path("/api/usuario/save").toUriString());
    return ResponseEntity.created(uri).body(this.repositorio.save(usuario));
  }

  @DeleteMapping(value = "/usuario/{id}/delete")
  public ResponseEntity<?> borrar(@PathVariable Long id) {
    Usuario usuario = this.repositorio.getById(id);
    if (usuario != null) {
      this.repositorio.delete(usuario);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.internalServerError().build();
  }
}
