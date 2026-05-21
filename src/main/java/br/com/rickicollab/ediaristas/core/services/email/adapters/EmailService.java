package br.com.rickicollab.ediaristas.core.services.email.adapters;

import br.com.rickicollab.ediaristas.core.services.email.dtos.EmailParams;


public interface EmailService {
    void enviarEmailComTemplateHtml(EmailParams params);
}
