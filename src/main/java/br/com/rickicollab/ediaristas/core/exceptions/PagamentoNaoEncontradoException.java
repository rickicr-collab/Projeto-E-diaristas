package br.com.rickicollab.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PagamentoNaoEncontradoException extends EntityNotFoundException{

    public PagamentoNaoEncontradoException() {
        super("Pagamento não encontrado");
    }

    public PagamentoNaoEncontradoException(String message) {
        super(message);
    }
    
}
