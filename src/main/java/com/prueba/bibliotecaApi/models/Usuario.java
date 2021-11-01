package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.RolErroneoException;
import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_usuario")
  private List<Rol> roles = new ArrayList<>();

  @Getter @Setter
  private int cantMaxRol;

  public Usuario(String nombre, String password) {
    this.nombre = nombre;
    this.password = password;
  }

  public Usuario() {
  }

  private void verificarVacio(String username, String password) {
    if(username == null|| password == null){
      throw new ValorVacioException("Usted debe rellenar todos los campos");
    }
  }

  public void agregarRol(Rol rol){
    if(this.roles.stream().map(rolX -> rolX.getClass()).collect(Collectors.toList()).contains(rol.getClass())){
      throw new RolErroneoException("Usted ya tiene este rol");
    }
    this.roles.add(rol);
  }

  public List<String> darNombreDeRoles(){
    List<String> rolesToString = this.roles.stream().map(Object::getClass).map(Class::toString).collect(Collectors.toList());
    rolesToString.add("Usuario");
    return rolesToString;
  }
}
