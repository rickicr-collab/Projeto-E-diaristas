package br.com.rickicollab.ediaristas.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUsuario {

    ADMIN (1),
    CLIENTE(2),
    DIARISTA (3);

    private Integer id;

}
