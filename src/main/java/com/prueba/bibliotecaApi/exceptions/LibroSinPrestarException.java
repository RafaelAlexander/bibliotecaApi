package com.prueba.bibliotecaApi.exceptions;

public class LibroSinPrestarException extends RuntimeException{
  public LibroSinPrestarException(String msg) { super(msg); }
}
