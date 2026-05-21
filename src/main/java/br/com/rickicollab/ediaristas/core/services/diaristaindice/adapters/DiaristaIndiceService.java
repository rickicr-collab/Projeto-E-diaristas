package br.com.rickicollab.ediaristas.core.services.diaristaindice.adapters;

import br.com.rickicollab.ediaristas.core.models.Diaria;
import br.com.rickicollab.ediaristas.core.models.Usuario;

public interface DiaristaIndiceService {
    
     public Usuario selecionarMelhorDiarista(Diaria diaria);
}
