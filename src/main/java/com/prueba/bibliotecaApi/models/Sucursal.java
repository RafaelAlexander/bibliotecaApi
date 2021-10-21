package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Sucursal {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Getter @Setter
  private Long id;
  @Getter @Setter
  private String nombre;
  @Getter @Setter
  private String posicionX;
  @Getter @Setter
  private String posicionY;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_sucursal")
  @Getter
  private List<Libro> libros = new ArrayList<>();

  @Getter
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_sucursal")
  private List<Administrador> administradors = new ArrayList<>();

  Sucursal(String nombre, String posicionX, String posicionY){
    this.validarVaciosEn(nombre, posicionX, posicionY);
    this.nombre = nombre;

  }

  private void validarVaciosEn(String nombre, String posicionX, String posicionY) {
    if(nombre==null || posicionX == null || posicionY == null){
      throw new ValorVacioException("No completo todos los campos");
    }
  }

  List<Libro> librosEnPrestamo(){
    return libros.stream().filter(libro -> libro.estoyPrestado()).collect(Collectors.toList());
  }

  void agregarLibro(Libro libro, Administrador administrador){
    if(this.administradors.contains(administrador))
      this.libros.add(libro);
  }

  void agregarAdministrador(Administrador administrador){ this.administradors.add(administrador); }
}
