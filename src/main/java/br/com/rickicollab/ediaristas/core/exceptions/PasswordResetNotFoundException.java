package br.com.rickicollab.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PasswordResetNotFoundException extends EntityNotFoundException{

    public PasswordResetNotFoundException() {}

    public PasswordResetNotFoundException(String message) {
        super(message);
    }
    
}
