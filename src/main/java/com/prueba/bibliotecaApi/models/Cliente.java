package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Rol {

  @Getter
  @Setter
  private String nombre;

  @Getter
  @Setter
  private String apellido;

  @Getter
  @Setter
  private String email;

  @Getter
  @Setter
  private String telefono;

  public Cliente(String nombre, String apellido, String email, String telefono) {
    this.verificarValorVacio(nombre, apellido, email, telefono);
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.telefono = telefono;
  }

  private void verificarValorVacio(String nombre, String apellido, String email, String telefono) {
    if (nombre == null || apellido == null || email == null || telefono == null) {
      throw new ValorVacioException("Usted no relleno todos los campos");
    }
  }
}
