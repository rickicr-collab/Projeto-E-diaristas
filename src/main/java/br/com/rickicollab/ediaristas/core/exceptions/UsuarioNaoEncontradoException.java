package br.com.rickicollab.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UsuarioNaoEncontradoException extends EntityNotFoundException {

    public UsuarioNaoEncontradoException(String message){
        super(message);
    }

}
