package com.prueba.bibliotecaApi.funciones;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.servicios.UsuarioDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class UsuarioJWT {

  private final String palabraSecreta = "esto no es una clave secreta";


  @Autowired
  private UsuarioDAOImpl repositorio;

  private UsuarioJWT() {

  }

  public Usuario obtenerUsuarioJWT(HttpServletRequest request) {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(palabraSecreta.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String nombre = decodedJWT.getSubject();
        Usuario usuario = repositorio.findByNombre(nombre);
        return usuario;
      } catch (Exception exception) {
        return null;
      }
    } else {
      return null;
    }
  }
}
