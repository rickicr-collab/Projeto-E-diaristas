package br.com.rickicollab.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rickicollab.ediaristas.core.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
