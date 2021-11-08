package com.prueba.bibliotecaApi.servicios;

import com.prueba.bibliotecaApi.models.Sucursal;
import com.prueba.bibliotecaApi.models.Usuario;
import com.prueba.bibliotecaApi.repositorios.RepoSucursal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SucursalDAOImpl implements SucursalDAO {
  private final RepoSucursal repositorio;

  @Override
  public Sucursal save(Sucursal sucursal) {
    return null;
  }
}
