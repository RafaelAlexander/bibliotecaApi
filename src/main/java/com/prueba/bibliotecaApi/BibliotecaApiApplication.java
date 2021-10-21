package com.prueba.bibliotecaApi;

import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.repositorios.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApiApplication.class, args);
	}
}
