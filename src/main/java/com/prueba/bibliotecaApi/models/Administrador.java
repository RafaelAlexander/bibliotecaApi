package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("adm")
public class Administrador extends Rol {
  @Getter
  @Setter
  private Long numeroDePersonal;

  public Administrador(Long numeroDePersonal, Sucursal sucursal) {
    if (numeroDePersonal == null)
      throw new ValorVacioException("Debe colocar un numero de personal");
    this.numeroDePersonal = Objects.requireNonNull(numeroDePersonal, "Usted debe ingresar un numero de personal");
    if (sucursal == null)
      throw new ValorVacioException("Debe colocar una sucursal");
    sucursal.agregarAdministrador(this);
  }

  void agregarLibro(Libro libro, Sucursal sucursal) {
    sucursal.agregarLibro(libro, this);
  }
}
