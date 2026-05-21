package br.com.rickicollab.ediaristas.api.dtos.response;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class UsuarioDiariaResponse {

    private Long id;

    private String nomeCompleto;

    private LocalDate nascimento;

    private String fotoUsuario;

    private String telefone;

    private Integer tipoUsuario;

    private Double reputacao;

}
