package br.com.rickicollab.ediaristas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashMessage {

    private String classeCss;

    private String message;

}
