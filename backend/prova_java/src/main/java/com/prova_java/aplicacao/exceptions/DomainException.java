package com.prova_java.aplicacao.exceptions;

import java.io.Serial;

public class DomainException extends ApplicationException{

    private static final long serialVersionID = 1L;

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable causa){
        super(message, causa);
    }
}
