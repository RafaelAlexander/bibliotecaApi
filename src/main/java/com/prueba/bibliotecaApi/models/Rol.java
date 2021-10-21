package com.prueba.bibliotecaApi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "tipoDeRol")
public abstract class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Getter @Setter
  private Long id;
}
