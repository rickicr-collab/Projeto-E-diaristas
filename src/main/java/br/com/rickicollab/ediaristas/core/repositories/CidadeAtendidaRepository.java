package br.com.rickicollab.ediaristas.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rickicollab.ediaristas.core.models.CidadeAtendida;

public interface CidadeAtendidaRepository extends JpaRepository<CidadeAtendida, Long>{
    Optional<CidadeAtendida> findByCodigoIbge(String codigoIbge);
}
