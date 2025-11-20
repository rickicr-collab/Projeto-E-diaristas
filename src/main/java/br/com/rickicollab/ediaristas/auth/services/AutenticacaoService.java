package br.com.rickicollab.ediaristas.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rickicollab.ediaristas.auth.models.UsuarioAutenticado;
import br.com.rickicollab.ediaristas.core.repositories.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var message = String.format("Usuario com email %s não encontrado!", email);
        return repository.findByEmail(email)
        .map((usuario) -> new UsuarioAutenticado(usuario))
        .orElseThrow(() -> new UsernameNotFoundException(message));
        

    }

}
