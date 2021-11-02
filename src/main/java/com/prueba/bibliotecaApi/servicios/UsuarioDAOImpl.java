package com.prueba.bibliotecaApi.servicios;

import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.repositorios.RepoUsuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioDAOImpl implements UsuarioDAO, UserDetailsService {
  private final RepoUsuario repoUsuario;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<Usuario> findAll() {
    log.info("Listando todos los usuarios");
    return this.repoUsuario.findAll();
  }

  @Override
  public Usuario save(Usuario usuario) {
    log.info("Guardando Usuario");
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    return this.repoUsuario.save(usuario);
  }

  @Override
  public Usuario getById(Long id) {
    log.info("Buscando usuario por ID");
    return this.repoUsuario.getById(id);
  }

  @Override
  public void delete(Usuario usuario) {
    log.info("Se borro usuario");
    this.repoUsuario.delete(usuario);
  }

  @Override
  public Usuario findByNombre(String nombre) {
    return this.repoUsuario.findByNombre(nombre);
  }

  @Override
  public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
    Usuario usuario = repoUsuario.findByNombre(nombre);
    if (usuario == null) {
      log.error("No hay un usuario con ese nombre");
      throw new UsernameNotFoundException("No hay un usuario con ese nombre");
    } else {
      log.info("Usuario encontrado en la BD: {}", nombre);
    }
    return new org.springframework.security.core.userdetails.User(usuario.getNombre(),
        usuario.getPassword(),
        usuario.darNombreDeRoles().stream().map(rol -> new SimpleGrantedAuthority(rol)).collect(Collectors.toList()));
  }
}
