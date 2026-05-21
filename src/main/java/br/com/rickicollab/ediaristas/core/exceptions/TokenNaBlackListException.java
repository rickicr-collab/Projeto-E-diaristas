package br.com.rickicollab.ediaristas.core.exceptions;

public class TokenNaBlackListException extends RuntimeException {

    public TokenNaBlackListException() {
        super("O token informado está inválidado");
    }

    public TokenNaBlackListException(String message) {
        super(message);
    }

    
}
