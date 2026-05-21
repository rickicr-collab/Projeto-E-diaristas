package br.com.rickicollab.ediaristas.core.services.email.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailParams {

    private String destinatario;
    private String assunto;
    private String template;
    private Map<String, Object> props;

}
