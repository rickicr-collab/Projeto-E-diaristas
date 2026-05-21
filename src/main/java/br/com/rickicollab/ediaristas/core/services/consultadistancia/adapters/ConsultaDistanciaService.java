package br.com.rickicollab.ediaristas.core.services.consultadistancia.adapters;

import br.com.rickicollab.ediaristas.core.services.consultadistancia.dtos.DistanciaResponse;

public interface ConsultaDistanciaService {

     DistanciaResponse calcularDistanciaEntreDoisCeps(String origem, String destino);
    
}
