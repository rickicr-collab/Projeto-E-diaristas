package br.com.rickicollab.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rickicollab.ediaristas.core.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
