package com.prueba.bibliotecaApi.controllers;

import com.prueba.bibliotecaApi.servicios.SucursalDAOImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
public class SucursalController {
  @Autowired
  private SucursalDAOImpl repositorio;

  private final String palabraSecreta = "esto no es una clave secreta";


}
