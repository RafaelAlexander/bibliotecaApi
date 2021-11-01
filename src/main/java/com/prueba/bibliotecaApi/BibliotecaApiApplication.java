package com.prueba.bibliotecaApi;

import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.servicios.UsuarioDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BibliotecaApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(BibliotecaApiApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(UsuarioDAO usuarioDAO) {

    return args -> {
      usuarioDAO.save(new Usuario("Rafael", "qwerty1234"));
      usuarioDAO.save(new Usuario("Alexander", "qwerty1234"));
    };
  }
}
