package br.com.rickicollab.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rickicollab.ediaristas.core.models.Usuario;
import br.com.rickicollab.ediaristas.core.repositories.UsuarioRepository;

@Service
public class WebUsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public List<Usuario> buscarTodos(){
        var lista = repository.findAll();
        return lista;
    }

    

}
