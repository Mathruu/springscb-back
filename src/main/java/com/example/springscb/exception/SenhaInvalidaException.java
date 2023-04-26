package com.example.springscb.exception;

public class SenhaInvalidaException extends RuntimeException {
    
        public SenhaInvalidaException() {
            super("Senha inválida");
        }
}
