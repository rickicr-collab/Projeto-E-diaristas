package br.com.rickicollab.ediaristas.core.services.consultadistancia.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistanciaResponse {

    private String origem;

    private String destino;

    private Double distanciaEmKm;

}
