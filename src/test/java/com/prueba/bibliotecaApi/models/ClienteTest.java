package com.prueba.bibliotecaApi.models;

import com.prueba.bibliotecaApi.exceptions.ValorVacioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
  @Test
  public void tieneUnaCampoVacio() {
    assertThrows(ValorVacioException.class, ()->new Cliente("Rafael","Olmos","rafael@olmos",null));
  }
}