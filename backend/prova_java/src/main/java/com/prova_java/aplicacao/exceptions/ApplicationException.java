package com.prova_java.aplicacao.exceptions;

public class ApplicationException extends RuntimeException{

    private static final long seralVersionID = 1L;

    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(String message, Throwable causa){
        super(message, causa);
    }

    public ApplicationException() {
    }
}
