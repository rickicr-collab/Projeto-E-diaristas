package br.com.rickicollab.ediaristas.api.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AvaliacaoRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String descricao;

    @NotNull
    @PositiveOrZero
    @Max(value = 5)
    private Double nota;

}