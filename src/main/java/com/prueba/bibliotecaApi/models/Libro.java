package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.LibroSinPrestarException;
import com.prueba.bibliotecaApi.exceptions.RolErroneoException;
import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private int cantidadPaginas;

  @Getter
  @Setter
  private String descripcion;

  @OneToOne(cascade = CascadeType.MERGE)
  @Getter
  @Setter
  private Cliente propietarioTransitorio;


  @OneToOne(cascade = CascadeType.MERGE)
  private Administrador ingresante;

  public Administrador getIngresante() {
    return ingresante;
  }

  public Libro(int cantidadPaginas, String descripcion, Sucursal sucursal, Administrador administrador) {
    if (descripcion.isEmpty())
      throw new ValorVacioException("Usted no coloco la descripcion");
    this.descripcion = descripcion;
    this.cantidadPaginas = cantidadPaginas;
    if (sucursal == null) {
      throw new ValorVacioException("Usted no coloco una sucursal para asociar al libro");
    }
    if (administrador == null) {
      throw new RolErroneoException("Usted no es un administrador");
    }
    sucursal.agregarLibro(this, administrador);
  }

  void sePrestoA(Cliente propietarioTransitorio) {
    if (this.propietarioTransitorio != null) {
      throw new LibroSinPrestarException("Este libro, ya fue prestado");
    }
    this.propietarioTransitorio = propietarioTransitorio;
  }

  boolean estoyPrestado() {
    return propietarioTransitorio == null ? false : true;
  }
}
