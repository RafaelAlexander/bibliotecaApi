package com.prueba.bibliotecaApi.controllers;

import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.repositorios.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class UsuarioController {

  @Autowired
  private UsuarioDAO repositorio;

  @GetMapping(value = "/usuarios")
  public ResponseEntity<List<Usuario>> darTodosLoUsuarios() {
    return new ResponseEntity<List<Usuario>>(this.repositorio.findAll(), HttpStatus.OK);
  }

  @PostMapping(value = "/usuario")
  public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario) {
    this.repositorio.save(usuario);
    return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/usuario/{id}")
  public ResponseEntity<Usuario> borrar(@PathVariable Long id) {
    Usuario usuario = this.repositorio.getById(id);
    if (usuario != null) {
      this.repositorio.delete(usuario);
      return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    return new ResponseEntity<Usuario>(usuario, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
