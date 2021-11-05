package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.RolErroneoException;
import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Getter
  @Setter
  private Long id;
  @Getter
  @Setter
  private String nombre;
  @Getter
  @Setter
  private String password;

  @Getter
  @Setter
  @OneToOne(cascade = CascadeType.ALL)
  private Cliente cliente;

  @Getter
  @Setter
  @OneToOne(cascade = CascadeType.ALL)
  private Administrador administrador;

  @Getter
  @Setter
  private int cantMaxRol;

  public Usuario(String nombre, String password) {
    this.nombre = nombre;
    this.password = password;
  }

  public Usuario() {
  }

  private void verificarVacio(String username, String password) {
    if (username == null || password == null) {
      throw new ValorVacioException("Usted debe rellenar todos los campos");
    }
  }

  public List<String> darNombreDeRoles() {
    List<String> rolesToString = new ArrayList<>();
    if (this.cliente != null)
      rolesToString.add(this.cliente.getClass().toString());
    if (this.administrador != null)
      rolesToString.add(this.administrador.getClass().toString());
    rolesToString.add("Usuario");
    return rolesToString;
  }
}
