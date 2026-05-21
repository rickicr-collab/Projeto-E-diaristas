package br.com.rickicollab.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rickicollab.ediaristas.core.models.TokenBlackList;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

      Boolean existsByToken(String token);
    
}
