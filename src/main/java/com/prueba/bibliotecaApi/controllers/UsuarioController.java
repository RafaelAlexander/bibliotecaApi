package com.prueba.bibliotecaApi.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.servicios.UsuarioDAOImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
public class UsuarioController {

  @Autowired
  private UsuarioDAOImpl repositorio;

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

  @PostMapping(value = "/token/refresh")
  public void refrescarToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("esto no es una clave secreta".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String nombre = decodedJWT.getSubject();
        Usuario usuario = repositorio.findByNombre(nombre);
        String accessToken = JWT.create()
            .withSubject(usuario.getNombre())
            .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles",usuario.darNombreDeRoles())
            .sign(algorithm);
        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken",accessToken);
        tokens.put("refreshToken",refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
      } catch (Exception exception) {
        response.setHeader("error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("No se pudo refrescar el token");
    }
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
