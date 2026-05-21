package br.com.rickicollab.ediaristas.api.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class DiaristaLocalidadeResponse {

    private String nomeCompleto;

    private Double reputacao;

    private String fotoUsuario;

    private String cidade;


    
}
