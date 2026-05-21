package br.com.rickicollab.ediaristas.api.dtos.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.rickicollab.ediaristas.core.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonNaming(SnakeCaseStrategy.class)
public class UsuarioResponse extends HateoasResponse {

    private Long id;
    private String nomeCompleto;
    private String email;
    private Integer tipoUsuario;
    private String cpf;
    private LocalDate nascimento;
    private String telefone;
    private String chavePix;
    private String fotoUsuario;

    @JsonIgnore
    public Boolean isCliente() {
        return tipoUsuario.equals(TipoUsuario.CLIENTE.getId());
    }

}
