package com.prueba.bibliotecaApi.repositorios;

import com.prueba.bibliotecaApi.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoSucursal extends JpaRepository<Sucursal, Long> {
}
