package br.com.rickicollab.ediaristas.api.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeAtendidaResponse {

    private Long id;
    private String cidade;
    private String estado;
    private String codigoIbge;

}